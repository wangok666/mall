package bupt.cs.shop.common.vo.goods.pages;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CarouselOption implements Serializable {

    private List<ImageData> list;
}
