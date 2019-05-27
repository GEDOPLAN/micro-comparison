package de.gedoplan.entity;

import de.gedoplan.baselibs.persistence.entity.GeneratedIntegerIdEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class Person extends GeneratedIntegerIdEntity {

  private String name;
  private String firstname;

  public Person(String name, String firstname) {
    this.name = name;
    this.firstname = firstname;
  }
}
