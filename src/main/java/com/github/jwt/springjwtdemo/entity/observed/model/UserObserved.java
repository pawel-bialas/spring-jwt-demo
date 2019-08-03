package com.github.jwt.springjwtdemo.entity.observed.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "observe")
public class UserObserved {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long observedAccId;
    private Long observingAccId;
    @CreationTimestamp
    private LocalDateTime creationDate;
}
