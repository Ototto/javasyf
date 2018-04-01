package pl.mateuszmacholl.Events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import pl.mateuszmacholl.Models.User.User;
import pl.mateuszmacholl.Services.TokenByEmail.TypeOfToken;

/**
 * Created by ggere on 20.02.2018.
 */
@Getter
@Setter
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
}
