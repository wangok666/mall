package bupt.cs.shop.buyer.service.impl;

import bupt.cs.shop.buyer.goods.pojo.Category;
import bupt.cs.shop.buyer.goods.vo.CategoryVO;
import bupt.cs.shop.buyer.mapper.CategoryMapper;
import bupt.cs.shop.buyer.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


//事务能生效
@DubboService(version = "1.0.0",interfaceClass = CategoryService.class)
//@Transactional
public class CategoryServiceImpl implements CategoryService {


    @Resource
    private CategoryMapper categoryMapper;

    public CategoryVO copy(Category category){
        CategoryVO target = new CategoryVO();
        BeanUtils.copyProperties(category, target);
        return target;
    }
    public List<CategoryVO> copyList(List<Category> categoryList){
        List<CategoryVO> categoryVOList = new ArrayList<CategoryVO>();
        for (Category category : categoryList) {
            categoryVOList.add(copy(category));
        }
        return categoryVOList;
    }

    private List<CategoryVO> categoryTree(Long parentId){
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getStatus,0);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        List<CategoryVO> categoryVOS = copyList(categories);
        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (CategoryVO categoryVO : categoryVOS) {
            if (categoryVO.getParentId().equals(parentId)){
                categoryVOList.add(categoryVO);
                addAllChildren(categoryVO,categoryVOS);
            }
        }
        return categoryVOList;
    }

    private void addAllChildren(CategoryVO categoryVO, List<CategoryVO> categoryVOS) {
        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (CategoryVO vo : categoryVOS) {
            if (vo.getParentId().equals(categoryVO.getId())){
                categoryVOList.add(vo);
                addAllChildren(vo,categoryVOS);
            }
        }
        categoryVO.setChildren(categoryVOList);
    }



    @Override
    public List<CategoryVO> findCategoryTree(Long parentId) {
        return categoryTree(parentId);
    }

}
