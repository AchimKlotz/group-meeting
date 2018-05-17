package org.groupmeetings.domain;

import org.groupmeetings.domain.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor

public class User {
	private long id;
	private String userName;
	private String salutation;
	private String firstName;
	private String lastName;
	private String eMailAddress;
	private String password;
	private Gender gender;
	private Address address;
	private short birthYear;
	private byte birthMonth;
	private byte birthDay;
	private String interests;
	private String website;
	private String publicEMail;

	BinaryData picture;

	
}
