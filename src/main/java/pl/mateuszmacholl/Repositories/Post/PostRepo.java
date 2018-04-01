package pl.mateuszmacholl.Repositories.Post;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mateuszmacholl.Models.Post.Post;

import java.util.List;

/**
 * Created by ggere on 21.03.2018.
 */
@Repository
public interface PostRepo extends CrudRepository<Post, Integer> {
	 List<Post> findAllByOrderByDate();
}
