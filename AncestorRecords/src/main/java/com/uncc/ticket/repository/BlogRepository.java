package com.uncc.ticket.repository;


import com.uncc.ticket.model.BlogEntity;
import com.uncc.ticket.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
    List<BlogEntity> findAllByOwner(UsersEntity owner);
    @Query(value = "Select * \n" +
            "FROM blogs \n" +
            "WHERE( \n" +
            "\towners_id=?1\n" +
            "\tor\n" +
            "\towners_id in ( select id\n" +
            "                   from users\n" +
            "\t\t\t\t   where person_id in (select following_id \n" +
            "\t\t\t\t   from persons_following\n" +
            "\t\t\t\t   where person_entity_id = ?2)\n" +
            "\tor \n" +
            "    id in (select blogs_about_me_id\n" +
            "            from blogs_subjects\n" +
            "\t\t\twhere subjects_id=?2\n" +
            "            )\n" +
            "\t)\n" +
            ");", nativeQuery=true )
    List<BlogEntity> getAllBlogsForUser(Long id,Long person_id);
}
