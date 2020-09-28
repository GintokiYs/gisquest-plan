package com.gisquest.plan.service.service.businessClassify;

import com.gisquest.plan.service.vo.quata.*;

import java.util.List;

/**
 * @author honght
 * @date 2020/9/28 10:32
 */
public interface BusinessClassifyService {

    List<TargetResponse> getTargetById(String resourceParentid);

    List<QuataResponse> getTargetList(QuataSearchResponse quataSearchResponse);
}
