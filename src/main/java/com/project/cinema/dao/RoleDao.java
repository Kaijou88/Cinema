package com.project.cinema.dao;

import com.project.cinema.model.Role;

public interface RoleDao extends GenericDao<Role> {
    Role getRoleByName(String roleName);
}
