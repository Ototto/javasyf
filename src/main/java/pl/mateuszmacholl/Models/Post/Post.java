package pl.mateuszmacholl.Models.Post;

import org.springframework.format.annotation.DateTimeFormat;
import pl.mateuszmacholl.Models.Answer.Answer;
import pl.mateuszmacholl.Models.User.User;

import javax.persistence.*;
import java.util.*;

/**
 * Created by ggere on 21.03.2018.
 */
@Entity
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue
	private Integer id;

	private String title;

	@Column(columnDefinition = "TEXT")
	private String content;

	@DateTimeFormat
	private Calendar date = Calendar.getInstance();

	private Boolean solved = false;

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	private Integer votes = 0;

	@ElementCollection
	private List<String> tags = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "post")
	private Set<Answer> answers = new HashSet<Answer>();

	public Post() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Boolean getSolved() {
		return solved;
	}

	public void setSolved(Boolean solved) {
		this.solved = solved;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}
}
