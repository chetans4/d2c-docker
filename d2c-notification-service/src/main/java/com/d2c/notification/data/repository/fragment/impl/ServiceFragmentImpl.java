package com.d2c.notification.data.repository.fragment.impl;


import com.d2c.notification.data.entity.ServiceEntity;
import com.d2c.notification.data.repository.fragment.ServiceFragment;

import java.util.List;
import java.util.logging.Logger;

/**
 * This is implementation of Fragment interface to show custom/dynamic/variable
 * querying with Spring Data JDBC. This is using JdbcTemplate.
 * 
 * @author ChetanChoudhary
 *
 */
public class ServiceFragmentImpl implements ServiceFragment {

	Logger logger = Logger.getLogger(ServiceFragmentImpl.class.getName());

	@Override
	public List<ServiceEntity> someCustomMethod() {
		return List.of();
	}
}
