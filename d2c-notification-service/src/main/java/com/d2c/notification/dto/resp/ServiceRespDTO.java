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

	private int id;
	private String name;
	private String desc;
	private String link;

	public ServiceRespDTO() {
		super();
	}

	public ServiceRespDTO(int id, String name, String desc, String link) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.link = link;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + id;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceRespDTO other = (ServiceRespDTO) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (id != other.id)
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return JsonUtil.objectToJson(this, false);
	}
	
}
