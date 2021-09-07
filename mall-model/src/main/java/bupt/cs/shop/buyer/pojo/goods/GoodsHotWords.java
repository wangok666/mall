package bupt.cs.shop.buyer.pojo.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsHotWords {

    private Long id;

    private String name;

    private int score;

}
