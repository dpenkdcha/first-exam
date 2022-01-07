package com.scott.betaexam.repositories;

import com.scott.betaexam.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {

    List<Users> findByNameContains(String filterBy);

    List<Users> findByEmailContains(String filterBy);
}
