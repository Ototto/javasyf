package pl.mateuszmacholl.DTO.User.Account;

import org.hibernate.validator.constraints.Length;
import pl.mateuszmacholl.Services.Validation.EmailValidation.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RegistrationUserDto {
	private Integer id;

	@NotNull
	@NotEmpty
	@ValidEmail
	private String email;

	@Length(min = 3, message = "Your password must have at least 3 characters")
	@NotNull
	@NotEmpty
	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
