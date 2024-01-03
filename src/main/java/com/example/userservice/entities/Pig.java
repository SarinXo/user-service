package com.example.userservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pigs")
public class Pig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "farmer_id")
    private Integer farmerId;
    private String breed;
    @Column(name = "date_of_birthday")
    private LocalDate dateOfBirthday;
    private String gender;
    @Column(name = "death_date")
    private LocalDate deathDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pig pig = (Pig) o;
        return getId() != null && Objects.equals(getId(), pig.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}


