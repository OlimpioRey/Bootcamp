package es.orc.socialmedia;

import java.time.LocalDate;
import java.util.List;

public class VideoPost extends Post {

	private String title;
	private int quality;
	private int duration;
	
	public VideoPost(LocalDate datePost, List<Comment> commentList, String title, int quality, int duration) {
		super(datePost, commentList);
		this.title = title;
		this.quality = quality;
		this.duration = duration;
	}
	
	@Override
	public String getDetails() {
		return (super.getDate().toString() + this.title + " (" + this.quality + "p, " + this.duration + "min.)");
	}
}
