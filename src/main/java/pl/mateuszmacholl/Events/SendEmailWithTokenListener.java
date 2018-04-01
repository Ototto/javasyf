package pl.mateuszmacholl.Events;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.mateuszmacholl.Models.User.User;
import pl.mateuszmacholl.Services.TokenByEmail.ClientUrlWithTokenCreator;
import pl.mateuszmacholl.Services.TokenByEmail.EmailCreator;
import pl.mateuszmacholl.Services.TokenByEmail.TypeOfToken;
import pl.mateuszmacholl.Services.User.UserService;

/**
 * Created by ggere on 20.02.2018.
 */
@Service
@Scope(value = "prototype")
public class SendEmailWithTokenListener implements ApplicationListener<OnSendEmailWithTokenListener> {
	private UserService userService;
	private EmailCreator emailCreator;
	private ClientUrlWithTokenCreator clientUrlWithTokenCreator;
	private User user;
	private TypeOfToken typeOfToken;

	protected SendEmailWithTokenListener(ClientUrlWithTokenCreator clientUrlWithTokenCreator, EmailCreator emailCreator,UserService userService) {
		this.clientUrlWithTokenCreator = clientUrlWithTokenCreator;
		this.emailCreator = emailCreator;
		this.userService = userService;
	}

	private void getDataFromEvent(final OnSendEmailWithTokenListener event) {
		this.user = event.getUser();
		this.typeOfToken = event.getTypeOfToken();
		clientUrlWithTokenCreator.setClientUrl(event.getClientUrl());
	}

	private void executeTypeOfTokenStrategy(){
		emailCreator.setUserEmail(user.getEmail());
		emailCreator.setBody(clientUrlWithTokenCreator.createClientUrlWithToken());
		switch (typeOfToken){
			case PasswordResetToken:
				userService.createPasswordResetToken(user, clientUrlWithTokenCreator.getToken());
				emailCreator.setSubject("Reset Password");
				break;
			case AccountVerificationToken:
				userService.createVerificationToken(user, clientUrlWithTokenCreator.getToken());
				emailCreator.setSubject("Verify your account");
				break;
		}
	}

	@Override
	@Async
	public void onApplicationEvent(final OnSendEmailWithTokenListener event) {
		getDataFromEvent(event);
		executeTypeOfTokenStrategy();
		emailCreator.sendEmail();
	}
}
