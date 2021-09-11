package br.com.wildrimak.springrolejwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wildrimak.springrolejwt.dao.RoleDao;
import br.com.wildrimak.springrolejwt.model.Role;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findByName(String name) {
	Role role = roleDao.findByName(name);
	return role;
    }

}
