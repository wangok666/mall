package bupt.cs.shop.buyer.service.goods;


import bupt.cs.shop.buyer.service.HotWordsService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsSearchService {

//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    public List<String> getHotWords(Integer start, Integer end) {
//        List<String> searchWords = new ArrayList<String>();
////        start = (start - 1) * end;
////        end = start + end;
//        start = start - 1;
//        end = end - 1;
//        Set<ZSetOperations.TypedTuple<String>> goodsHotWords = redisTemplate.opsForZSet().reverseRangeWithScores("goods_hot_words", start, end);
//        if (goodsHotWords == null) {
//            return searchWords;
//        }
//        for (ZSetOperations.TypedTuple<String> goodsHotWord : goodsHotWords) {
//            String value = goodsHotWord.getValue();
//            searchWords.add(value);
//        }
//        return searchWords;
//    }

    @DubboReference(version = "1.0.0")
    private HotWordsService hotWordsService;


    public List<String> getHotWords(Integer start, Integer end) {
        return hotWordsService.getHotWords(start, end);
    }




}
