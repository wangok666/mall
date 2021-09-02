package bupt.cs.shop.buyer.service.impl;

import bupt.cs.shop.buyer.enums.ClientType;
import bupt.cs.shop.buyer.enums.OpenStatusEnum;
import bupt.cs.shop.buyer.goods.pojo.pages.PageTemplate;
import bupt.cs.shop.buyer.goods.pojo.pages.TemplateDetail;
import bupt.cs.shop.buyer.goods.vo.pages.*;
import bupt.cs.shop.buyer.mapper.PageTemplateMapper;
import bupt.cs.shop.buyer.mapper.TemplateDetailMapper;
import bupt.cs.shop.buyer.service.PageService;
import bupt.cs.shop.common.model.BusinessCodeEnum;
import bupt.cs.shop.common.vo.Result;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@DubboService(version = "1.0.0",interfaceClass = PageService.class)
public class PageServiceImpl implements PageService {

    @Autowired
    private PageTemplateMapper pageTemplateMapper;
    @Autowired
    private TemplateDetailMapper templateDetailMapper;


    public Result findPageTemplate(Integer clientType, int pageType){
        ClientType clientType1 = ClientType.codeOf(clientType);
        if (clientType1 == null){
            return Result.fail(BusinessCodeEnum.CHECK_PARAM_NO_RESULT.getCode(),"参数错误");
        }
        LambdaQueryWrapper<PageTemplate> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PageTemplate::getClientType,clientType);
        queryWrapper.eq(PageTemplate::getPageType,pageType);
        queryWrapper.eq(PageTemplate::getOpenStatus, OpenStatusEnum.OPEN.getCode());
        queryWrapper.last("limit 1");
        PageTemplate pageTemplate = pageTemplateMapper.selectOne(queryWrapper);
        if (pageTemplate == null){
            return Result.fail(-999,"不存在页面模板");
        }
        Long id = pageTemplate.getId();
        LambdaQueryWrapper<TemplateDetail> detailQueryWrapper = new LambdaQueryWrapper<>();
        detailQueryWrapper.eq(TemplateDetail::getTemplateId,id);
        List<TemplateDetail> templateDetails = templateDetailMapper.selectList(detailQueryWrapper);
//        List<PageTemplateVo> pageTemplateVoList = new ArrayList<>();
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        for (TemplateDetail templateDetail : templateDetails) {
//            PageTemplateVo pageTemplateVo = new PageTemplateVo();
            String templateData = templateDetail.getTemplateData();

            String templateType = templateDetail.getTemplateType();
            if ("topAdvert".equals(templateType)){
                TopAdvert topAdvert = JSON.parseObject(templateData, TopAdvert.class);
//                pageTemplateVo.setPageData(topAdvert);
                map.put(templateDetail.getTemplateType(),topAdvert);
            }
            if ("navBar".equals(templateType)){
                NavBar navBar = JSON.parseObject(templateData,NavBar.class);
                map.put(templateDetail.getTemplateType(),navBar);
//                pageTemplateVo.setPageData(navBar);
            }
            if ("carousel".equals(templateType)){
                Carousel carousel = JSON.parseObject(templateData,Carousel.class);
                map.put(templateDetail.getTemplateType(),carousel);
            }
            if ("discountAdvert".equals(templateType)){
                DiscountAdvert discountAdvert = JSON.parseObject(templateData, DiscountAdvert.class);
                map.put(templateDetail.getTemplateType(),discountAdvert);
            }
            if ("recommend".equals(templateType)){
                PageRecommend pageRecommend = JSON.parseObject(templateData,PageRecommend.class);
                map.put(templateDetail.getTemplateType(),pageRecommend);
            }
            if ("newGoodsSort".equals(templateType)){
                NewGoodsSort newGoodsSort = JSON.parseObject(templateData,NewGoodsSort.class);
                map.put(templateDetail.getTemplateType(),newGoodsSort);
            }
            if ("firstAdvert".equals(templateType)){
                FirstAdvert firstAdvert = JSON.parseObject(templateData,FirstAdvert.class);
                map.put(templateDetail.getTemplateType(),firstAdvert);
            }
            if ("notEnough".equals(templateType)){
                NotEnough notEnough = JSON.parseObject(templateData,NotEnough.class);
                map.put(templateDetail.getTemplateType(),notEnough);
            }
        }

        return Result.success(map);
    }


}
