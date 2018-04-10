package pl.mateuszmacholl.Services.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mateuszmacholl.Configuration.ModelMapper.MyModelMapper;
import pl.mateuszmacholl.DTO.Post.PostDto;
import pl.mateuszmacholl.Models.Post.Post;
import pl.mateuszmacholl.Repositories.Post.PostRepo;
import pl.mateuszmacholl.Repositories.User.UserRepo;

import java.util.List;
import java.util.Optional;

/**
 * Created by ggere on 21.03.2018.
 */
@Service
public class PostService {
	private final PostRepo postRepo;
	private final UserRepo userRepo;
	private final MyModelMapper myModelMapper;

	@Autowired
	public PostService(PostRepo postRepo, UserRepo userRepo, MyModelMapper myModelMapper) {
		this.postRepo = postRepo;
		this.userRepo = userRepo;
		this.myModelMapper = myModelMapper;
	}

	
	public Optional<Post> findById(Integer id) {
		return postRepo.findById(id);
	}

	
	public List<Post> findAll() {
		return (List<Post>) postRepo.findAll();
	}

	
	public Post add(Post post) {
		return postRepo.save(post);
	}

	
	public void delete(Integer id) {
		if(findById(id).isPresent())
		postRepo.delete(findById(id).get());
	}

	
	public List<Post> findAllByOrderByDate() {
		return postRepo.findAllByOrderByDate();
	}

	public PostDto convertToDto(Post post) {
		PostDto postDto = myModelMapper.modelMapper().map(post, PostDto.class);
		postDto.setUsername(userRepo.findById(post.getUser().getId()).get().getUsername());
		return postDto;
	}

	public Post convertToEntity(PostDto postDto){
		Post post = myModelMapper.modelMapper().map(postDto, Post.class);
		post.setUser(userRepo.findByUsername(postDto.getUsername()));
		return post;
	}
}
