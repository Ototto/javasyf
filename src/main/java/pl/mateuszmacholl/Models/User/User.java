package pl.mateuszmacholl.Models.User;

import pl.mateuszmacholl.Models.PasswordResetToken.PasswordResetToken;
import pl.mateuszmacholl.Models.Post.Post;
import pl.mateuszmacholl.Models.VerificationToken.VerificationToken;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by ggere on 25.03.2017.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String email;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "user")
    private Set<Post> posts = new HashSet<Post>();

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public PasswordResetToken getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(PasswordResetToken passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public VerificationToken getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(VerificationToken verificationToken) {
        this.verificationToken = verificationToken;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
