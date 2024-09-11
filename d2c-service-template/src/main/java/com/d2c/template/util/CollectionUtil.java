package com.d2c.template.util;

import java.util.Collection;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class CollectionUtil {

  public static boolean isEmpty(Collection collection) {
    return Objects.isNull(collection) || collection.isEmpty();
  }

  public static boolean isNotEmpty(Collection collection) {
    return !isEmpty(collection);
  }

}
