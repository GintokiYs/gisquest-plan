package com.gisquest.plan.service.service.search.impl;

import com.gisquest.plan.service.dao.SearchMapper;
import com.gisquest.plan.service.service.search.SearchService;
import com.gisquest.plan.service.utils.TransformUtil;
import com.gisquest.plan.service.vo.ResponseResult;
import com.gisquest.plan.service.vo.quata.QuataDataVo;
import com.gisquest.plan.service.vo.quata.QuataSearchVo;
import com.gisquest.plan.service.vo.quata.QuataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
        if (searchContent == null || "".equals(searchContent)) {
            searchContent = "%";
        }
        List<QuataVo> quataList = searchMapper.fuzzySearch("%" + searchContent + "%");
        for (QuataVo quataVo : quataList) {
            //tableName 填充完整表名
            quataVo.setTableName("t" + TransformUtil.frontCompWithZore(quataVo.getTableName(), 6));
        }
        ResponseResult<List<QuataVo>> ok = ResponseResult.ok();
        ok.setData(quataList);
        return ok;
    }

    @Override
    public ResponseResult searchByTopic(String topicId) {
        List<QuataVo> quataList = searchMapper.searchByTopicId(topicId);
        for (QuataVo quataVo : quataList) {
            //tableName 填充完整表名
            quataVo.setTableName("t" + TransformUtil.frontCompWithZore(quataVo.getTableName(), 6));
        }
        ResponseResult<List<QuataVo>> ok = ResponseResult.ok();
        ok.setData(quataList);
        return ok;
    }

    @Override
    public ResponseResult searchCondition(List<String> quataIdList, String fieldName) {
        TreeSet<String> strSet = new TreeSet<>();
        for (String quataId : quataIdList) {
            int tableName = searchMapper.searchTableName(quataId);
            System.out.println(tableName);
            List<String> strList = searchMapper.searchCondition("t" + TransformUtil.frontCompWithZore(tableName, 6), fieldName);
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
