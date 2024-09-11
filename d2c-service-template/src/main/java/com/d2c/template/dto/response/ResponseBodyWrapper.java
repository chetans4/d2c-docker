package com.d2c.template.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Wrapper class for response to provide additional information with core Response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"status", "data"})
public final class ResponseBodyWrapper<D> {

  @JsonProperty("data")
  private D data = (D) new ObjectNode(null);

  private StatusResponseDto status = new StatusResponseDto();

  public ResponseBodyWrapper(StatusResponseDto status) {
    this.status = status;
  }
}