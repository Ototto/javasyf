package pl.mateuszmacholl.DTO.User.Account;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RegistrationAccountDto {
	@Valid
	@NotNull
	private RegistrationUserDto registrationUserDto;
	@NotNull
	@NotEmpty
	private String clientVerifyAccountUrl;

	public RegistrationUserDto getRegistrationUserDto() {
		return registrationUserDto;
	}

	public void setRegistrationUserDto(RegistrationUserDto registrationUserDto) {
		this.registrationUserDto = registrationUserDto;
	}

	public String getClientVerifyAccountUrl() {
		return clientVerifyAccountUrl;
	}

	public void setClientVerifyAccountUrl(String clientVerifyAccountUrl) {
		this.clientVerifyAccountUrl = clientVerifyAccountUrl;
	}
}
