package com.example.miroslavfacebookproject.repository;
import com.example.miroslavfacebookproject.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository <Roles, Long>{
}
