package com.project.cinema.service;

import com.project.cinema.model.Role;

public interface RoleService {
    Role add(Role role);

    Role getRoleByName(String roleName);
}
