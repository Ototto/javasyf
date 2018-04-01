package pl.mateuszmacholl.Repositories.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mateuszmacholl.Models.User.User;

/**
 * Created by ggere on 27.03.2017.
 */
@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    User findByUsername(String u);
    User findByEmail(String e);
}

