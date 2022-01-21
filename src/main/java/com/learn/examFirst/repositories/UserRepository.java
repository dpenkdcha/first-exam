package com.scott.betaexam.repositories;

import com.scott.betaexam.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {

    List<Users> findByNameContains(String filterBy);

    List<Users> findByEmailContains(String filterBy);

//    @Query(nativeQuery = true, value = "Select * from employee e where e.name =:name and e.address =:address")
//    List<Users> findByNameAndAddress(@Param(value = "name") String name, @Param(value = "address") String address);

    List<Users> findByNameAndAddress(String name, String address);
}
