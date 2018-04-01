package pl.mateuszmacholl.Models.VerificationToken;

import lombok.NoArgsConstructor;
import pl.mateuszmacholl.Models.Token.Token;
import pl.mateuszmacholl.Models.User.User;

import javax.persistence.Entity;

/**
 * Created by ggere on 13.09.2017.
 */
@Entity
@NoArgsConstructor
public class VerificationToken  extends Token{
	public VerificationToken(String token, User user) {
		super(token, user);
	}
}