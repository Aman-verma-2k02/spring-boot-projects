package com.project.spring_boot.UserAuthenticationSystem.repo;

import com.project.spring_boot.UserAuthenticationSystem.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserInfo, Integer> {

    //this one returns the object of UserInfo, it can't return the username directly, findBy always the entity object
    UserInfo findByUserCred_Username(String username);

    @Query("SELECT u.firstName FROM UserInfo u WHERE u.userCred.username = :username")
    String findFirstNameByUserName(@Param("username") String name);
}
