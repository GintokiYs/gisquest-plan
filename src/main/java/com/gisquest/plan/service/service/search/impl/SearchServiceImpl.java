package com.gisquest.plan.service.service.search.impl;

import cn.hutool.core.collection.CollUtil;
import com.gisquest.plan.service.dao.SearchMapper;
import com.gisquest.plan.service.service.search.SearchService;
import com.gisquest.plan.service.utils.TransformUtil;
import com.gisquest.plan.service.vo.Coding;
import com.gisquest.plan.service.vo.CodingListVo;
import com.gisquest.plan.service.vo.CodingPo;
import com.gisquest.plan.service.vo.ResponseResult;
import com.gisquest.plan.service.vo.quata.QuataByTopicVo;
import com.gisquest.plan.service.vo.quata.QuataDataVo;
import com.gisquest.plan.service.vo.quata.QuataSearchVo;
import com.gisquest.plan.service.vo.quata.QuataVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        ResponseResult<List<QuataByTopicVo>> ok = ResponseResult.ok();
        ArrayList<QuataByTopicVo> list = new ArrayList<>();
        Map<String, List<QuataVo>> collect = quataList.stream().collect(Collectors.groupingBy(QuataVo::getFirstQuataId));
        for (Map.Entry<String, List<QuataVo>> entry : collect.entrySet()) {
            list.add(new QuataByTopicVo(entry.getKey(), entry.getValue().get(0).getFirstQuata(), entry.getValue()));
        }
        ok.setData(list);
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

    @Override
    public ResponseResult searchCoding() {
        List<CodingPo> codingPos = searchMapper.searchCoding();
        codingPos.forEach(System.out::println);
        CodingListVo codingListVo = new CodingListVo();
        ArrayList<Coding> codingsProvince = new ArrayList<>();
        ArrayList<Coding> codingsCity = new ArrayList<>();
        ArrayList<Coding> codingsDistrict = new ArrayList<>();
        for (CodingPo codingPo : codingPos) {
            int codeType = codingPo.getCodeType();
            switch (codeType) {
                case 3:
                    codingsDistrict.add(new Coding(codingPo.getXzqbh(), codingPo.getName(), "3"));
                    break;
                case 2:
                    Coding cityCoding = new Coding(codingPo.getXzqbh(), codingPo.getName(), "2");
                    Collections.sort(codingsDistrict, new DefineSortComparator());
                    cityCoding.setCodings(codingsDistrict);
                    codingsCity.add(cityCoding);
                    codingsDistrict = new ArrayList<>();
                    break;
                case 1:
                    Coding provinceCoding = new Coding(codingPo.getXzqbh(), codingPo.getName(), "1");
                    Collections.sort(codingsCity, new DefineSortComparator());
                    provinceCoding.setCodings(codingsCity);
                    codingsProvince.add(provinceCoding);
                    codingsCity = new ArrayList<>();
                    codingsDistrict = new ArrayList<>();
                    break;
            }
        }
        Collections.sort(codingsProvince, new DefineSortComparator());
        codingListVo.setCodinglist(codingsProvince);
        ResponseResult<CodingListVo> ok = ResponseResult.ok();
        ok.setData(codingListVo);
        return ok;
    }

    @Override
    public ResponseResult codingFixedSearch(int type) {
        String searchCondition;
        List<CodingPo> codings;
        List<CodingPo> finalCollection;
        if (type == 1) {
            ArrayList<String> arrayList = CollUtil.newArrayList("330127", "330324", "330326", "330327", "330328",
                    "330329", "330723", "330727", "330802", "330803",
                    "330822", "330824", "330825", "330881", "331022",
                    "331023", "331024", "331102", "331121", "331122",
                    "331123", "331124", "331125", "331126", "331127",
                    "331181");
            List<String> collect = arrayList.stream().map((x) -> "'" + x + "'").collect(Collectors.toList());
            searchCondition = StringUtils.join(collect, ",");
            System.out.println("26个加强县 : " + searchCondition);
            codings = searchMapper.codingFixedSearch(searchCondition);
            finalCollection = codings.stream().map((x) -> {
                x.setCodeType(1);
                return x;
            }).collect(Collectors.toList());
        } else {
            ArrayList<String> arrayList = CollUtil.newArrayList("330109", "330110", "330111", "330212", "330281",
                    "330282", "330381", "330382", "330481", "330482",
                    "330483", "330603", "330604", "330681", "330782",
                    "330784", "331081");
            List<String> collect = arrayList.stream().map((x) -> "'" + x + "'").collect(Collectors.toList());
            searchCondition = StringUtils.join(collect, ",");
            System.out.println("17个工业强县 : " + searchCondition);
            codings = searchMapper.codingFixedSearch(searchCondition);
            finalCollection = codings.stream().map((x) -> {
                x.setCodeType(2);
                return x;
            }).collect(Collectors.toList());
        }
        ResponseResult<List<CodingPo>> ok = ResponseResult.ok();
        ok.setData(finalCollection);
        return ok;
    }

    @Override
    public ResponseResult neighboringSearch(String code) {
        List<String> codings= searchMapper.neighboringSearch(code);
        ResponseResult<List<String>> ok = ResponseResult.ok();
        ok.setData(codings);
        return ok;
    }

    class DefineSortComparator implements Comparator<Coding> {
        @Override
        public int compare(Coding o1, Coding o2) {
            return Integer.parseInt(o1.getId()) - Integer.parseInt(o2.getId());
        }
    }
}
