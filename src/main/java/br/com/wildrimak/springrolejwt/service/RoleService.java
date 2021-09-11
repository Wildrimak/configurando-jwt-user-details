package br.com.wildrimak.springrolejwt.service;

import br.com.wildrimak.springrolejwt.model.Role;

public interface RoleService {
    Role findByName(String name);
}
