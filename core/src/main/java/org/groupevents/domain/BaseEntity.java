package org.groupevents.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable{
	
	
	private static final long serialVersionUID = -8966732509860345634L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",nullable=false,columnDefinition="BIGINT")
	Long id;
	
	@CreatedDate
	LocalDateTime created;
	@LastModifiedDate
	LocalDateTime changed;
	@CreatedBy
	String createdBy;
	@LastModifiedBy
	String modifiedBy;
	
		
	
	public BaseEntity() {
		
	}
	

}
