package com.gisquest.plan.service.service.search.impl;

import com.gisquest.plan.service.dao.SearchMapper;
import com.gisquest.plan.service.service.search.SearchService;
import com.gisquest.plan.service.vo.quata.QuataDataVo;
import com.gisquest.plan.service.vo.quata.QuataSearchVo;
import com.gisquest.plan.service.vo.quata.QuataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：yeyh
 * @date ：Created in 2020/9/27 16:09
 * @description：
 * @modified By：
 */
@Service("searchService")
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchMapper searchMapper;

    @Override
    public List<String> searchTopics() {
        return null;
    }

    @Override
    public List<QuataVo> fuzzySearch(String searchContent) {
        return null;
    }

    @Override
    public List<QuataDataVo> searchByTopic(String topic) {
        return null;
    }

    @Override
    public List<String> searchCondition(List<String> quataIdList, String fieldName) {
        return null;
    }

    @Override
    public List<QuataDataVo> searchQuataDetail(List<QuataSearchVo> quataSearchVoList) {
        return null;
    }
}
