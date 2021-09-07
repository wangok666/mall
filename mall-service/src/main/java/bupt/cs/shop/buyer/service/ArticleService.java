package bupt.cs.shop.buyer.service;

import bupt.cs.shop.buyer.params.ArticleSearchParams;
import bupt.cs.shop.common.vo.article.ArticleCategoryVO;
import bupt.cs.shop.common.vo.article.ArticleVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface ArticleService {


    //常见问题
    Page<ArticleVO> articlePage(ArticleSearchParams articleSearchParams);

    //文章详情
    ArticleVO findArticleById(Long id);

    //文章分类列表
    List<ArticleCategoryVO> findAllArticleCategory();
}