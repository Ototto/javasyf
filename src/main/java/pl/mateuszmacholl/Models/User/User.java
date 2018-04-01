package pl.mateuszmacholl.Models.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import pl.mateuszmacholl.Models.PasswordResetToken.PasswordResetToken;
import pl.mateuszmacholl.Models.VerificationToken.VerificationToken;
import pl.mateuszmacholl.Services.Validation.EmailValidation.ValidEmail;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ggere on 25.03.2017.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    @Length(min = 3, message = "Your password must have at least 3 characters")
    @NotNull
    @NotEmpty
    private String password;

    private Boolean enabled = false;

    @ElementCollection
    private List<String> roles = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,
            mappedBy = "user")
    private PasswordResetToken passwordResetToken;

    @OneToOne(fetch = FetchType.LAZY,
            mappedBy = "user")
    private VerificationToken verificationToken;
}
