package com.javaee.project.project3.service;

import com.javaee.project.project3.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;
}
