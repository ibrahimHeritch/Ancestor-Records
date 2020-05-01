package com.uncc.ticket.repository;


import com.uncc.ticket.EmbededId.RelationId;
import com.uncc.ticket.model.PersonEntity;
import com.uncc.ticket.model.RelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelationRepository extends JpaRepository<RelationEntity, RelationId> {

}
