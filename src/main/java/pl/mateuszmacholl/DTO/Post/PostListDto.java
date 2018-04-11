package pl.mateuszmacholl.DTO.Post;

import java.util.ArrayList;
import java.util.List;

public class PostListDto {
	private Integer id;
	private String title;
	private String shortContent;
	private List<String> tags = new ArrayList<>();
	private String username;
	private Integer createdTimeAgo;
	private Integer numberOfAnswers;
	private Integer votes;

	public PostListDto() {
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

	public String getShortContent() {
		return shortContent;
	}

	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getCreatedTimeAgo() {
		return createdTimeAgo;
	}

	public void setCreatedTimeAgo(Integer createdTimeAgo) {
		this.createdTimeAgo = createdTimeAgo;
	}

	public Integer getNumberOfAnswers() {
		return numberOfAnswers;
	}

	public void setNumberOfAnswers(Integer numberOfAnswers) {
		this.numberOfAnswers = numberOfAnswers;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}
}
