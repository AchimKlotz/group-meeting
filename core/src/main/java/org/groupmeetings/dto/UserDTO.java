package org.groupmeetings.dto;

import org.groupmeetings.domain.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {

	private long id;
	private String userName;
	private String salutation;
	private String firstName;
	private String lastName;
	private String eMailAddress;
	private String password;
	private String passwordRepeat;
	private Short birthYear;
	private Byte birthMonth;
	private Byte birthDay;
	private Gender gender;
	private String interests;
	private String website;
	private String publicEMail;
	
	private String addressStreet;
	private String addressZipCode;
	private String addressCity;
	private String addressCountry;
	
	
	public UserDTO() {
		
	}

}
