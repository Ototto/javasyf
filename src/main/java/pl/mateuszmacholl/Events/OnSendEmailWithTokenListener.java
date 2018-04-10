package pl.mateuszmacholl.Events;

import org.springframework.context.ApplicationEvent;
import pl.mateuszmacholl.Models.User.User;
import pl.mateuszmacholl.Services.TokenByEmail.TypeOfToken;

/**
 * Created by ggere on 20.02.2018.
 */

@SuppressWarnings("serial")
public class OnSendEmailWithTokenListener extends ApplicationEvent {
	private String clientUrl;
	private User user;
	private TypeOfToken typeOfToken;

	public OnSendEmailWithTokenListener(
			User user, String clientUrl, TypeOfToken typeOfToken) {
		super(user);

		this.user = user;
		this.clientUrl = clientUrl;
		this.typeOfToken = typeOfToken;
	}

	public String getClientUrl() {
		return clientUrl;
	}

	public void setClientUrl(String clientUrl) {
		this.clientUrl = clientUrl;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TypeOfToken getTypeOfToken() {
		return typeOfToken;
	}

	public void setTypeOfToken(TypeOfToken typeOfToken) {
		this.typeOfToken = typeOfToken;
	}
}
