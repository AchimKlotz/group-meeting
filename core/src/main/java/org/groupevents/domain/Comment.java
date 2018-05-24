package org.groupevents.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Size;

import lombok.Data;
@Entity
@Data
public class Comment extends BaseEntity {

	private static final long serialVersionUID = 2879626145055213061L;

	@Size(max=3000)
	private String text;
	
	@ManyToOne
	@JoinColumn(name="fk_parent_comment")
	private Comment parentComment;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="parentComment")
	@OrderBy(value="created")
	private List<Comment> childComments;
	
}
