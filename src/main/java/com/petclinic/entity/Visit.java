package com.petclinic.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    @JsonBackReference 
    private Pet pet;

    public Visit() {
    }

    public Visit(Date date, String description, Pet pet) {
        this.date = date;
        this.description = description;
        this.pet = pet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(id, visit.id) &&
               Objects.equals(date, visit.date) &&
               Objects.equals(description, visit.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, description);
    }

    @Override
    public String toString() {
        return "Visit{" +
               "id=" + id +
               ", date=" + date +
               ", description='" + description + '\'' +
               ", pet=" + pet +
               '}';
    }
}
