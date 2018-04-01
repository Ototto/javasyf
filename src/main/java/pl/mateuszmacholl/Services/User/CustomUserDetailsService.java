package pl.mateuszmacholl.Services.User;

/**
 * Created by ggere on 05.09.2017.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mateuszmacholl.Models.User.User;
import pl.mateuszmacholl.Repositories.User.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepo userRepo;

	@Autowired
	public CustomUserDetailsService(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(
					"No user found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User
				(user.getUsername(),
						user.getPassword(), user.getEnabled(), true,
						true, true,
						getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (String role : user.getRoles()) {
			System.out.println("role : " + role);
			authorities.add(new SimpleGrantedAuthority("role_" + role));
		}
		System.out.print("authorities :" + authorities);
		return authorities;
	}
}

