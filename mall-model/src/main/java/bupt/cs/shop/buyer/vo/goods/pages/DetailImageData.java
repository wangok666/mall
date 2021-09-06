package bupt.cs.shop.buyer.vo.goods.pages;

import lombok.Data;

import java.io.Serializable;

@Data
public class DetailImageData implements Serializable {

    private String name;

    private String describe;

    private String img;

    private String url;

    private String fromColor;

    private String toColor;

    private String size;
}
