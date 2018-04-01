package pl.mateuszmacholl.Services.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mateuszmacholl.Models.Post.Post;
import pl.mateuszmacholl.Repositories.Post.PostRepo;

import java.util.List;
import java.util.Optional;

/**
 * Created by ggere on 21.03.2018.
 */
@Service
public class PostService {
	private final PostRepo postRepo;

	@Autowired
	public PostService(PostRepo postRepo) {
		this.postRepo = postRepo;
	}

	
	public Optional<Post> findById(Integer id) {
		return postRepo.findById(id);
	}

	
	public List<Post> findAll() {
		return (List<Post>) postRepo.findAll();
	}

	
	public void add(Post post) {
		postRepo.save(post);
	}

	
	public void delete(Integer id) {
		if(findById(id).isPresent())
		postRepo.delete(findById(id).get());
	}

	
	public List<Post> findAllByOrderByDate() {
		return postRepo.findAllByOrderByDate();
	}

}
