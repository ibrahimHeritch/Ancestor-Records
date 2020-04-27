package com.uncc.ticket.repository;


import com.uncc.ticket.model.PersonEntity;
import com.uncc.ticket.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

}