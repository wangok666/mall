package bupt.cs.shop.buyer.vo.goods.pages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NavBarData implements Serializable {
    private String name;
    private String url;
}
