package com.veterinary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import com.veterinary.repository.SystemUserRepository;
import com.veterinary.model.SystemUser;

@Service
@Transactional
public class SystemUserService {
    @Autowired
    private SystemUserRepository repository;

    public List<SystemUser> getAll() {
        return repository.findActiveUsers();
    }

    public SystemUser save(SystemUser systemUser) {
        return repository.save(systemUser);
    }

    public SystemUser getById(Integer id) {
        return repository.findById(id).get();
    }

    public void deactivate(Integer id) {
        SystemUser systemUser = repository.findById(id).get();
        systemUser.setActive(false);
        repository.save(systemUser);
    }

    public Optional<SystemUser> findByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    public List<SystemUser> findByRoleId(Integer roleId) {
        return repository.findByRoleId(roleId);
    }
}