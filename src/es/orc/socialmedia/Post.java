package es.orc.socialmedia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Post {

	private LocalDate datePost;
	List<Comment> comments = new ArrayList<>();
	
	public Post(LocalDate datePost, List<Comment> commentList) {
		this.datePost = datePost;
		this.comments = commentList;
	}
	
	public void setCommentList(List<Comment> commentList) {
		this.comments = commentList;
	}
	
	public LocalDate getDate() {
		return this.datePost;
	}
	
	public List<Comment> getCommentList(){
		return this.comments;
	}
	
	public String getDetails() {
		return (getDate().toString());
	}
	
	public void addComment(User user) {
		Comment comment = new Comment(Input.string("\nIntroduce comentario: "), LocalDate.now(), user);
		this.comments.add(comment);
	}
	
	public void delComment(int index) {
		this.comments.remove(index);
	}
	
	public void deleleAllComments() {
		this.comments.removeAll(comments);
	}
	
	public int numberOfComments() {
		return this.comments.size();
	}
}

