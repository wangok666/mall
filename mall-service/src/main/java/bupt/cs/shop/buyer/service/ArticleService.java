package bupt.cs.shop.buyer.service;

import bupt.cs.shop.buyer.params.ArticleSearchParams;
import bupt.cs.shop.buyer.vo.article.ArticleVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface ArticleService {



    Page<ArticleVO> articlePage(ArticleSearchParams articleSearchParams);
}