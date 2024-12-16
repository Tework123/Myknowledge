package com.Tework123.Myknowledge.controllers.relationship;


import com.Tework123.Myknowledge.dtos.ResponseDto;
import com.Tework123.Myknowledge.dtos.book.BookGetDto;
import com.Tework123.Myknowledge.dtos.relationship.RelationshipCreateDto;
import com.Tework123.Myknowledge.dtos.relationship.RelationshipGetDto;
import com.Tework123.Myknowledge.entities.Book;
import com.Tework123.Myknowledge.entities.Relationship;
import com.Tework123.Myknowledge.entities.User;
import com.Tework123.Myknowledge.entities.enums.Status;
import com.Tework123.Myknowledge.repositories.RelationshipRepository;
import com.Tework123.Myknowledge.repositories.UserRepository;
import com.Tework123.Myknowledge.services.RelationshipService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
//@RequestMapping("/book")
public class RelationshipController {
    private UserRepository userRepository;
    private RelationshipService relationshipService;
    private RelationshipRepository relationshipRepository;


    @GetMapping("/relationship")
    public List<RelationshipGetDto> getRelationships(Authentication auth) {
        User currentUser = userRepository.findByUsername(auth.getName());

        List<Relationship> relationshipsList1 = relationshipRepository
                .findByReceivedRelationshipId(currentUser.getId());
        List<Relationship> relationshipsList2 = relationshipRepository
                .findByRequestedRelationshipId(currentUser.getId());

        for (int i = 0; i < relationshipsList2.size(); i++) {
            relationshipsList2.get(i)
                    .setRequestedRelationship(relationshipsList2
                            .get(i).getReceivedRelationship());

            relationshipsList2.get(i).setReceivedRelationship(currentUser);
        }

        relationshipsList1.addAll(relationshipsList2);
        for (Relationship el : relationshipsList1) {
            System.out.println(el);
        }
        return RelationshipGetDto.toListRelationship(relationshipsList1);

    }

    @PostMapping("/relationship/{id}")
    public ResponseEntity<ResponseDto> createRelationship(@PathVariable("id") Long id,
                                                          @Validated @RequestBody
                                                          RelationshipCreateDto relationshipCreateDto,
                                                          Authentication auth) {
        User currentUser = userRepository.findByUsername(auth.getName());
        String response = relationshipService.createRelationship(id, currentUser, relationshipCreateDto);
        return ResponseEntity.ok(ResponseDto.toDto(response));

    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ResponseDto> deleteBook(@PathVariable("id") Long id,
//                                                  Authentication auth) {
//        User currentUser = userRepository.findByUsername(auth.getName());
//        bookService.deleteBook(id, currentUser);
//        return ResponseEntity.ok(ResponseDto.toDto("Book deleted"));
//    }


}
