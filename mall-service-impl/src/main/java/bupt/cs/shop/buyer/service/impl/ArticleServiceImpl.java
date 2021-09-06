package bupt.cs.shop.buyer.service.impl;

import bupt.cs.shop.buyer.goods.pojo.Article;
import bupt.cs.shop.buyer.mapper.ArticleMapper;
import bupt.cs.shop.buyer.mapper.TestMapper;
import bupt.cs.shop.buyer.params.ArticleSearchParams;
import bupt.cs.shop.buyer.pojo.Test;
import bupt.cs.shop.buyer.service.ArticleService;
import bupt.cs.shop.buyer.vo.article.ArticleVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

//加dubbo注解
//发布当前的service服务到nacos上
//ip：port/接口名称/方法名称
//version便于 接口有不同的实现，或者版本升级之用
//interfaceClass 不加这个事务 无法使用
@DubboService(version = "1.0.0",interfaceClass = ArticleService.class)
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public Page<ArticleVO> articlePage(ArticleSearchParams articleSearchParams) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        String categoryId = articleSearchParams.getCategoryId();
        if (StringUtils.isNotBlank(categoryId)) {
            queryWrapper.eq(Article::getCategoryId, categoryId);
        }
        String title = articleSearchParams.getTitle();
        if (StringUtils.isNotBlank(title)) {
            queryWrapper.likeRight(Article::getTitle, title);
        }
        String type = articleSearchParams.getType();
        if (StringUtils.isNotBlank(type)) {
            queryWrapper.eq(Article::getType, type);
        }
        queryWrapper.orderByDesc(Article::getSort);

        queryWrapper.select(Article::getCategoryId,Article::getId,Article::getOpenStatus,Article::getTitle,Article::getSort);
        Page<Article> articlePage = new Page<>(articleSearchParams.getPageNumber(),articleSearchParams.getPageSize());
        Page<Article> articlePage1 = articleMapper.selectPage(articlePage, queryWrapper);

        Page<ArticleVO> articleVOPage = new Page<>();
        BeanUtils.copyProperties(articlePage1,articleVOPage);
        List<Article> records = articlePage1.getRecords();
        articleVOPage.setRecords(copyList(records));
        return articleVOPage;
    }



    public ArticleVO copy(Article article){
        if (article == null){
            return null;
        }
        ArticleVO articleVO = new ArticleVO();

        BeanUtils.copyProperties(article,articleVO);
        articleVO.setId(article.getId().toString());
        return articleVO;
    }
    public  List<ArticleVO> copyList(List<Article> articleList){
        List<ArticleVO> articleVOList = new ArrayList<>();

        for (Article article : articleList) {
            articleVOList.add(copy(article));
        }
        return articleVOList;
    }
}