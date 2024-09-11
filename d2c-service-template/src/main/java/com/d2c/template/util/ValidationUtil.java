package com.d2c.template.util;

import com.d2c.template.exception.DataNotFoundException;
import com.d2c.template.dto.BaseDataDto;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class ValidationUtil {

  public static void validateData(BaseDataDto data) {
    if (Objects.isNull(data)) {
      throw new DataNotFoundException("Request body doesn't have data");
    }
  }
}
