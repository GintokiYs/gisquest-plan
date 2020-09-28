package com.gisquest.plan.service.service.search.impl;

import com.gisquest.plan.service.dao.SearchMapper;
import com.gisquest.plan.service.service.search.SearchService;
import com.gisquest.plan.service.vo.ResponseResult;
import com.gisquest.plan.service.vo.quata.QuataDataVo;
import com.gisquest.plan.service.vo.quata.QuataSearchVo;
import com.gisquest.plan.service.vo.quata.QuataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public ResponseResult fuzzySearch(String searchContent) {
        List<QuataVo> quataList = searchMapper.fuzzySearch("%" + searchContent + "%");
        ResponseResult<List<QuataVo>> ok = ResponseResult.ok();
        ok.setData(quataList);
        return ok;
    }

    @Override
    public ResponseResult searchByTopic(String topic) {
        List<QuataVo> quataList = searchMapper.searchByTopic(topic);
        ResponseResult<List<QuataVo>> ok = ResponseResult.ok();
        ok.setData(quataList);
        return ok;
    }

    @Override
    public ResponseResult searchCondition(List<String> quataIdList, String fieldName) {
        HashSet<String> strSet = new HashSet<>();
        for (String quataId : quataIdList) {
            String tableName = searchMapper.searchTableName(quataId);
            tableName = "t00000" + tableName;
            System.out.println(tableName);
            List<String> strList = searchMapper.searchCondition(tableName, fieldName);
            strSet.addAll(strList);
        }
        ResponseResult<Set<String>> ok = ResponseResult.ok();
        ok.setData(strSet);
        return ok;
    }

    @Override
    public List<QuataDataVo> searchQuataDetail(List<QuataSearchVo> quataSearchVoList) {
        return null;
    }
}
