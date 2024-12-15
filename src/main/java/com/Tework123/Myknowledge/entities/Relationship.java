package com.Tework123.Myknowledge.entities;

import com.Tework123.Myknowledge.entities.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_1_id")
    private User user_1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_2_id")
    private User user_2;
}
