package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.tag.Tag;
import com.gisquest.plan.service.vo.quata.TagResponse;

import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(String id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    List<Tag> selectOrderByKeword();

    List<TagResponse> selectOrderByCondition(Tag tag);
}