package pl.mateuszmacholl.DTO.User.Account;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ChangePasswordDto {
	@NotNull
	@NotEmpty
	private String newPassword;

	public String getNewPassword() {
		return newPassword;
	}
}
