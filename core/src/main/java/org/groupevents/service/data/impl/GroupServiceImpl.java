package org.groupevents.service.data.impl;

import java.util.Optional;

import org.groupevents.domain.Group;
import org.groupevents.dto.GroupDTO;
import org.groupevents.repository.GroupRepository;
import org.groupevents.service.data.GroupService;
import org.groupevents.service.exceptions.CreationFailedException;
import org.groupevents.service.exceptions.FieldException;
import org.groupevents.service.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	GroupRepository groupRepository;
	@Override
	public GroupDTO createGroup(GroupDTO groupDTO) throws CreationFailedException {
		Group group= mapFromDTO(groupDTO);
		if(fieldValueExists(group.getName(),"name")) {
			throw new FieldException("Name exisitiert bereits","name");
		}
		if(fieldValueExists(group.getUrl(),"url")) {
			throw new FieldException("URL exisitiert bereits","url");
		}
		groupRepository.save(group);
		groupDTO.setId(group.getId());
		return groupDTO;
	}
	
	@Override
	public GroupDTO findGroupById(Long id) {
		Optional<Group> group = groupRepository.findById(id);
		if(!group.isPresent()) {
			throw new NotFoundException("Gruppe nicht gefunden");
		}
		return mapToDTO(group.get());
	}
	
	private GroupDTO mapToDTO(Group group) {
		return modelMapper.map(group, GroupDTO.class);
	}

	private Group mapFromDTO(GroupDTO groupDTO) {
		Group group = modelMapper.map(groupDTO, Group.class);
		return group;
	}

	@Override
	public boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException {
		if(fieldName.equals("name")) {
			return groupRepository.findByName(value.toString()).isPresent();
		}
		if(fieldName.equals("url")) {
			return groupRepository.findByUrl(value.toString()).isPresent();
		}
		throw new UnsupportedOperationException("can't get value for field "+fieldName);
	}

	

}
