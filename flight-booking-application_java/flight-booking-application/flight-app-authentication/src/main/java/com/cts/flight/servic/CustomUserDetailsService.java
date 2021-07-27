package com.cts.flight.servic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cts.admin.modal.User;
import com.cts.admin.modal.UserRequest;
import com.cts.admin.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	public final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
	UserRepository userRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);
		if (user == null) {
			throw new BadCredentialsException("Invalid user name and password");
		} else {
			return new CustomUser(user);
		}
	}

	public boolean register(UserRequest request) throws Exception {
		logger.info("user registration request {} ", request);
		boolean isAdded = false;
		User findByEmail = userRepo.findByEmail(request.getUsername());
		if (findByEmail == null) {
			User user = new User();
			user.setEmail(request.getUsername());
			user.setIsAdmin("N");
			user.setMobileNumber(request.getMobileNumber());
			user.setName(request.getFirstName() + "" + request.getLastName());
			user.setPassword(passwordEncoder.encode(request.getPassword()));
			User save = userRepo.save(user);
			isAdded = true;
		} else {
			throw new RuntimeException("User is already register,Please Try to login");
		}

		return isAdded;
	}

}
