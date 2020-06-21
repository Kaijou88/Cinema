package com.project.cinema.dao;

import com.project.cinema.model.Role;

public interface RoleDao {
    Role add(Role role);

    Role getRoleByName(String roleName);
}
