package pl.mateuszmacholl.Models.Contexts;

import lombok.Getter;
import pl.mateuszmacholl.Models.User.User;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by ggere on 20.02.2018.
 */
@Getter
public class RegistrationAccountContext {
	@Valid
	User user;
	@NotNull
	@NotEmpty
	String clientVerifyAccountUrl;
}
