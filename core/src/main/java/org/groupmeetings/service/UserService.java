package org.groupmeetings.service;

import org.groupmeetings.domain.User;
import org.groupmeetings.dto.UserDTO;
import org.groupmeetings.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public UserDTO getUserById(long id) {
		User user = userRepository.getUserById(id);
		return mapToDTO(user);		
	}
	
	public UserDTO getUserByUsername(String userName) {
		return mapToDTO(userRepository.getUserByUserName(userName));		
	}
	
	public UserDTO saveUser(UserDTO user) {
		return mapToDTO(userRepository.saveUser(mapToDAO(user)));
	}
	
	private UserDTO mapToDTO(User user) {
		UserDTO res = modelMapper.map(user, UserDTO.class);
		return res;
	}
	
	private User mapToDAO(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		return user;
		
	}

}
