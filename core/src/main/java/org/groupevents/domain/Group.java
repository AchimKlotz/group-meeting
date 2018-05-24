package org.groupevents.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.groupevents.domain.enums.GroupLocation;
import org.groupevents.domain.enums.Visibility;
import org.groupevents.service.data.GroupService;
import org.groupevents.validators.Unique;

import lombok.Data;

@Data
@Entity
@Table(name = "`group`")
//@Unique(service = GroupService.class, fields= {"name","url"})
public class Group extends BaseEntity {

	private static final long serialVersionUID = -4600556083304631298L;

	@NotNull
	@Size(min = 5, max = 40)
	private String name;
	@Size(min = 5, max = 40)
	@NotNull
	@Pattern(regexp="^[a-zA-Z-\\d]+$", message="Invalid URL")
	private String url;
	@NotNull
	@Size(max = 100)
	private String motto;
	@Size(max = 255)
	private String topic;
	@Size(max = 10000)
	private String description;
	
	@Enumerated(EnumType.STRING)
	private Visibility visibility;
	
	@Enumerated(EnumType.STRING)
	private GroupLocation groupLocation;
	@Size(max = 100)
	private String locationName;
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "fk_address")
	private Address address;
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="group_category")
	private Set<String> category;
	
	
	@ManyToMany
	@JoinTable(name="group_members",
	   joinColumns=@JoinColumn(name="group_id"),
	   inverseJoinColumns=@JoinColumn(name="member_id"))
	private Set<User> members;
	
	
	@ManyToMany
	private Set<User> administrators;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="group")
	private List<Event> events;
	

	@ManyToOne
	@JoinColumn(name = "fk_picture")
	BinaryData picture;

}
