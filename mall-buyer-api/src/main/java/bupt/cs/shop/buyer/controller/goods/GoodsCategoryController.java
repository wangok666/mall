package bupt.cs.shop.buyer.controller.goods;


import bupt.cs.shop.buyer.service.goods.GoodsCategoryService;
import bupt.cs.shop.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
@Api(tags = "买家端-商品分类")
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;


    @ApiOperation(value = "获取商品分类列表")
    @ApiImplicitParam(name = "parentId", value = "上级分类ID，全部为0", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/get/{parentId}")
    public Result list(@PathVariable Long parentId) {
        return goodsCategoryService.listChildren(parentId);
    }



}
