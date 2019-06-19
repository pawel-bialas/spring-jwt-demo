package com.github.jwt.springjwtdemo.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Observe")
public class UserObserved {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long observedAccId;
    private Long observingAccId;
    @CreationTimestamp
    private LocalDateTime creationDate;
}
