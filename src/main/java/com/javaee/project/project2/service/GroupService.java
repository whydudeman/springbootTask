package com.javaee.project.project2.service;

import com.javaee.project.project2.form.GroupDto;
import com.javaee.project.project2.model.Group;
import com.javaee.project.project2.repository.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepo groupRepo;

    public Group createByDto(GroupDto groupDto) {
        Group group = new Group();
        group.setName(groupDto.getName());
        group.setShortName(groupDto.getShortName());
        return groupRepo.save(group);
    }

    public List<Group> getAll() {
        return groupRepo.findAll();
    }

    public Group getGroupById(Long id) {
        return groupRepo.findById(id).orElseThrow(() -> new RuntimeException("NO_GROUP_WITH_THIS_ID"));
    }

    public Group save(Group group) {
       return groupRepo.save(group);
    }

    public void deleteById(Long id) {
        groupRepo.deleteById(id);
    }
}
