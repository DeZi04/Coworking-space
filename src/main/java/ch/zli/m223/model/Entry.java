package ch.zli.m223.model;

import javax.persistence.*;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;

@Entity
public class Entry {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Integer id;

  @Column(nullable = false)
  private String status;

  @Column(nullable = false)
  private LocalDateTime date;

  @ManyToOne
  @Fetch(FetchMode.JOIN)
  private Workplace workplace;

  @ManyToOne(optional = false)
  @Fetch(FetchMode.JOIN)
  private Member member;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public Workplace getWorkplace() {
    return workplace;
  }

  public void setWorkplace(Workplace workplace) {
    this.workplace = workplace;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

 
}