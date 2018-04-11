package pl.mateuszmacholl.Models.Answer;

import pl.mateuszmacholl.Models.Post.Post;
import pl.mateuszmacholl.Models.User.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by ggere on 21.03.2018.
 */
@Entity
@Table(name = "answer")
public class Answer {
	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@NotEmpty
	@Lob
	private String content;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User author;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "post_id")
	private Post post;

	public Answer() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
