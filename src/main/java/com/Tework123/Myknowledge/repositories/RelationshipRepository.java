package com.Tework123.Myknowledge.repositories;

import com.Tework123.Myknowledge.entities.Book;
import com.Tework123.Myknowledge.entities.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelationshipRepository extends JpaRepository<Relationship, Long> {

    List<Relationship> findByRequestedRelationshipIdOrReceivedRelationshipId(Long id_1, Long id_2);

    List<Relationship> findByRequestedRelationshipId(Long id);

    List<Relationship> findByReceivedRelationshipId(Long id);


}
