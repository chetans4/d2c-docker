package com.d2c.template.service.helper;

import com.d2c.template.exception.ApplicationException;
import com.d2c.template.exception.DataNotFoundException;
import com.d2c.template.util.DateUtil;
import com.d2c.template.util.StringUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlanServiceHelper {

  private final PlanRepository planRepository;

  /**
   * Create helper methods
   */
  public Plan toEntityOnCreate(PlanDto planDto) {
    this.validateIdOnCreate(planDto);
    var plan = new Plan();
    BeanUtils.copyProperties(planDto, plan);
    plan.setIsActive(StringUtil.Y_ABBR);
    plan.setCreatedBy(
        StringUtil.isNotBlank(plan.getCreatedBy()) ? plan.getCreatedBy() : StringUtil.SUB_SERVICE);
    plan.setCreatedOn(DateUtil.NOW);
    return plan;
  }

  private void validateIdOnCreate(PlanDto planDto) {
    if (Objects.isNull(planDto)) {
      throw new DataNotFoundException("No Data received in request for Plan Creation");
    }
    if (Objects.nonNull(planDto.getPlanId())) {
      throw new ApplicationException("Do not send id field in create request");
    }
  }

  /**
   * Update helper methods
   */
  public Plan toEntityOnUpdate(PlanDto planDto) {
    this.validateIdOnUpdate(planDto);
    var plan = this.findOrThrow(planDto.getPlanId());

    planDto.setCreatedBy(plan.getCreatedBy());
    planDto.setCreatedOn(plan.getCreatedOn());
    log.info("planDataDto created on in update : {}", planDto.getCreatedOn());

    BeanUtils.copyProperties(planDto, plan);
    plan.setUpdatedBy(
        StringUtil.isNotBlank(plan.getUpdatedBy()) ? plan.getUpdatedBy() : StringUtil.SUB_SERVICE);
    plan.setUpdatedOn(DateUtil.NOW);
    return plan;
  }

  public Plan findOrThrow(Integer planId) {
    return planRepository.findById(planId).orElseThrow(
        () -> new DataNotFoundException(String.format("Plan not found with id : %d", planId)));
  }

  private void validateIdOnUpdate(PlanDto planDto) {
    if (Objects.isNull(planDto)) {
      throw new DataNotFoundException("No Data received in request for Plan Update");
    }
    if (Objects.isNull(planDto.getPlanId())) {
      throw new ApplicationException("Id field is required in update request");
    }
  }

  /**
   * Entity to DTO helper methods
   */
  public PlanDto toDataDto(Plan plan) {
    log.info("plan values : {}", plan);
    var planDto = new PlanDto();
    BeanUtils.copyProperties(plan, planDto);
    return planDto;
  }

  public List<PlanDto> toDataDtos(Collection<Plan> plans) {
    List<PlanDto> dtos = new ArrayList<>();
    for (Plan plan : plans) {
      dtos.add(toDataDto(plan));
    }
    return dtos;
  }

}
