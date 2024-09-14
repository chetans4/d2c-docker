package com.d2c.notification.data.repository;

import com.d2c.notification.data.entity.ServiceEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository{// extends PagingAndSortingRepository<ServiceEntity, Integer>, ServiceFragment {

	/**
	 * Test Method, Demonstrating Query Methods
	 * 
	 * @param name
	 * @return ServiceEntity
	 */
	ServiceEntity findFirstByName(String name);

	/**
	 * Test Method, Demonstrating Query annotation
	 * 
	 * @return
	 */
//	@Query(" SELECT MAX(ser_id) AS latestId FROM service_entity ")
	Integer findLatestId();

	/**
	 * Template method with update query and also checking case insensitivity for
	 * table names.
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
//	@Modifying
//	@Query("UPDATE test_service_entity SET name = :name WHERE ser_id = :id")
//	boolean updateName(@Param("id") Integer id, @Param("name") String name);

}
