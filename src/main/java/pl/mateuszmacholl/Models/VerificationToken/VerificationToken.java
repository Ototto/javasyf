package pl.mateuszmacholl.Models.VerificationToken;

import pl.mateuszmacholl.Models.Token.Token;
import pl.mateuszmacholl.Models.User.User;

import javax.persistence.Entity;

/**
 * Created by ggere on 13.09.2017.
 */
@Entity
public class VerificationToken  extends Token{
	public VerificationToken(String token, User user) {
		super(token, user);
	}

	public VerificationToken() {
	}
}