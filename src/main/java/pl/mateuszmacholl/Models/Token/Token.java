package pl.mateuszmacholl.Models.Token;

import pl.mateuszmacholl.Models.User.User;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ggere on 31.03.2018.
 */

@Entity
@Inheritance
public abstract class Token {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "token")
	private String token;

	@Column(name = "expirationDate")
	private Date expirationDate = setExpirationDate();

	@OneToOne(fetch = FetchType.LAZY)
	private User user;

	protected Token() {
	}

	private Date setExpirationDate(){
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 180);
		return this.expirationDate = now.getTime();
	}

	public boolean isExpired() {
		return new Date().after(this.expirationDate);
	}

	public Token(String token, User user){
		this.token = token;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
