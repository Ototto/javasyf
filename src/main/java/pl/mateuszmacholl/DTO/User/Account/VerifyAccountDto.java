package pl.mateuszmacholl.DTO.User.Account;

import pl.mateuszmacholl.Services.Validation.EmailValidation.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VerifyAccountDto {
	@NotNull
	@NotEmpty
	@ValidEmail
	private String email;
	@NotNull
	@NotEmpty
	private String clientVerifyAccountUrl;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClientVerifyAccountUrl() {
		return clientVerifyAccountUrl;
	}

	public void setClientVerifyAccountUrl(String clientVerifyAccountUrl) {
		this.clientVerifyAccountUrl = clientVerifyAccountUrl;
	}
}
