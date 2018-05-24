package org.groupevents.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;

import org.groupevents.domain.enums.Gender;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {	
	private static final long serialVersionUID = 3368172182726943322L;
	@NotNull
	@Column(unique = true)
	private String userName;
	private String salutation;
	private String firstName;
	private String lastName;
	@Column(name = "email", unique = true)
	private String eMailAddress;
	private String password;
	private Gender gender;
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "fk_address")
	private Address address;
	private short birthYear;
	private byte birthMonth;
	private byte birthDay;
	private String interests;
	private String website;
	private String publicEMail;

	private boolean blocked;
	
	private boolean verified;	
	private String activationKey;
	private LocalDateTime activationKeyValidUntil;

	@ManyToOne
	@JoinColumn(name = "fk_picture")
	BinaryData picture;
	
	@ManyToMany
	@JoinTable(name="group_members"	,
	joinColumns=@JoinColumn(name="member_id"),
	inverseJoinColumns=@JoinColumn(name="group_id")			)
	Set<Group> groups;
	
	@ManyToMany
	@JoinTable(name="group_administrators"	,
	joinColumns=@JoinColumn(name="adminstrators_id"),
	inverseJoinColumns=@JoinColumn(name="group_id")	)		
	
	Set<Group> adminForGroups;
	
	@ManyToMany
	@JoinTable(name="event_members",
	joinColumns=@JoinColumn(name="member_id"),
	inverseJoinColumns=@JoinColumn(name="event_id"))	
	@OrderBy(value="startTime")
	private List<Event> events;
	
	@ManyToMany
	@JoinTable(name="event_canceled",
	joinColumns=@JoinColumn(name="member_id"),
	inverseJoinColumns=@JoinColumn(name="event_id"))	
	@OrderBy(value="startTime")
	private List<Event> eventsCanceled;
	
	
	@ManyToMany
	@JoinTable(name="event_maybe",
	joinColumns=@JoinColumn(name="member_id"),
	inverseJoinColumns=@JoinColumn(name="event_id"))	
	@OrderBy(value="startTime")
	private List<Event> eventsMayBe;
	
	

	public User() {
		super();
	}

	@Builder
	public User(Long id, LocalDateTime created, LocalDateTime changed, String createdBy, String modifiedBy,
			@NotNull String userName, String salutation, String firstName, String lastName, String eMailAddress,
			String password, Gender gender, Address address, short birthYear, byte birthMonth, byte birthDay,
			String interests, String website, String publicEMail, boolean blocked, boolean verified,
			String activationKey, BinaryData picture) {
		super(id, created, changed, createdBy, modifiedBy);
		this.userName = userName;
		this.salutation = salutation;
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMailAddress = eMailAddress;
		this.password = password;
		this.gender = gender;
		this.address = address;
		this.birthYear = birthYear;
		this.birthMonth = birthMonth;
		this.birthDay = birthDay;
		this.interests = interests;
		this.website = website;
		this.publicEMail = publicEMail;
		this.blocked = blocked;
		this.verified = verified;
		this.activationKey = activationKey;
		this.picture = picture;
	}

}
