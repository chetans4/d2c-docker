package com.d2c.template.dto.request;

import java.util.Date;

import com.d2c.template.dto.BaseDataDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDataDto {

  private static final long serialVersionUID = 1L;

  private String userId;

  private String firstName;

  private String lastName;

  private Date registrationDate;

}
