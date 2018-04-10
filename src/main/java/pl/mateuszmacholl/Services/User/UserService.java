package pl.mateuszmacholl.Services.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mateuszmacholl.Configuration.ModelMapper.MyModelMapper;
import pl.mateuszmacholl.DTO.User.Account.RegistrationUserDto;
import pl.mateuszmacholl.DTO.User.User.UserDto;
import pl.mateuszmacholl.Models.PasswordResetToken.PasswordResetToken;
import pl.mateuszmacholl.Models.User.User;
import pl.mateuszmacholl.Models.VerificationToken.VerificationToken;
import pl.mateuszmacholl.Repositories.PasswordResetToken.PasswordResetTokenRepository;
import pl.mateuszmacholl.Repositories.User.UserRepo;
import pl.mateuszmacholl.Repositories.VerificationToken.VerificationTokenRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by ggere on 27.03.2017.
 */
@Service
public class UserService {
    private final UserRepo userRepo;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MyModelMapper myModelMapper;

    @Autowired
    public UserService(UserRepo userRepo, VerificationTokenRepository verificationTokenRepository, PasswordResetTokenRepository passwordResetTokenRepository, BCryptPasswordEncoder passwordEncoder, MyModelMapper myModelMapper) {
        this.userRepo = userRepo;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.myModelMapper = myModelMapper;
    }

    public Optional<User> findById(Integer id) {
        return userRepo.findById(id);
    }


    public List<User> findAll() {
        return (List<User>) userRepo.findAll();
    }

    @Transactional
    public User add(User user){
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

    public UserDto convertToUserDto(User user){
        return myModelMapper.modelMapper().map(user, UserDto.class);
    }

    public User convertFromRegistrationUserDtoToEntity(RegistrationUserDto registrationUserDto){
        User user = myModelMapper.modelMapper().map(registrationUserDto, User.class);
        user.setUsername(user.getEmail().substring(0,(user.getEmail().indexOf("@"))));
        List<String> roles = new ArrayList<>();
        roles.add("user");
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        return user;
    }
}
