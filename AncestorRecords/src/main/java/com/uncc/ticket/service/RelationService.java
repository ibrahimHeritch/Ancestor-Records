package com.uncc.ticket.service;

import com.uncc.ticket.EmbededId.RelationId;
import com.uncc.ticket.model.RelationEntity;
import com.uncc.ticket.model.UsersEntity;
import com.uncc.ticket.repository.RelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RelationService {
    private RelationRepository RelationRepository;

    @Autowired
    public RelationService(RelationRepository relationRepository){
        this.RelationRepository=relationRepository;
    }

    @Transactional
    public RelationEntity storeRelation(RelationEntity relation){
        return RelationRepository.save(relation);
    }

    public RelationEntity findById(RelationId id) { return RelationRepository.getOne(id); }

    @Transactional
    public void deleteById(RelationId id) { RelationRepository.deleteById(id); }
}
