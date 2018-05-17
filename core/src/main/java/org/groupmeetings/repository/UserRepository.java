package org.groupmeetings.repository;

import org.groupmeetings.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	public User getUserByUserName(String userName) {
		return User.builder().userName("xyz").id(123).build();
	}

	public User getUserById(long id) {
		return User.builder().userName("xyz").id(123).build();
	}

	public User saveUser(User user) {
		user.setId(123);
		return user;
	}

}
