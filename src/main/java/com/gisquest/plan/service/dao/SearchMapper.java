package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.vo.quata.QuataVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：yeyh
 * @date ：Created in 2020/9/27 16:10
 * @description：
 * @modified By：
 */
@Repository
public interface SearchMapper {
    List<QuataVo> fuzzySearch(String searchContent);

    List<QuataVo> searchByTopic(String topic);

    Integer searchTableName(String quataId);

    List<String> searchCondition(@Param("tableName") String tableName,@Param("fieldName") String fieldName);
}
