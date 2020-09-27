package com.gisquest.plan.service.service.search;

import com.gisquest.plan.service.vo.quata.QuataDataVo;
import com.gisquest.plan.service.vo.quata.QuataSearchVo;
import com.gisquest.plan.service.vo.quata.QuataVo;

import java.util.List;

/**
 * @author ：yeyh
 * @date ：Created in 2020/9/27 16:09
 * @description：
 * @modified By：
 */
public interface SearchService {
    List<String> searchTopics();

    List<QuataVo> fuzzySearch(String searchContent);

    List<QuataDataVo> searchByTopic(String topic);

    List<String> searchCondition(List<String> quataIdList, String fieldName);

    List<QuataDataVo> searchQuataDetail(List<QuataSearchVo> quataSearchVoList);
}
