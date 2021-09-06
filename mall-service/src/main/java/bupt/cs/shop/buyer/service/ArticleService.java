package bupt.cs.shop.buyer.service;

import bupt.cs.shop.buyer.params.ArticleSearchParams;
import bupt.cs.shop.buyer.vo.article.ArticleVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface ArticleService {


    //常见问题
    Page<ArticleVO> articlePage(ArticleSearchParams articleSearchParams);

    //文章详情
    ArticleVO findArticleById(Long id);
}