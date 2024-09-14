package com.d2c.notification.dto.req;

import com.d2c.notification.data.entity.ServiceEntity;

/**
 * DTO for accepting request from client/peer.
 * 
 * @author ChetanChoudhary
 *
 */
public class ServiceReqDTO {

	private Integer id;
	private String name;
	private String desc;
	private String link;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public ServiceEntity toEntity() {
		return new ServiceEntity(this.getId(), this.getName(), this.getDesc(), this.getLink());
	}

}
