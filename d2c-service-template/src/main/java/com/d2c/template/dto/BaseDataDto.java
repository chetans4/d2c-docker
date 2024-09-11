package com.d2c.template.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDataDto {

  protected String isActive;
  protected String createdBy;
  protected Date createdOn;
  protected String updatedBy;
  protected Date updatedOn;

}
