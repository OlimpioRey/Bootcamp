package es.orc.socialmedia;

import java.time.LocalDate;
import java.util.List;

public class ImagePost extends Post{
	
	private String title;
	private int width;
	private int height;
	
	public ImagePost(LocalDate datePost, List<Comment> commentList, String title, int width, int height) {
		super(datePost, commentList);
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	
	@Override
	public String getDetails() {
		return (super.getDate().toString() + " " + this.title + " (" + this.width + "x" + this.height + ")");
	}

}
