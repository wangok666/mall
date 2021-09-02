package bupt.cs.shop.buyer.service;

import bupt.cs.shop.common.vo.Result;

public interface PageService {

    //装修页面查询
    Result findPageTemplate(Integer clientType, int pageType);
}
