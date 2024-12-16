package com.Tework123.Myknowledge.entities;

import com.Tework123.Myknowledge.entities.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Relationships")
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime dateCreate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "requestedRelationship_id")
    private User requestedRelationship;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receivedRelationship_id")
    private User receivedRelationship;

    @Override
    public String toString() {
        return "Relationship{" +
                "requestedRelationship=" + requestedRelationship +
                ", receivedRelationship=" + receivedRelationship +
                ", dateCreate=" + dateCreate +
                ", status=" + status +
                '}';
    }
}
