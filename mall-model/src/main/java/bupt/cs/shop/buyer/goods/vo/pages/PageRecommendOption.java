package bupt.cs.shop.buyer.goods.vo.pages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRecommendOption implements Serializable {

    private PageContent contentLeft;

    private PageContent contentRight;
}
