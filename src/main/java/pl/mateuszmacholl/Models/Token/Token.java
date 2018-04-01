package pl.mateuszmacholl.Models.Token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mateuszmacholl.Models.User.User;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ggere on 31.03.2018.
 */

@Entity
@Inheritance
@Getter
@Setter
@NoArgsConstructor
public abstract class Token {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "token")
	private String token;

	@Column(name = "expirationDate")
	private Date expirationDate = setExpirationDate(180);

	@OneToOne(fetch = FetchType.LAZY)
	private User user;

	private Date setExpirationDate(int minutes){
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, minutes);
		return this.expirationDate = now.getTime();
	}

	public boolean isExpired() {
		return new Date().after(this.expirationDate);
	}

	public Token(String token, User user){
		this.token = token;
		this.user = user;
	}
}
