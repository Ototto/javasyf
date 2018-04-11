package pl.mateuszmacholl.DTO.Post;

import java.util.ArrayList;
import java.util.List;

public class PostListDto {
	private Integer id;
	private String title;
	private String content;
	private List<String> tags = new ArrayList<>();
	private String username;
	private String createdTimeAgo;
}
