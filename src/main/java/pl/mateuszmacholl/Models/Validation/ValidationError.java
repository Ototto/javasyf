package pl.mateuszmacholl.Models.Validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ggere on 04.01.2018.
 */
@Getter
public class ValidationError {

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<String> errors = new ArrayList<>();

	private final String errorMessage;

	public ValidationError(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void addValidationError(String error) {
		errors.add(error);
	}
}
