package bupt.cs.shop.common.vo.goods.pages;

import lombok.Data;

import java.io.Serializable;

@Data
public class FirstAdvert implements Serializable {

    private String type;

    private String name;

    private String icon;

    private String key;

    private FirstAdvertOption options;
}
