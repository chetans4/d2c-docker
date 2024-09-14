package com.d2c.notification.data.repository.fragment;

import com.d2c.notification.data.entity.ServiceEntity;

import java.util.List;

/**
 * This is template of Fragment interface to show custom/dynamic/variable querying with
 * Spring Data JDBC. Implementation of this will use JdbcTemplate.
 * 
 * @author ChetanChoudhary
 *
 */

public interface ServiceFragment {

	List<ServiceEntity> someCustomMethod();

}
