package pl.mateuszmacholl.Controllers;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.mateuszmacholl.Events.OnSendEmailWithTokenListener;
import pl.mateuszmacholl.Models.Contexts.ChangePasswordContext;
import pl.mateuszmacholl.Models.Contexts.RegistrationAccountContext;
import pl.mateuszmacholl.Models.Contexts.ResetPasswordContext;
import pl.mateuszmacholl.Models.Contexts.VerifyAccountContext;
import pl.mateuszmacholl.Models.PasswordResetToken.PasswordResetToken;
import pl.mateuszmacholl.Models.User.User;
import pl.mateuszmacholl.Models.VerificationToken.VerificationToken;
import pl.mateuszmacholl.Services.TokenByEmail.TypeOfToken;
import pl.mateuszmacholl.Services.User.UserService;
import pl.mateuszmacholl.Services.Validation.ValidationErrorBuilder;

import javax.validation.Valid;

/**
 * Created by ggere on 20.02.2018.
 */
@RestController
@RequestMapping(value = "/account")
public class Account {
	private final UserService userService;
	private final ApplicationEventPublisher eventPublisher;
	private final BCryptPasswordEncoder passwordEncoder;

	public Account(UserService userService, ApplicationEventPublisher eventPublisher, BCryptPasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.eventPublisher = eventPublisher;
		this.passwordEncoder = passwordEncoder;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity registration(@RequestBody @Valid RegistrationAccountContext registrationAccountContext, Errors errors) {

		if (errors.hasErrors()) {
			return new ResponseEntity<>(ValidationErrorBuilder.fromBindingErrors(errors), HttpStatus.BAD_REQUEST);
		}

		User userExist = userService.findByEmail(registrationAccountContext.getUser().getEmail());

		if (userExist != null) {
			return new ResponseEntity<>("Account with this email already exist", HttpStatus.CONFLICT);
		} else if (errors.hasErrors()) {
			return new ResponseEntity<>(ValidationErrorBuilder.fromBindingErrors(errors), HttpStatus.BAD_REQUEST);
		} else {
			User registered = userService.registerNewUserAccount(registrationAccountContext.getUser());
			new Thread(() -> eventPublisher.publishEvent(new OnSendEmailWithTokenListener
					(registered, registrationAccountContext.getClientVerifyAccountUrl(), TypeOfToken.AccountVerificationToken))).start();
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/verify-account", method = RequestMethod.GET)
	public ResponseEntity verifyRegistration(@RequestParam(value = "token") String token) {

		VerificationToken verificationToken = userService.getVerificationToken(token);
		if (verificationToken == null) {
			return new ResponseEntity<>("Token is invalid", HttpStatus.BAD_REQUEST);
		} else if (verificationToken.isExpired()){
			return new ResponseEntity<>("Token has expired", HttpStatus.BAD_REQUEST);
		}

		User user = verificationToken.getUser();
		user.setEnabled(true);
		userService.saveRegisteredUser(user);
		userService.deleteVerificationToken(token);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/resend-verification-token", method = RequestMethod.POST)
	public ResponseEntity resendVerificationToken(@RequestBody @Valid VerifyAccountContext verifyAccountContext, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<>(ValidationErrorBuilder.fromBindingErrors(errors), HttpStatus.BAD_REQUEST);
		} else {
			new Thread(() -> eventPublisher.publishEvent(new OnSendEmailWithTokenListener
					(userService.findByEmail(verifyAccountContext.getEmail()), verifyAccountContext.getClientVerifyAccountUrl(), TypeOfToken.AccountVerificationToken))).start();
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/reset-password", method = RequestMethod.POST)
	public ResponseEntity resetPassword(@RequestBody @Valid ResetPasswordContext resetPasswordContext, Errors errors) {

		if (errors.hasErrors()) {
			return new ResponseEntity<>(ValidationErrorBuilder.fromBindingErrors(errors), HttpStatus.BAD_REQUEST);
		} else {
			User user = userService.findByEmail(resetPasswordContext.getEmail());
			if (user != null) {
				new Thread(() -> eventPublisher.publishEvent(new OnSendEmailWithTokenListener(user, resetPasswordContext.getClientResetPasswordUrl(), TypeOfToken.PasswordResetToken))).start();
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	public ResponseEntity resetPasswordCompleted(@RequestBody @Valid ChangePasswordContext changePasswordContext,
	                                             @RequestParam("token") String token,
	                                             Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<>(ValidationErrorBuilder.fromBindingErrors(errors), HttpStatus.BAD_REQUEST);
		} else {
			PasswordResetToken passwordResetToken = userService.getPasswordResetToken(token);
			if (passwordResetToken == null) {
				return new ResponseEntity<>("Token is invalid", HttpStatus.BAD_REQUEST);
			} else if (passwordResetToken.isExpired()){
				return new ResponseEntity<>("Token has expired", HttpStatus.BAD_REQUEST);
			}

			User user = passwordResetToken.getUser();
			user.setPassword(passwordEncoder.encode(changePasswordContext.getNewPassword()));
			userService.saveRegisteredUser(user);
			userService.deletePasswordResetToken(token);
			return ResponseEntity.ok().build();
		}
	}
}
