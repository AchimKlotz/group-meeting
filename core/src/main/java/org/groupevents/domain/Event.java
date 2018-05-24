package org.groupevents.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.groupevents.domain.enums.Repetition;

import lombok.Data;

@Data
@Entity(name="event")
public class Event extends BaseEntity {
	@NotNull
	private LocalDateTime startTime;
	@Size(max = 100)
	@NotNull
	private String title;
	@Size(max = 100)
	@NotNull
	private String url;
	@Size(max = 10000)
	@NotNull
	private String description;
	
	private Integer maxMember;
	
	private boolean allowMaybe;
	
	private LocalDateTime cancelTime;
	@Size(max=255)
	private String cancelReason;
	
	@ManyToMany
	@OrderColumn(name="attachment_idx")
	private List<BinaryData> attachments;
	private Duration duration;
    @Enumerated(EnumType.STRING)
	private Repetition repetition;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="fk_group")
	@NotNull
	private Group group;
	
	@ManyToMany
	@JoinTable(name="event_members",
	   joinColumns=@JoinColumn(name="event_id"),
	   inverseJoinColumns=@JoinColumn(name="member_id"))
	@OrderColumn(name="member_idx")
	private List<User> members;	
	
	@ManyToMany
	@JoinTable(name="event_waiting")
	@OrderColumn(name="member_idx")
	private List<User> waitingMembers;
	
	@ManyToMany
	@JoinTable(name="event_canceled",
	   joinColumns=@JoinColumn(name="event_id"),
	   inverseJoinColumns=@JoinColumn(name="member_id"))
	@OrderColumn(name="member_idx")
	private List<User> canceledMembers;
	
	@ManyToMany
	@JoinTable(name="event_maybe",
			   joinColumns=@JoinColumn(name="event_id"),
			   inverseJoinColumns=@JoinColumn(name="member_id"))
	@OrderColumn(name="member_idx")
	private List<User> maybeMembers;

	private String locationName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_address")
	private Address address;
	@Column(name = "showAddressMemberOnly")
	private boolean showAddressOnlyGroupMembers;
	
	@ManyToMany
	@JoinTable(name="event_comments")
	@OrderBy(value="created")
	private List<Comment> comments;

}
