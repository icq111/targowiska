package pl.minicode.targowiska.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.minicode.targowiska.domain.CustomUserDetails;
import pl.minicode.targowiska.domain.User;
import pl.minicode.targowiska.repository.UserRepository;


@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//		Optional<User> user = userRepository.findByUsername(userName);
//		
//		user.orElseThrow(() -> new UsernameNotFoundException("User not found" + userName));
//		
//		return user.map(CustomUserDetails::new).get();		
		return new CustomUserDetails(getMockedAdmin());
	}
	
	private User getMockedAdmin() {
		User usr = new User();
		usr.setActive(true);
		usr.setEmail("mail@mm.pl");
		usr.setId(1l);
		usr.setLastName("Admin Second Name");
		usr.setName("Admin name");
		usr.setPassword("pass");
		usr.setRole("ROLE_ADMIN");
		usr.setUsername("admin");
		return usr;
	}

}
