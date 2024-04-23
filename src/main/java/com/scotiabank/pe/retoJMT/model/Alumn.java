package com.scotiabank.pe.retoJMT.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "alumn")
public class Alumn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.annotation.Id
    private Integer id;
    private String name;
    private String lastName;
    private String state;
    private Integer age;
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-5:00")
    @Column(name = "created", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalTime created;
    @UpdateTimestamp
    @Column(name = "modified", insertable = false, columnDefinition = "TIMESTAMP NULL")
    private LocalTime modified;
    @Column(name = "deleted", columnDefinition = "TIMESTAMP NULL")
    private LocalTime deleted;
}
