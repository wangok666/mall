package bupt.cs.shop.common.vo.goods.pages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewGoodsSort implements Serializable {

    private String type;

    private String name;

    private String icon;

    private NewGoodsSortOption options;

    private String key;
}
