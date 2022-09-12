package es.orc.socialmedia;

import java.time.LocalDate;
import java.util.List;

public class TextPost extends Post {

	private String contents;
	
	public TextPost(LocalDate datePost, List<Comment> commentList, String contents) {
		super(datePost, commentList);
		this.contents = contents;
	}
	
	@Override
	public String getDetails() {
		return (super.getDate().toString() + " " + this.contents);
	}
}
