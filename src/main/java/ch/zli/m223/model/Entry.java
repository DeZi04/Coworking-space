package ch.zli.m223.model;

import javax.persistence.*;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@Entity
public class Entry {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;

  @Column(nullable = false)
  private StatusEnume status;

  @Column(nullable = false)
  private LocalDateTime date;

  @Column(nullable = false)
  private double duration;

  @ManyToOne
  @Fetch(FetchMode.JOIN)
  @JsonIgnoreProperties("workplace")
  private Workplace workplace;

  @ManyToOne
  @Fetch(FetchMode.JOIN)
  @JsonIgnoreProperties("member")
  private Member member;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public StatusEnume getStatus() {
    return status;
  }

  public void setStatus(StatusEnume status) {
    this.status = status;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public double getDuration() {
    return duration;
  }

  public void setDuration(double duration) {
    this.duration = duration;
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