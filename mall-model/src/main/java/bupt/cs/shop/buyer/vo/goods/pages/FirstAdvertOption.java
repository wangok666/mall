package bupt.cs.shop.buyer.vo.goods.pages;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FirstAdvertOption implements Serializable {

    private List<DetailImageData> list;
}
