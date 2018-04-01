package pl.mateuszmacholl.Services.Validation;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import pl.mateuszmacholl.Models.Validation.ValidationError;

/**
 * Created by ggere on 04.01.2018.
 */
@Service
public class ValidationErrorBuilder {

	public static ValidationError fromBindingErrors(Errors errors) {
		ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");
		for (ObjectError objectError : errors.getAllErrors()) {
			FieldError fieldError = (FieldError) objectError;
			error.addValidationError(fieldError.getField() + " : " + objectError.getDefaultMessage());
		}
		return error;
	}
}
