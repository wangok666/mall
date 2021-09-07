package bupt.cs.shop.buyer.service.impl;

import bupt.cs.shop.buyer.pojo.goods.GoodsHotWords;
import bupt.cs.shop.buyer.mapper.GoodsHotWordsMapper;
import bupt.cs.shop.buyer.service.HotWordsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@DubboService(version = "1.0.0",interfaceClass = HotWordsService.class)
public class HotWordsServiceImpl implements HotWordsService {

    @Autowired
    private GoodsHotWordsMapper hotWordsMapper;

    @Override
    public List<String> getHotWords(Integer start, Integer end) {

        LambdaQueryWrapper<GoodsHotWords> queryWrapper = new LambdaQueryWrapper<>();
        List<GoodsHotWords> goodsHotWords = hotWordsMapper.selectList(queryWrapper);
        List<String> hotWordsList = new ArrayList<>();
        Collections.sort(goodsHotWords, new Comparator<GoodsHotWords>() {
            @Override
            public int compare(GoodsHotWords o1, GoodsHotWords o2) {
                return o2.getScore() - o1.getScore();
            }
        });
        for (GoodsHotWords goodsHotWord : goodsHotWords) {
            hotWordsList.add(goodsHotWord.getName());
        }
        return hotWordsList;

    }
}
