package bupt.cs.shop.buyer.service;


import bupt.cs.shop.common.vo.CategoryVO;

import java.util.List;

public interface CategoryService {

    //根据父类id获取对应的商品分类列表
    List<CategoryVO> findCategoryTree(Long parentId);
}
