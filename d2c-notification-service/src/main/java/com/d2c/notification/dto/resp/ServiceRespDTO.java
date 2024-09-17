package com.d2c.notification.dto.resp;

import com.d2c.notification.data.entity.ServiceEntity;
import com.d2c.notification.util.JsonUtil;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * DTO to send response data to client/peer.
 * 
 * @author ChetanChoudhary
 *
 */

public class ServiceRespDTO {

	private Integer id;
	private String recipient;
	private String desc;
	private String sent;

	public ServiceRespDTO() {
		super();
	}

	public ServiceRespDTO(Integer id, String recipient, String desc, String sent) {
		super();
		this.id = id;
		this.recipient = recipient;
		this.desc = desc;
		this.sent = sent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSent() {
		return sent;
	}

	public void setSent(String sent) {
		this.sent = sent;
	}

	/**
	 * This method will convert entity object into the DTO
	 * 
	 * @param entity
	 * @return
	 */
	public static ServiceRespDTO byEntity(ServiceEntity entity) {
		return Objects.nonNull(entity)
				? new ServiceRespDTO(entity.getId(), entity.getName(), entity.getDesc(), entity.getLink())
				: null;
	}

	/**
	 * This method will convert Entity list into the DTO list
	 * 
	 * @param entities
	 * @return
	 */
	public static List<ServiceRespDTO> byEntities(List<ServiceEntity> entities) {
		return entities.isEmpty() ? Collections.emptyList()
				: entities.parallelStream().map(ServiceRespDTO::byEntity).collect(Collectors.toList());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ServiceRespDTO that = (ServiceRespDTO) o;
		return Objects.equals(id, that.id) && Objects.equals(recipient, that.recipient) && Objects.equals(desc, that.desc) && Objects.equals(sent, that.sent);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, recipient, desc, sent);
	}

	@Override
	public String toString() {
		return JsonUtil.objectToJson(this, false);
	}
	
}
