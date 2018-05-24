package org.groupevents.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.groupevents.domain.enums.GroupLocation;
import org.groupevents.domain.enums.Visibility;

import lombok.Data;

@Data

public class GroupDTO {

	private static final long serialVersionUID = -4600556083304631298L;
	
	private long id;
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

	private Visibility visibility;

	private GroupLocation groupLocation;
	@Size(max = 100)
	private String locationName;

	@Size(max = 100)
	private String addressStreet;
	@Size(max = 100)
	private String addressZipCode;
	@Size(max = 100)
	private String addressCity;
	@Size(max = 2)
	private String addressCountry;
	private Set<String> category;

}
