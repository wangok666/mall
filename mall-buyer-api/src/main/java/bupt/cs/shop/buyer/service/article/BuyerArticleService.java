package bupt.cs.shop.buyer.service.article;

import bupt.cs.shop.buyer.params.ArticleSearchParams;
import bupt.cs.shop.buyer.service.ArticleService;
import bupt.cs.shop.common.vo.article.ArticleCategoryVO;
import bupt.cs.shop.common.vo.article.ArticleVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //文章分类列表
    public List<ArticleCategoryVO> allChildren() {
        return articleService.findAllArticleCategory();
    }
}
