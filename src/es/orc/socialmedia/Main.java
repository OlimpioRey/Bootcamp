package es.orc.socialmedia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		//La lista de los usuarios que forman parte de la red social
		List<User> usersList = new ArrayList<>();
		//Usuarios
		User pepe = new User("pepe");
		User manolita = new User("manolita");
		User juan = new User("juan");
		User julia = new User("julia");
		User sandra = new User("sandra");
		User diego = new User("diego");
		User carlos = new User("carlos");
		
		//seguidores de pepe
		List<User> followedpepe = new ArrayList<>();
		followedpepe.add(manolita);
		followedpepe.add(diego);
		//seguidores de manolita
		List<User> followedmanolita = new ArrayList<>();
		followedmanolita.add(juan);
		followedmanolita.add(diego);
		
		//post de pepe
		Post imagpepe = new ImagePost(LocalDate.of(2022, 5, 4),new ArrayList<Comment>(), "Selfie.jpg", 400, 450);
		Post textpepe = new TextPost(LocalDate.of(2022, 4, 1), new ArrayList<Comment>(), "Hoy salí de acampada");
		List<Post> postlistpepe = new ArrayList<>();
		postlistpepe.add(imagpepe);
		postlistpepe.add(textpepe);
		
		//comentarios de diego y manolita en el post de pepe
		Comment commenttextpepediego = new Comment("Menuda suerte", LocalDate.of(2022, 4, 2), diego);
		Comment commenttextpepemanolita = new Comment("Espero que no lloviese", LocalDate.of(2022, 4, 2), manolita);
		List<Comment>commentlist = new ArrayList<>();
		commentlist.add(commenttextpepemanolita);
		commentlist.add(commenttextpepediego);
		//añadimos los comentarios al post de pepe
		textpepe.setCommentList(commentlist);
		//añadimos la lista de post a pepe
		pepe.setListpost(postlistpepe);
		//añadimos amigos de pepe a pepe
		pepe.setFollowedlist(followedpepe);
		//añadimos amigos de manolita a manolita
		manolita.setFollowedlist(followedmanolita);
		
		//añadimos los usuarios a la lista de usuarios de la red social
		usersList.add(diego);
		usersList.add(carlos);
		usersList.add(pepe);
		usersList.add(manolita);
		usersList.add(julia);
		usersList.add(sandra);
		usersList.add(juan);
		

		//en el menú se llaman a las funcionalidades descritas en el pdf
		int option = 0;
		String result = "";
		User activeUser = null;
		User selUser = null;
		String nameActiveUser = "";
		
		//menuApp();
		
		do {
			option = Input.integer( "Usuario activo: " + nameActiveUser + "\n\n"
					+   "Acciones:\n"
					+   " '1' - Crear Usuario\n"
					+   " '2' - Seleccionar un usuario\n"
					+   " '3' - Comenzar a seguir a otro usuario\n"
					+   " '4' - Dejar de seguir a un usuario\n"
					+   " '5' - Añadir post\n"
					+   " '6' - Añadir comentarios\n"
					+   " '7' - Eliminar un usuario\n"
					+   " '8' - Eliminar post\n"
					+   " '9' - Eliminar comentario\n"
					+   " '10' - Listar post de un usuario\n"
					+   " '11' - Listar comentarios de un usuario\n"
					+   " '12' - Número de comentarios de un post\n"
					+   " '99' - Cerrar aplicación: ");

			switch (option) {
				case 1:
					result = newUser(usersList);
					break;
				case 2:
					activeUser = selUser(usersList);
					nameActiveUser = activeUser.getName();
					break;
				case 3:
					selUser = selUser(usersList);
					selUser.addFollowed(activeUser);
					break;
				case 4:
					selUser = selUser(usersList);
					selUser.delFollowed(activeUser);
					break;
				case 5:
					activeUser.addPost();
					break;
				case 6:
					activeUser.comentOnAPost(usersList);
					break;
				case 7:
					deleteUser(activeUser, usersList);
					activeUser = null;
					break;
				case 8:
					activeUser.deleleAPost();
					break;
				case 9:
					activeUser.deleteAComment(usersList);
					break;
				case 10:
					activeUser.showPost();
					break;
				case 11:
					activeUser.showCommentsMade(usersList);
					break;
				case 12:
					break;
				case 99:
					break;
				default:
					System.out.println("Acción inexistente");
					break;
			}
	
		} while (option != 99);
				
	}
	
	
	
	public static void menuApp() {
		String userName = "";
		String option = "";
		User activeUser = null;
			
		do {
			System.out.println("Red Social 1.0"); 
			userName = Input.string( "\nIntroduce tu nombre de usuario (intro para ver opciones):\n");

			if (userName != "") {
				//activeUser = getUser();
				if (activeUser == null) {
					System.out.println("<!> El usuario no existe.");
				} else {
				}
			} else {
				do {
					option = Input.string( "\nSelecciona: [C]rear nuevo usuario, [R]epetir ingreso, [S]alir:\n");
					
					switch (option) {
					case "C":
						break;
					case "S":
						System.exit(0);
						break;
					default:
						System.out.println("Acción inexistente");
						option = "";
						break;
					}	
				} while (option == "");
			}	
		} while (option != "S");
		
		
		
	}
	
	
	private static User selUser(List<User> usersList) {
		int userIndex = 0;
		
		listUsers(usersList);
		
		userIndex = Input.integer("\nSelecciona un usuario: ");
		
		return (usersList.get(userIndex));
	}
	
	private static String newUser(List<User> usersList) {
		String userName = "";
		String result = "";
		userName = Input.string("\nNombre para el usuario: ");
		if (searchUser(usersList, userName) == null) {
			User newUser = new User(userName);
			usersList.add(newUser);
			result = "Usuario " + newUser.getName() +" creado.";
		} else {
			result = "Ya existe otro usuario con este nombre.";
		}
		return result;
	}


	public static void deleteUser(User user, List<User> usersList) {
		User userInList = null;
		user.deleteData();
		
		for (int i = 0; i < usersList.size(); i++) {
			userInList = usersList.get(i);
			if (userInList.getName().equals(user.getName())) {
				usersList.remove(i);
				break;
			}
		}
	}
	
	private static void listUsers(List<User> usersList) {
		System.out.println("\n\nLista de usuarios");
		for (int i = 0; i < usersList.size(); i++) {
			System.out.println(i + ": " + usersList.get(i).getName());
		}
	}
	
		
	
	private static User searchUser(List<User> usersList, String nameUser) {
		User result = null;
		for (User user : usersList) {
			if (user.getName().equals(nameUser)) {
				result = user;
				break;
			}
		}
		return result;
	}

}
