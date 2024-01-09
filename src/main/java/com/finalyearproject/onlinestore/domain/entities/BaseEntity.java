package com.mitron.onlinestore.domain.entities;

import static com.mitron.onlinestore.util.constants.AppConstants.*;

import javax.persistence.Column;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
    private String id;

    public BaseEntity() {
    }

    @Id
    @GeneratedValue(generator = UUID_STRING)
    @GenericGenerator(name = UUID_STRING, strategy = UUID_GENERATOR)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
