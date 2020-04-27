package com.uncc.ticket.repository;


import com.uncc.ticket.model.BlogEntity;
import com.uncc.ticket.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
    List<BlogEntity> findAllByOwner(UsersEntity owner);
}
