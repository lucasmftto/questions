package br.com.questions.service;

import br.com.questions.entity.Role;

import java.util.List;

public interface RoleService {
    Role insertRole(Role role);
    void deleteRole(Long Id);
    void updateRole(Long id, Role newRole);
    List<Role> findAllRoles();
    Role findById(Long id);
}
