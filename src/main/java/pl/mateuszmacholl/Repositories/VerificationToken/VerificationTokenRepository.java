package pl.mateuszmacholl.Repositories.VerificationToken;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateuszmacholl.Models.VerificationToken.VerificationToken;

/**
 * Created by ggere on 14.09.2017.
 */
public interface VerificationTokenRepository
		extends JpaRepository<VerificationToken, Long> {

	VerificationToken findByToken(String token);
}