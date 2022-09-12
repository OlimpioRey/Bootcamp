package es.orc.socialmedia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

	private String name;
	List<User> followedList = new ArrayList<>();
	List<Post> postList = new ArrayList<>();	

	public User(String name) {
		this.name = name;
	};
	
	public void setFollowedlist(List<User> followers) {
		this.followedList = followers;
	}
	
	public List<User> getFollowedlist(){
		return this.followedList;
	}
	
	public void setListpost(List<Post> postList) {
		this.postList = postList;
	}
	
	public List<Post> getListpost(){
		return this.postList;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void addFollowed(User user) {
		this.followedList.add(user);
	}
	
	
	public void delFollowed(User user) {
		for (User userF : followedList) {
			if (userF.getName().equals(user.getName())) {
				this.followedList.remove(user);
				break;
			}
		}
	}
	
	
	public void deleteData() {
		Post post = null;
		
		// borra todos sus post y comentarios asociados
		for (int i = 0; i < postList.size(); i++) {
			post = postList.get(i);
			post.deleleAllComments();
			postList.remove(i);
		}		
	}
	
	
	public void showPost() {
		Post post = null;
		String separator = "--------------------------------------------";
		System.out.println("\n\nLista de post de " + this.name + "\n" + separator);
		for (int i = 0; i < this.postList.size(); i++) {
			post = postList.get(i);
			System.out.println(i + ": " + post.getDetails());
		}
		System.out.println(separator + "\n");
	}
	
	public void addPost() {
		Post post = null;
		String postType = "";
		String content = "";
		String postDate = "";
		String title = "";
		int width = 0;
		int height = 0;
		int quality = 0;
		int duration = 0;
		List<Comment> commentList = new ArrayList<>();
		LocalDate datePost = LocalDate.now();
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		// solicita datos del post
		postType = Input.string("\nTipo de post (<T>exto / <I>magen / <V>ideo: ");
		postDate = Input.string("\nFecha (dd/mm/aaaa): ");
		datePost = LocalDate.parse(postDate, formatDate);
		
		// solicita el tipo de post y sus datos
		switch (postType.toUpperCase()) {
		case "I":
			title = Input.string("\nTitulo: ");
			width = Input.integer("\nAncho (999px): ");
			width = Input.integer("\nAlto: (999px)");
			post = new ImagePost(datePost, commentList, title, width, height);
			break;
		case "T":
			content =Input.string("\nTitulo: ");
			//LocalDate datePost, List<Comment> commentList, String contents
			post = new TextPost(datePost, commentList, content);
			break;
		case "V":
			title = Input.string("\nTitulo: ");
			quality= Input.integer("\nCalidad (999p): ");
			duration = Input.integer("\nDuracion: ");
			// LocalDate datePost, List<Comment> commentList, String title, int quality, int duration
			post = new VideoPost(datePost, commentList, title, quality, duration);
			break;
		default:
			System.out.println("Tipo incorrecto.");
			break;
		}
		
		// añade el post a la lista
		if (post != null) {
			this.postList.add(post);
		}
		
	}
	
	
	public void deleleAPost() {
		int indexPost = -1;
		
		showPost(postList);
		
		indexPost = Input.integer("\nSelecciona un post: ");
		
		if ( (indexPost >= 0) && (indexPost < postList.size()) ) {
			Post post = postList.get(indexPost);
			post.deleleAllComments();
			this.postList.remove(indexPost);
		}
	}
	
	
	// a quién sigue en la red social
	public List<User> whoIFollow(List<User> usersList) {
		List<User> iFollowList = new ArrayList();
		
		// para cada usuario registrado en la red social
		for (User user : usersList) {
			// descargo su lista de seguidores
			for (User follower : user.getFollowedlist()) {
				// compruebo si el usuario está en esta lista
				if (follower.getName().equals(this.name)) {
					// lo añado a la lista de usuarios que está siguiendo
					iFollowList.add(user);
				}
			}
		}
		
		return iFollowList;	
	}
	
	// obtener la lista de los post de los usuarios a los que sigue
	public List<Post> postThatIFollow(List<User> usersList) {
		List<Post> postIFollow = new ArrayList();
		List<Post> postUser = new ArrayList();
		
		for (User user : whoIFollow(usersList)) {
			postUser = user.getListpost();
			for (Post post : postUser) {
				postIFollow.add(post);
			}
		}
		
		return postIFollow;
	}
	
	// comentar un post
	public void comentOnAPost(List<User> usersList) {
		Post post = selectAPost(usersList);
		
		if (!(Objects.isNull(post))) {
			post.addComment(this);
		}		
	}

	public void deleteAComment(List<User> usersList) {
		Post post = selectAPost(usersList);
		
		if (!(Objects.isNull(post))) {
			int indexCommentsList = selectAComment(post);
			if (indexCommentsList >= 0) {
				post.delComment(indexCommentsList);
			}
		}
	}

	public Post selectAPost(List<User> usersList) {
		List<Post> postIFollow = new ArrayList();
		Post post = null;
		int indexPost = -1;
		
		// obtener la lista de Post de los usuarios a los que sigue
		postIFollow = postThatIFollow(usersList);

		showPost(postIFollow);
		
		indexPost = Input.integer("\nSelecciona un post: ");
		
		if ( (indexPost >= 0) && (indexPost < postIFollow.size()) ) {
			post = postIFollow.get(indexPost);
		}
		
		return post;
	}
	
	public int selectAComment(Post post) {
		List<Comment> commentsList = post.getCommentList();
		int indexCommentsList = -1;
		
		showComments(post, commentsList);
		indexCommentsList = Input.integer("\nSelecciona un comentario: ");
				
		return indexCommentsList;
	}
	
	public void showPost(List<Post> postList) {
		List<Comment> comments = new ArrayList<>();
		Comment comment = null;
		Post post = null;
		String separator = "--------------------------------------------";

		System.out.println("\n\nLista de post en seguimiento\n" + separator);

		for (int i = 0; i < postList.size(); i++) {
			post = postList.get(i);
			System.out.println(i + ": " + post.getDetails());
			comments = post.getCommentList();
			for (int j = 0; j < comments.size(); j++) {
				comment = comments.get(j);
				System.out.println("   ... " + comment.getDetails());
			}
		}
		
		System.out.println(separator + "\n");	
	}
	
	public void showComments(Post post, List<Comment> commentsList) {
		Comment comment = null;
		String separator = "--------------------------------------------";
		
		System.out.println("\n\nPost:");
		System.out.println(post.getDetails());
		System.out.println(separator);
		System.out.println("Comentarios (" + post.numberOfComments() + "):");
		
		for (int i = 0; i < commentsList.size(); i++) {
			comment = commentsList.get(i);
			System.out.println("   .." + i + "> " + comment.getDetails());
		}
		
		System.out.println(separator + "\n");
	}


	public void showCommentsMade(List<User> usersList) {
		List<Post> postIFollow = postThatIFollow(usersList);
		List<Comment> comments = new ArrayList();
		List<Comment> myComments = new ArrayList();
		
		for (Post post : postIFollow) {
			comments = post.getCommentList();
			for (Comment comment : comments) {
				if (comment.getAuthor().getName().equals(this.getName())) {
					myComments.add(comment);
				}
			}
			showComments(post, myComments);
		}
	}
	
}