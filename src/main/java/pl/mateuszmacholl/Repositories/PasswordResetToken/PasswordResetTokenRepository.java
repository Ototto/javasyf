package pl.mateuszmacholl.Repositories.PasswordResetToken;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateuszmacholl.Models.PasswordResetToken.PasswordResetToken;

/**
 * Created by ggere on 19.09.2017.
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

	PasswordResetToken findByToken(String token);
}
