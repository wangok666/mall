package bupt.cs.shop.buyer.vo.goods.pages;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CarouselOption implements Serializable {

    private List<ImageData> list;
}
