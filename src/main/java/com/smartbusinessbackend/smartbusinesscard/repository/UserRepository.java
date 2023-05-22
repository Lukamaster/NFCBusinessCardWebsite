package com.smartbusinessbackend.smartbusinesscard.repository;

import com.smartbusinessbackend.smartbusinesscard.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByEmail(String email);
}
