package org.groupevents.service.data;

import org.groupevents.dto.GroupDTO;
import org.groupevents.service.exceptions.CreationFailedException;
import org.groupevents.validators.FieldValueExists;

public interface GroupService extends FieldValueExists {
  GroupDTO createGroup(GroupDTO groupDTO) ;
  GroupDTO findGroupById(Long id);
  
}
