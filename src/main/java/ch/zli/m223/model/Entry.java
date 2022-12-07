package ch.zli.m223.model;

import javax.persistence.*;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.Set;

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

  @OneToMany
  @Fetch(FetchMode.JOIN)
  private Set<Workspace> Workplace;

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

  public Set<Workspace> getWorkplace() {
    return Workplace;
  }

  public void setWorkplace(Set<Workspace> workplace) {
    Workplace = workplace;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }
}