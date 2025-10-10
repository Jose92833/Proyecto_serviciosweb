package com.veterinary.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.model.SystemUser;

public interface SystemUserRepository extends JpaRepository<SystemUser, Integer> {
    
    @Query(value = "SELECT * FROM systemuser WHERE user_name = :userName", nativeQuery = true)
    Optional<SystemUser> findByUserName(@Param("userName") String userName);
    
    @Query(value = "SELECT u FROM SystemUser u WHERE u.active = true")
    List<SystemUser> findActiveUsers();
    
    @Query(value = "SELECT u FROM SystemUser u WHERE u.role.id = :roleId AND u.active = true")
    List<SystemUser> findByRoleId(@Param("roleId") Integer roleId);
}