package pl.mateuszmacholl.Models.Contexts;

import lombok.Getter;
import pl.mateuszmacholl.Services.Validation.EmailValidation.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by ggere on 27.02.2018.
 */
@Getter
public class VerifyAccountContext {
	@NotNull
	@NotEmpty
	@ValidEmail
	String email;
	@NotNull
	@NotEmpty
	String clientVerifyAccountUrl;
}
