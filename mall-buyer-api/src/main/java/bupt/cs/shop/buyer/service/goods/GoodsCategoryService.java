package bupt.cs.shop.buyer.service.goods;


import bupt.cs.shop.buyer.vo.CategoryVO;
import bupt.cs.shop.buyer.service.CategoryService;
import bupt.cs.shop.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GoodsCategoryService {

    @DubboReference(version = "1.0.0")
    private CategoryService categoryService;

    public Result listChildren(Long parentId) {
        //1. 获取商品分类类表  ms_category
        //2. api 只是业务逻辑组合，具体的表查询，应该交过dubbo服务
        //3. 访问分类列表的请求 多了，一个dubbo服务扛不住了，可以部署多个，api的代码不需要做更改
        //4. categoryService提供查询商品分类列表的服务
        try {
            List<CategoryVO> list = categoryService.findCategoryTree(parentId);
            return Result.success(list);

        }catch (Exception e) {
            e.printStackTrace();
            log.error("获取商品分类列表出错了:{}", e.getMessage());
        }

        return null;
    }
}
