package com.Tework123.Myknowledge.services;

import com.Tework123.Myknowledge.dtos.book.BookCreateDto;
import com.Tework123.Myknowledge.dtos.relationship.RelationshipCreateDto;
import com.Tework123.Myknowledge.entities.Relationship;
import com.Tework123.Myknowledge.entities.User;
import com.Tework123.Myknowledge.entities.enums.Status;
import com.Tework123.Myknowledge.exceptions.customException.CustomException;
import com.Tework123.Myknowledge.repositories.RelationshipRepository;
import com.Tework123.Myknowledge.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RelationshipService {
    private RelationshipRepository relationshipRepository;
    private UserRepository userRepository;


    public String createRelationship(Long id, User currentUser,
                                     RelationshipCreateDto relationshipCreateDto) {
        List<Relationship> relationshipList1 = relationshipRepository
                .findByRequestedRelationshipIdOrReceivedRelationshipId(id, currentUser.getId());
        List<Relationship> relationshipList2 = relationshipRepository
                .findByRequestedRelationshipIdOrReceivedRelationshipId(currentUser.getId(), id);
        if (!relationshipList2.isEmpty() || !relationshipList1.isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Relationship already exist");
        }

        Relationship relationship = new Relationship();
        User receivedUser = userRepository.getById(id);
        relationship.setReceivedRelationship(receivedUser);
        relationship.setRequestedRelationship(currentUser);
        relationship.setStatus(relationshipCreateDto.getStatus());
        relationshipRepository.save(relationship);
        return "Relationship set with " + receivedUser.getUsername();
    }
}
