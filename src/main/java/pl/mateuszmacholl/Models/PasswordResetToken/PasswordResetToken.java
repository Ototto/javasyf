package pl.mateuszmacholl.Models.PasswordResetToken;

import lombok.NoArgsConstructor;
import pl.mateuszmacholl.Models.Token.Token;
import pl.mateuszmacholl.Models.User.User;

import javax.persistence.Entity;


/**
 * Created by ggere on 19.09.2017.
 */
@Entity
@NoArgsConstructor
public class PasswordResetToken extends Token {
	public PasswordResetToken(String token, User user){
		super(token, user);
	}
}
