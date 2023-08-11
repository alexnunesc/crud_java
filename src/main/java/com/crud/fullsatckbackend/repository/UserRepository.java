package com.crud.fullsatckbackend.repository;

import com.crud.fullsatckbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
