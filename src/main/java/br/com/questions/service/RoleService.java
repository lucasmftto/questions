package br.com.questions.service;

import br.com.questions.entity.Role;

import java.util.List;

public interface RoleService {
    public Role insertRole(Role role);
    public void deleteRole(Integer Id);
    public void updateRole(Integer id, Role newRole);
    public List<Role> findAllRoles();
    public Role findById(Integer id);
}
