package pl.mateuszmacholl.Models.Contexts;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by ggere on 20.02.2018.
 */
@Getter
public class ChangePasswordContext {
	@NotNull
	@NotEmpty
	String newPassword;
}
