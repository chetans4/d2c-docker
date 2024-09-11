package com.d2c.template.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract sealed class AuditStamp permits User {

  @EqualsAndHashCode.Include
  @Column(nullable = false)
  String isActive;

  @JsonIgnore
  @Column(nullable = false)
  String createdBy;

  @JsonIgnore
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  Date createdOn;

  @JsonIgnore
  String updatedBy;

  @JsonIgnore
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  Date updatedOn;

}
