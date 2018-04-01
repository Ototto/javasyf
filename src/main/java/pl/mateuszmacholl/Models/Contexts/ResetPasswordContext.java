package pl.mateuszmacholl.Models.Contexts;

import lombok.Getter;
import pl.mateuszmacholl.Services.Validation.EmailValidation.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by ggere on 20.02.2018.
 */
@Getter
public class ResetPasswordContext {
	@NotNull
	@NotEmpty
	@ValidEmail
	String email;
	@NotNull
	@NotEmpty
	String clientResetPasswordUrl;
}
