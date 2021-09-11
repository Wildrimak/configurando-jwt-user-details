package br.com.wildrimak.springrolejwt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.wildrimak.springrolejwt.model.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
