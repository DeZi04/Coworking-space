package ch.zli.m223.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class Workplace {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;

  @Column(nullable = false)
  private Boolean ocupation;

  @OneToMany
  @Fetch(FetchMode.JOIN)
  private Set<Entry> entries;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Boolean getOcupation() {
    return ocupation;
  }

  public void setOcupation(Boolean ocupation) {
    this.ocupation = ocupation;
  }

  public Set<Entry> getEntries() {
    return entries;
  }

  public void setEntries(Set<Entry> entries) {
    this.entries = entries;
  }


}
