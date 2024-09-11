package com.d2c.template.data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class RowPageRequest extends PageRequest {

  private final Integer offset;//first record is 0
  private final Integer size;
  private final Sort sort;

  public RowPageRequest(int offset, int size) {
    super(offset, size, Sort.unsorted());

    if (offset < 0) {
      throw new IllegalArgumentException("Row index must not be less than or equals to zero.");
    }
    if (size < 1) {
      throw new IllegalArgumentException("Page size must not be less than one.");
    }
    this.offset = offset;
    this.size = size;
    this.sort = Sort.unsorted();
  }

  @Override
  public long getOffset() {
    return offset;
  }

}
