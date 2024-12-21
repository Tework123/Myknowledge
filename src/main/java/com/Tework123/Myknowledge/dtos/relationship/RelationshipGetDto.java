package com.Tework123.Myknowledge.dtos.relationship;


import com.Tework123.Myknowledge.dtos.user.UserGetDto;
import com.Tework123.Myknowledge.entities.Relationship;
import com.Tework123.Myknowledge.entities.enums.Status;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class RelationshipGetDto {
    private LocalDateTime dateCreate;
    private Status status;
    private UserGetDto userGetDto;


    public static RelationshipGetDto toDto(Relationship relationship) {
        RelationshipGetDto relationshipGetDto = new RelationshipGetDto();
        UserGetDto userGetDto = new UserGetDto();
        userGetDto.setId(relationship.getRequestedRelationship().getId());
        userGetDto.setUsername(relationship.getRequestedRelationship().getUsername());
        relationshipGetDto.setUserGetDto(userGetDto);
        relationshipGetDto.setStatus(relationship.getStatus());

        return relationshipGetDto;
    }


    public static List<RelationshipGetDto> toListRelationship(List<Relationship> bookFromDb) {
        return bookFromDb.stream().map(RelationshipGetDto::toDto)
                .collect(Collectors.toList());
    }
}
