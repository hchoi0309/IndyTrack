package com.example.cms.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admins")
@NoArgsConstructor
@Getter
@Setter
@Data
public class Admin extends User {

}
