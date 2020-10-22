package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.District.District;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictMapper {
    int insert(District record);

    int insertSelective(District record);

    List<District> selectAll();
}
