package org.groupevents.service.data;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.groupevents.domain.User;
import org.groupevents.dto.UserDTO;
import org.groupevents.dto.UserSignupDTO;
import org.groupevents.repository.UserRepository;
import org.groupevents.service.connectivity.EmailService;
import org.groupevents.service.exceptions.ActivationKeyException;
import org.groupevents.service.exceptions.EMailAlreadyExistsException;
import org.groupevents.service.exceptions.NotFoundException;
import org.groupevents.service.exceptions.ServiceException;
import org.groupevents.service.exceptions.FieldException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	EmailService emailService;

	public UserDTO getUserById(long id) {
		User user = userRepository.findById(id).get();
		return mapToDTO(user);
	}

	public UserDTO getUserByUsername(String userName) {
		return mapToDTO(userRepository.findByUserName(userName).get());
	}

	public UserSignupDTO createUser(UserSignupDTO user) {
		checkUserNameExists(user);
		checkEmailExists(user);
		User userDAO = mapToDAO(user);
		userDAO.setPassword(passwordEncoder.encode(user.getPassword()));
		generateActivationKey(userDAO);
		return user;
	}

	public void updateUser(UserDTO userDTO) {
		User user = userRepository.findById(userDTO.getId()).get();
		modelMapper.map(userDTO, user);
		userRepository.saveAndFlush(user);

	}
	
	

	public void activateUser(String activationKey) {
		Optional<User> userOptional = userRepository.findByActivationKey(activationKey);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			if (user.getActivationKeyValidUntil() != null && 
					user.getActivationKeyValidUntil().isAfter(LocalDateTime.now())) {
				user.setActivationKey(null);
				user.setActivationKeyValidUntil(null);
				user.setVerified(true);
				userRepository.save(user);
			} else {
				throw new NotFoundException("Aktivierungsschlüssel nicht mehr gültig");
			}
		} else {
			throw new NotFoundException("Aktivierungsschlüssel nicht gefunden");
		}
	}
	
	public void requestNewActivationKey(String email) {
		Optional<User> userOptional = userRepository.findByEMailAddress(email);
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			if(user.isVerified()) {
				throw new ServiceException("Benutzer ist bereits aktiviert");				
			}
			if(user.isBlocked()) {
				throw new ServiceException("Benutzer ist blockiert, Aktivierung nicht möglich");
			}
			generateActivationKey(user);
			
		}else {
			throw new ActivationKeyException("Benutzer mit der eMail-Adresse "+email+" wurde nicht gefunden");
		}
		
	}

	private void checkUserNameExists(UserSignupDTO user) {
		if (userRepository.findByUserName(user.getUserName()).isPresent()) {
			throw new FieldException();
		}
	}

	private void checkEmailExists(UserSignupDTO user) {
		if (userRepository.findByEMailAddress(user.getEMailAddress()).isPresent()) {
			throw new EMailAlreadyExistsException();
		}
	}

	private UserDTO mapToDTO(User user) {
		UserDTO res = modelMapper.map(user, UserDTO.class);
		return res;
	}

	private User mapToDAO(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		return user;
	}

	private User mapToDAO(UserSignupDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		return user;

	}
	
	private void generateActivationKey(User user) {
		user.setActivationKey(UUID.randomUUID().toString());
		user.setActivationKeyValidUntil(LocalDateTime.now().plusMonths(1));
		user.setId(userRepository.save(user).getId());
		try {
			emailService.sendSimpleMessage(user.getEMailAddress(), "Benutzer-Account für Group-Meetings wurde erstellt",
					"Hallo " + user.getUserName()
							+ "!\n\nIhr Account wurde erstellt.\nBitte aktivieren Sie Ihre eMail-Adresse, indem Sie auf folgenden Link klicken:\nhttp://localhost:8080/user/activate/"
							+ user.getActivationKey() + "\n\nIhr Group-Meetings System.");
		} catch (Exception e) {

		}
	}

	

}
