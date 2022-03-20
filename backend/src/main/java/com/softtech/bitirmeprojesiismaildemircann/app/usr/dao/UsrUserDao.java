package com.softtech.bitirmeprojesiismaildemircann.app.usr.dao;

import com.softtech.bitirmeprojesiismaildemircann.app.usr.entity.UsrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsrUserDao extends JpaRepository<UsrUser, Long> {

    Boolean existsByUsername(String username);

    UsrUser findByUsername(String username);
}
