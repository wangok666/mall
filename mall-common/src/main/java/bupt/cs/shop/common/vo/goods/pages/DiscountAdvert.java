package bupt.cs.shop.common.vo.goods.pages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountAdvert implements Serializable {

    private String type;

    private String name;

    private String icon;

    private DiscountAdvertOption options;

    private String key;
}
