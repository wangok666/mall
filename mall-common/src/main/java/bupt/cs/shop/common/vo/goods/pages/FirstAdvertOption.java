package bupt.cs.shop.common.vo.goods.pages;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FirstAdvertOption implements Serializable {

    private List<DetailImageData> list;
}
