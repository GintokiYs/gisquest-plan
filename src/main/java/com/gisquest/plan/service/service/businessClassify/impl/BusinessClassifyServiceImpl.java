package com.gisquest.plan.service.service.businessClassify.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.gisquest.plan.service.dao.BusinessClassifyMapper;
import com.gisquest.plan.service.dao.SearchMapper;
import com.gisquest.plan.service.dao.TargetClassifyMapper;
import com.gisquest.plan.service.model.targetClassify.TargetClassify;
import com.gisquest.plan.service.service.businessClassify.BusinessClassifyService;
import com.gisquest.plan.service.utils.TransformUtil;
import com.gisquest.plan.service.vo.ResponseResult;
import com.gisquest.plan.service.vo.quata.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author honght
 * @date 2020/9/28 10:33
 */
@Service
public class BusinessClassifyServiceImpl implements BusinessClassifyService {

    @Autowired
    BusinessClassifyMapper businessClassifyMapper;

    @Autowired
    SearchMapper searchMapper;

    @Autowired
    TargetClassifyMapper targetClassifyMapper;

    @Override
    public List<TargetResponse> getTargetById(String resourceParentid) {
        return businessClassifyMapper.getTargetById(resourceParentid);
    }

    @Override
    public Map<String, Object> getTargetList(QuataSearchResponse quataSearchResponse) {
        List<String> list = quataSearchResponse.getQuataIdList();
        List<String> year = quataSearchResponse.getYearList();
        List<String> areaList = quataSearchResponse.getAreaList();
        List<String> collectList = quataSearchResponse.getCollectList();
        List<String> dataSourcetList = quataSearchResponse.getDataSourcetList();
        List<QuataResponse> responses = new ArrayList<>();
        List<QuataResponse> avgList = new ArrayList<>();
        List<QuataResponse> medianList = new ArrayList<>();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                TargetClassify targetClassify = targetClassifyMapper.getTable(list.get(i));
                String tableName = "t" + TransformUtil.frontCompWithZore(targetClassify.getAlias(), 6);
                if (year.size() == 0 && areaList.size() == 0 && collectList.size() == 0 && dataSourcetList.size() == 0) {
                    quataSearchResponse.setTableName(tableName);
                    List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                    for (int j = 0; j < list1.size(); j++) {
                        list1.get(j).setTrage(targetClassify.getType());
                    }
                    responses.addAll(list1);
                } else if (year.size() > 0 && areaList.size() == 0 && collectList.size() == 0 && dataSourcetList.size() == 0) {
                    for (int m = 0; m < year.size(); m++) {
                        quataSearchResponse.setTableName(tableName);
                        quataSearchResponse.setYear(Integer.parseInt(year.get(m)));
                        List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                        for (int j = 0; j < list1.size(); j++) {
                            list1.get(j).setTrage(targetClassify.getType());
                        }
                        responses.addAll(list1);
                    }
                } else if (areaList.size() > 0 && year.size() == 0 && collectList.size() == 0 && dataSourcetList.size() == 0) {
                    for (int m = 0; m < areaList.size(); m++) {
                        quataSearchResponse.setTableName(tableName);
                        quataSearchResponse.setArea(areaList.get(m));
                        List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                        for (int j = 0; j < list1.size(); j++) {
                            list1.get(j).setTrage(targetClassify.getType());
                        }
                        responses.addAll(list1);
                    }
                } else if (collectList.size() > 0 && areaList.size() == 0 && year.size() == 0 && dataSourcetList.size() == 0) {
                    for (int m = 0; m < collectList.size(); m++) {
                        quataSearchResponse.setTableName(tableName);
                        quataSearchResponse.setCollectYear(Integer.parseInt(collectList.get(m)));
                        List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                        for (int j = 0; j < list1.size(); j++) {
                            list1.get(j).setTrage(targetClassify.getType());
                        }
                        responses.addAll(list1);
                    }
                } else if (collectList.size() == 0 && areaList.size() == 0 && year.size() == 0 && dataSourcetList.size() >= 0) {
                    for (int m = 0; m < dataSourcetList.size(); m++) {
                        quataSearchResponse.setTableName(tableName);
                        quataSearchResponse.setDataSource(dataSourcetList.get(m));
                        List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                        for (int j = 0; j < list1.size(); j++) {
                            list1.get(j).setTrage(targetClassify.getType());
                        }
                        responses.addAll(list1);
                    }
                } else if (year.size() > 0 && areaList.size() > 0 && collectList.size() == 0 && dataSourcetList.size() == 0) {
                    for (int m = 0; m < areaList.size(); m++) {
                        for (int n = 0; n < year.size(); n++) {
                            quataSearchResponse.setTableName(tableName);
                            quataSearchResponse.setArea(areaList.get(m));
                            quataSearchResponse.setYear(Integer.parseInt(year.get(n)));
                            List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                            for (int j = 0; j < list1.size(); j++) {
                                list1.get(j).setTrage(targetClassify.getType());
                            }
                            responses.addAll(list1);
                        }
                    }
                } else if (year.size() > 0 && areaList.size() == 0 && collectList.size() > 0 && dataSourcetList.size() == 0) {
                    for (int m = 0; m < collectList.size(); m++) {
                        for (int n = 0; n < year.size(); n++) {
                            quataSearchResponse.setTableName(tableName);
                            quataSearchResponse.setCollectYear(Integer.parseInt(collectList.get(m)));
                            quataSearchResponse.setYear(Integer.parseInt(year.get(n)));
                            List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                            for (int j = 0; j < list1.size(); j++) {
                                list1.get(j).setTrage(targetClassify.getType());
                            }
                            responses.addAll(list1);
                        }
                    }
                } else if (year.size() == 0 && areaList.size() > 0 && collectList.size() > 0 && dataSourcetList.size() == 0) {
                    for (int m = 0; m < areaList.size(); m++) {
                        for (int n = 0; n < collectList.size(); n++) {
                            quataSearchResponse.setTableName(tableName);
                            quataSearchResponse.setArea(areaList.get(m));
                            quataSearchResponse.setCollectYear(Integer.parseInt(collectList.get(n)));
                            List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                            for (int j = 0; j < list1.size(); j++) {
                                list1.get(j).setTrage(targetClassify.getType());
                            }
                            responses.addAll(list1);
                        }
                    }
                } else if (year.size() > 0 && areaList.size() == 0 && collectList.size() == 0 && dataSourcetList.size() > 0) {
                    for (int m = 0; m < dataSourcetList.size(); m++) {
                        for (int n = 0; n < year.size(); n++) {
                            quataSearchResponse.setTableName(tableName);
                            quataSearchResponse.setDataSource(dataSourcetList.get(m));
                            quataSearchResponse.setYear(Integer.parseInt(year.get(n)));
                            List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                            for (int j = 0; j < list1.size(); j++) {
                                list1.get(j).setTrage(targetClassify.getType());
                            }
                            responses.addAll(list1);
                        }
                    }
                } else if (year.size() == 0 && areaList.size() > 0 && collectList.size() == 0 && dataSourcetList.size() > 0) {
                    for (int m = 0; m < dataSourcetList.size(); m++) {
                        for (int n = 0; n < areaList.size(); n++) {
                            quataSearchResponse.setTableName(tableName);
                            quataSearchResponse.setDataSource(dataSourcetList.get(m));
                            quataSearchResponse.setArea(areaList.get(n));
                            List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                            for (int j = 0; j < list1.size(); j++) {
                                list1.get(j).setTrage(targetClassify.getType());
                            }
                            responses.addAll(list1);
                        }
                    }
                } else if (year.size() == 0 && areaList.size() == 0 && collectList.size() > 0 && dataSourcetList.size() > 0) {
                    for (int m = 0; m < dataSourcetList.size(); m++) {
                        for (int n = 0; n < collectList.size(); n++) {
                            quataSearchResponse.setTableName(tableName);
                            quataSearchResponse.setDataSource(dataSourcetList.get(m));
                            quataSearchResponse.setCollectYear(Integer.parseInt(collectList.get(n)));
                            List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                            for (int j = 0; j < list1.size(); j++) {
                                list1.get(j).setTrage(targetClassify.getType());
                            }
                            responses.addAll(list1);
                        }
                    }
                } else if (year.size() > 0 && areaList.size() > 0 && collectList.size() > 0 && dataSourcetList.size() == 0) {
                    for (int m = 0; m < areaList.size(); m++) {
                        for (int n = 0; n < collectList.size(); n++) {
                            for (int q = 0; q < year.size(); q++) {
                                quataSearchResponse.setTableName(tableName);
                                quataSearchResponse.setArea(areaList.get(m));
                                quataSearchResponse.setCollectYear(Integer.parseInt(collectList.get(n)));
                                quataSearchResponse.setYear(Integer.parseInt(year.get(q)));
                                List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                                for (int j = 0; j < list1.size(); j++) {
                                    list1.get(j).setTrage(targetClassify.getType());
                                }
                                responses.addAll(list1);
                            }

                        }
                    }
                } else if (year.size() == 0 && areaList.size() > 0 && collectList.size() > 0 && dataSourcetList.size() > 0) {
                    for (int m = 0; m < areaList.size(); m++) {
                        for (int n = 0; n < collectList.size(); n++) {
                            for (int q = 0; q < dataSourcetList.size(); q++) {
                                quataSearchResponse.setTableName(tableName);
                                quataSearchResponse.setArea(areaList.get(m));
                                quataSearchResponse.setCollectYear(Integer.parseInt(collectList.get(n)));
                                quataSearchResponse.setDataSource(dataSourcetList.get(q));
                                List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                                for (int j = 0; j < list1.size(); j++) {
                                    list1.get(j).setTrage(targetClassify.getType());
                                }
                                responses.addAll(list1);
                            }

                        }
                    }
                } else if (year.size() > 0 && areaList.size() == 0 && collectList.size() > 0 && dataSourcetList.size() > 0) {
                    for (int m = 0; m < year.size(); m++) {
                        for (int n = 0; n < collectList.size(); n++) {
                            for (int q = 0; q < dataSourcetList.size(); q++) {
                                quataSearchResponse.setTableName(tableName);
                                quataSearchResponse.setYear(Integer.parseInt(year.get(m)));
                                quataSearchResponse.setCollectYear(Integer.parseInt(collectList.get(n)));
                                quataSearchResponse.setDataSource(dataSourcetList.get(q));
                                List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                                for (int j = 0; j < list1.size(); j++) {
                                    list1.get(j).setTrage(targetClassify.getType());
                                }
                                responses.addAll(list1);
                            }

                        }
                    }
                } else if (year.size() > 0 && areaList.size() > 0 && collectList.size() == 0 && dataSourcetList.size() > 0) {
                    for (int m = 0; m < year.size(); m++) {
                        for (int n = 0; n < areaList.size(); n++) {
                            for (int q = 0; q < dataSourcetList.size(); q++) {
                                quataSearchResponse.setTableName(tableName);
                                quataSearchResponse.setYear(Integer.parseInt(year.get(m)));
                                quataSearchResponse.setArea(areaList.get(n));
                                quataSearchResponse.setDataSource(dataSourcetList.get(q));
                                List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                                for (int j = 0; j < list1.size(); j++) {
                                    list1.get(j).setTrage(targetClassify.getType());
                                }
                                responses.addAll(list1);
                            }

                        }
                    }
                } else if (year.size() > 0 && areaList.size() > 0 && collectList.size() > 0 && dataSourcetList.size() > 0) {
                    for (int m = 0; m < year.size(); m++) {
                        for (int n = 0; n < areaList.size(); n++) {
                            for (int q = 0; q < dataSourcetList.size(); q++) {
                                for (int p = 0; p < collectList.size(); p++) {
                                    quataSearchResponse.setTableName(tableName);
                                    quataSearchResponse.setYear(Integer.parseInt(year.get(m)));
                                    quataSearchResponse.setArea(areaList.get(n));
                                    quataSearchResponse.setDataSource(dataSourcetList.get(q));
                                    quataSearchResponse.setCollectYear(Integer.parseInt(collectList.get(p)));
                                    List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                                    for (int j = 0; j < list1.size(); j++) {
                                        list1.get(j).setTrage(targetClassify.getType());
                                    }
                                    responses.addAll(list1);
                                }

                            }

                        }
                    }
                }


                List<String> listYear = searchMapper.getTargetYear(tableName);

                if (quataSearchResponse.getAve().equals("true")) {
                    for (int k = 0; k < listYear.size(); k++) {
                        float avg = searchMapper.getTargetAvg(tableName, Integer.parseInt(listYear.get(k)));
                        QuataResponse quataResponse = new QuataResponse();
                        quataResponse.setTrage(targetClassify.getType());
                        quataResponse.setTarget(String.valueOf(avg));
                        quataResponse.setYear(Integer.parseInt(listYear.get(k)));
                        avgList.add(quataResponse);

                    }
                }
                if (quataSearchResponse.getMedian().equals("true")) {
                    for (int z = 0; z < listYear.size(); z++) {
                        List<QuataResponse> midel = searchMapper.getTargetMedian(tableName, Integer.parseInt(listYear.get(z)));
                        int length = midel.size();
                        if (length % 2 == 0) { // 偶数
                            int number = length / 2;
                            float number1 = Float.parseFloat(midel.get(number).getTarget());
                            float number2 = Float.parseFloat(midel.get(number + 1).getTarget());
                            float ave = (number1 + number2) / 2;
                            QuataResponse quataResponse = new QuataResponse();
                            quataResponse.setTrage(targetClassify.getType());
                            quataResponse.setTarget(String.valueOf((ave)));
                            quataResponse.setYear(Integer.parseInt(listYear.get(z)));
                            medianList.add(quataResponse);
                        } else {
                            int number = (length + 1) / 2;
                            QuataResponse quataResponse = new QuataResponse();
                            quataResponse.setTrage(targetClassify.getType());
                            quataResponse.setTarget(midel.get(number).getTarget());
                            quataResponse.setYear(Integer.parseInt(listYear.get(z)));
                            medianList.add(quataResponse);
                        }
                    }
                }
            }
        }

        Map<String, Object> hashMap = new HashMap();
        hashMap.put("topic", responses);
        hashMap.put("avg", avgList);
        hashMap.put("median", medianList);
        return hashMap;
    }

    @Override
    public ResponseResult downloadTargetList(QuataSearchResponse quataSearchResponse) {
        // 获得request对象,response对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        Map<String, Object> result = getTargetList(quataSearchResponse);
        List<QuataResponse> responses = (List<QuataResponse>) result.get("topic");
        List<QuataResponse> avgList = (List<QuataResponse>) result.get("avg");
        List<QuataResponse> medianList = (List<QuataResponse>) result.get("median");
        responses.addAll(avgList);
        responses.addAll(medianList);
        List<SimpleQuataResponse> collect = responses.stream().map((p) -> {
            return new SimpleQuataResponse(p.getTrage(), p.getArea(), p.getYear(), p.getSource(), p.getTarget());
        }).collect(Collectors.toList());
        // 通过工具类创建writer，创建xlsx格式
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("trage", "指标名");
        writer.addHeaderAlias("area", "区域");
        writer.addHeaderAlias("year", "年份");
        writer.addHeaderAlias("source", "数据来源");
//        writer.addHeaderAlias("sourceYear", "数据收集年份");
        writer.addHeaderAlias("target", "指标量");
        // 一次性写出内容
        writer.write(collect, true);
        //out为OutputStream，需要写出到的目标流
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        ServletOutputStream out = null;
        String fileName = collect.get(0).getTrage() + ".xlsx";
        System.out.println(fileName);
        try {
            //todo 下载文档存在乱码问题，待解决
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "ISO-8859-1"));
            out = response.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.flush(out, true);
        // 关闭writer，释放内存
        writer.close();
        //关闭输出Servlet流
        IoUtil.close(out);
        return ResponseResult.ok();
    }
}
