package bupt.cs.shop.common.vo.goods.pages;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class NotEnoughOption implements Serializable {

    private List<List<PageContentImageData>> list;

    private List<Nav> navList;
}
