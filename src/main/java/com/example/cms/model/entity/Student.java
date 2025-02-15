package com.example.cms.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity // defines a class that represents a table in the database
@NoArgsConstructor // creates a constructor that takes no arguments and initialize a new entity with empty fields
@Getter // creates a getter function
@Setter // creates a setter function
@Table(name = "students") // defines the name of the table in the database that would store our entity
public class Student extends Person {

    @OneToMany(mappedBy = "student")
    @Nullable
    private List<CourseMark> marks  = new ArrayList<>();

}
