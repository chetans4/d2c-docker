package com.d2c.template.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"start", "size", "total", "items"})
public final class PaginationResponseWrapper<I> {

  private Integer start;
  private Integer size;
  private Long total;
  private List<I> items = new ArrayList<>();

  public PaginationResponseWrapper(Integer start, Integer size, List<I> items) {
    this.start = start;
    this.size = size;
    this.items = items;
  }
}
