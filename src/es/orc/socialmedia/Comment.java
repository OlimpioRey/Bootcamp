package es.orc.socialmedia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Comment {

	private String text;
	private LocalDate date;
	private User author;
	
	List<Comment> comentsList = new ArrayList<>();
	
	public Comment(String textComment, LocalDate dateComment, User authorComment) {
		this.text = textComment;
		this.date = dateComment;
		this.author = authorComment;
	}
	
	public void setCommentlist(List<Comment> commentList) {
		this.comentsList = commentList;
	}
	
	public User getAuthor() {
		return this.author;
	}
	
	public String getDetails() {
		return (this.text + " (" + this.author.getName() + ") [" + 
				this.date.getDayOfMonth() + "/" + 
				this.date.getMonthValue() + "/" +
				this.date.getYear() + "]");
	}
}
