package org.groupevents.domain;

import java.io.InputStream;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity(name = "binarydata")
public class BinaryData extends BaseEntity {
	
	private String name;
	private String mimeType;
	@Lob
	private InputStream content;
}
