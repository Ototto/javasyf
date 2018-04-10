package pl.mateuszmacholl.DTO.User.Account;

import pl.mateuszmacholl.Services.Validation.EmailValidation.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ResetPasswordDto {
	@NotNull
	@NotEmpty
	@ValidEmail
	private String email;
	@NotNull
	@NotEmpty
	private String clientResetPasswordUrl;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClientResetPasswordUrl() {
		return clientResetPasswordUrl;
	}

	public void setClientResetPasswordUrl(String clientResetPasswordUrl) {
		this.clientResetPasswordUrl = clientResetPasswordUrl;
	}
}
