package bupt.cs.shop.buyer.service.article;

import bupt.cs.shop.buyer.params.ArticleSearchParams;
import bupt.cs.shop.buyer.service.ArticleService;
import bupt.cs.shop.buyer.vo.article.ArticleVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class BuyerArticleService {

    @DubboReference(version = "1.0.0")
    private ArticleService articleService;

    public Page<ArticleVO> articlePage(ArticleSearchParams articleSearchParams) {
        return articleService.articlePage(articleSearchParams);
    }

    public ArticleVO customGet(Long id) {
        return articleService.findArticleById(id);
    }
}
