package com.example.miroslavfacebookproject.repository;
import com.example.miroslavfacebookproject.entity.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRolesRepository extends JpaRepository <UsersRoles, Long>{
}
