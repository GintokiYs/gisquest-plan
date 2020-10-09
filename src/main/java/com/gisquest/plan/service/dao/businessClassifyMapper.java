package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.vo.quata.TargetResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessClassifyMapper {
    List<TargetResponse> getTargetById(String resourceParentid);
}
