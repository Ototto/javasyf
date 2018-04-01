package pl.mateuszmacholl.Services.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mateuszmacholl.Models.PasswordResetToken.PasswordResetToken;
import pl.mateuszmacholl.Models.User.User;
import pl.mateuszmacholl.Models.VerificationToken.VerificationToken;
import pl.mateuszmacholl.Repositories.PasswordResetToken.PasswordResetTokenRepository;
import pl.mateuszmacholl.Repositories.User.UserRepo;
import pl.mateuszmacholl.Repositories.VerificationToken.VerificationTokenRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ggere on 27.03.2017.
 */

@Service
public class UserService {
    private final UserRepo userRepo;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, VerificationTokenRepository verificationTokenRepository, PasswordResetTokenRepository passwordResetTokenRepository, BCryptPasswordEncoder passwordEncoder1) {
        this.userRepo = userRepo;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.passwordEncoder = passwordEncoder1;
    }

    @Transactional
    public User registerNewUserAccount(User account){
        User user = new User();
        user.setPassword(passwordEncoder.encode(account.getPassword()));
        user.setEmail(account.getEmail());
        user.setUsername(account.getEmail().substring(0,(account.getEmail().indexOf("@"))));
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        user.setRoles(roles);
        return userRepo.save(user);
    }

    public VerificationToken getVerificationToken(String verificationToken) {
        return verificationTokenRepository.findByToken(verificationToken);
    }

    public PasswordResetToken getPasswordResetToken(String passwordResetToken) {
        return passwordResetTokenRepository.findByToken(passwordResetToken);
    }

    public User findByEmail(String mail) {
        return userRepo.findByEmail(mail);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public void saveRegisteredUser(User user) {
        userRepo.save(user);
    }

    public void createVerificationToken(User user, String token) {
        if(user.getVerificationToken() != null){
            deleteVerificationToken(user.getVerificationToken().getToken());
        }
        VerificationToken myToken = new VerificationToken(token, user);
        verificationTokenRepository.save(myToken);
    }

    public void createPasswordResetToken(User user, String token) {
        if(user.getPasswordResetToken() != null){
            deletePasswordResetToken(user.getPasswordResetToken().getToken());
        }
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
    }

    public void deleteVerificationToken(String token) {
        verificationTokenRepository.delete(getVerificationToken(token));
    }

    public void deletePasswordResetToken(String token) {
        passwordResetTokenRepository.delete(getPasswordResetToken(token));
    }

}
