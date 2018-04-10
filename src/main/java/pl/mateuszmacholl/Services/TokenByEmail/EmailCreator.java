package pl.mateuszmacholl.Services.TokenByEmail;

import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Created by ggere on 19.02.2018.
 */
@Service
@Scope(value = "prototype")
public class EmailCreator {
	private final MailSender mailSender;
	private String userEmail;
	private String subject;
	private String body;

	protected EmailCreator(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	private SimpleMailMessage createEmail(){
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(subject);
		email.setText(body);
		email.setTo(userEmail);
		return email;
	}

	public void sendEmail(){
		mailSender.send(createEmail());
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
