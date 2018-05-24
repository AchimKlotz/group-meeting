package org.groupevents.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.groupevents.domain.User;
import org.groupevents.dto.UserDTO;
import org.groupevents.dto.UserSignupDTO;
import org.groupevents.repository.UserRepository;
import org.groupevents.service.data.UserService;
import org.groupevents.service.exceptions.EMailAlreadyExistsException;
import org.groupevents.service.exceptions.FieldException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Before
	public void setup() {
		User doe = User.builder().id((long) 1).userName("john").firstName("John").lastName("Doe")
				.eMailAddress("john.doe@test.com").build();
		User max = User.builder().id((long)2).userName("max").firstName("Max").lastName("Mustermann")
				.eMailAddress("max.mustermann@test.com").build();
		
		Mockito.when(userRepository.findById((long) 1)).thenReturn(Optional.of(doe));
		Mockito.when(userRepository.findByUserName(doe.getUserName())).thenReturn(Optional.of(doe));
		Mockito.when(userRepository.findByEMailAddress(doe.getEMailAddress())).thenReturn(Optional.of(doe));
		Mockito.when(userRepository.save(any(User.class))).thenReturn(max);
	}

	@Test
	public void getUserById() {
		assertEquals(userService.getUserById(1).getUserName(), "john");
	}

	@Test
	public void getUserByUserName() {
		assertEquals(userService.getUserByUsername("john").getId(), 1);
	}
	
	@Test
	public void userCreate() {
		UserSignupDTO max = UserSignupDTO.builder().userName("max").firstName("Max").lastName("Mustermann").password("1234")
				.eMailAddress("max.mustermann@test.com").build();
		assertEquals(userService.createUser(max).getId(),2);
	}

	@Test
	public void userAlreadyExists() {
		UserSignupDTO doe2 = UserSignupDTO.builder().userName("john").firstName("John").lastName("Doe")
				.eMailAddress("john.doe@test.com").build();
		exception.expect(FieldException.class);
		userService.createUser(doe2);
	}

	@Test
	public void emailAlreadyExists() {
		UserSignupDTO doe2 = UserSignupDTO.builder().userName("john2").firstName("John").lastName("Doe")
				.eMailAddress("john.doe@test.com").build();
		exception.expect(EMailAlreadyExistsException.class);
		userService.createUser(doe2);
	}
}