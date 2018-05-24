package org.groupevents.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.groupevents.domain.enums.Gender;
import org.groupevents.dto.common.HasPassword;
import org.groupevents.validators.IDNameConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserSignupDTO implements HasPassword{

	private long id;
	@NotBlank
	@Size(max=100)
	@IDNameConstraint
	private String userName;
	@Size(max=100)
	private String firstName;
	@Size(max=100)
	private String lastName;
	@Size(max=100)
	@Email
	private String eMailAddress;
	@NotEmpty
	@Size(min=6,max=30)
	private String password;
	
	private String confirmedPassword;
	@Min(1900)	
	private Short birthYear;
	
	@Max(12)
	@Min(1)
	private Byte birthMonth;
	@Min(1)
	@Max(31)
	private Byte birthDay;
	private Gender gender;
	
	@Size(max=1000)
	private String interests;
	@Size(max=100)
	private String website;
	@Size(max=100)
	@Email
	private String publicEMail;
	@Size(max=100)
	private String addressStreet;
	@Size(max=100)
	private String addressZipCode;
	@Size(max=100)
	private String addressCity;
	@Size(max=2)
	private String addressCountry;
	
	
	public UserSignupDTO() {
		
	}

}
