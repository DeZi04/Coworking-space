package ch.zli.m223.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class Workplace {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Integer id;

  @Column(nullable = false)
  private Boolean ocupation;

  @ManyToOne
  @Fetch(FetchMode.JOIN)
  private Entry entries;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Boolean getOcupation() {
    return ocupation;
  }

  public void setOcupation(Boolean ocupation) {
    this.ocupation = ocupation;
  }


}
