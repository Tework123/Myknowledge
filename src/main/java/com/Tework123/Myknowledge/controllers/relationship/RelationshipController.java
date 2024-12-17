package com.Tework123.Myknowledge.controllers.relationship;


import com.Tework123.Myknowledge.dtos.ResponseDto;
import com.Tework123.Myknowledge.dtos.relationship.RelationshipCreateDto;
import com.Tework123.Myknowledge.dtos.relationship.RelationshipGetDto;
import com.Tework123.Myknowledge.entities.Relationship;
import com.Tework123.Myknowledge.entities.User;
import com.Tework123.Myknowledge.repositories.RelationshipRepository;
import com.Tework123.Myknowledge.repositories.UserRepository;
import com.Tework123.Myknowledge.services.RelationshipService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@AllArgsConstructor
public class RelationshipController {
    private UserRepository userRepository;
    private RelationshipService relationshipService;
    private RelationshipRepository relationshipRepository;


    @GetMapping("/relationship")
    public List<RelationshipGetDto> getRelationships(Authentication auth) {
        User currentUser = userRepository.findByUsername(auth.getName());
        List<Relationship> relationshipList = relationshipService.getRelationship(currentUser);

        return RelationshipGetDto.toListRelationship(relationshipList);

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

    @DeleteMapping("/relationship/{id}")
    public ResponseEntity<ResponseDto> deleteRelationship(@PathVariable("id") Long id,
                                                          Authentication auth) {
        User currentUser = userRepository.findByUsername(auth.getName());
        relationshipService.deleteRelationship(id, currentUser);
        return ResponseEntity.ok(ResponseDto.toDto("Relationship deleted"));
    }


}
