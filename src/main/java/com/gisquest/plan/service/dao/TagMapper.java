package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.tag.Tag;
import org.springframework.stereotype.Repository;

@Repository
public interface TagMapper {
    int insert(Tag record);

}
