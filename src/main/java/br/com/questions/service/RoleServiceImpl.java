package br.com.questions.service;

import br.com.questions.entity.Role;
import br.com.questions.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RoleServiceImpl implements  RoleService{

    @Autowired
    private RoleRepository roleRepository;

    Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Override
    public Role insertRole(Role role) {
        logger.info("Inseringo objeto Role: " + role.toString());
        return this.roleRepository.save(role);
    }

    @Override
    public void deleteRole(Integer id) {
        this.roleRepository.findById(id).map(
                role -> {
                    logger.info("Deletanto objeto Role: " + role.toString());
                    this.roleRepository.delete(role);
                    return Void.TYPE;
                }
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role não encontrada"));
    }

    @Override
    public void updateRole(Integer id, Role newRole) {
        logger.info("Atualizando objeto Role: " + newRole.toString());
        this.roleRepository.findById(id).map(
                role -> {
                    newRole.setId(role.getId());
                    roleRepository.save(newRole);
                    return Void.TYPE;
                }
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role não encontrada"));
    }

    @Override
    public List<Role> findAllRoles() {
        return this.roleRepository.findAll();
    }
    @Override
    public Role findById(Integer id) {
        logger.info("Pesquisando objeto Role: " + id);
        return this.roleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role não encontrada"));
    }
}
