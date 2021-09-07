package bupt.cs.shop.common.vo.goods.pages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nav implements Serializable {

    private String title;

    private String desc;
}
