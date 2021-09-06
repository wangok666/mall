package bupt.cs.shop.buyer.vo.goods.pages;

import lombok.Data;

import java.io.Serializable;

@Data
public class Carousel implements Serializable {

    private String type;

    private String name;

    private String icon;

    private String showName;

    private String size;

    private CarouselOption options;

    private String key;

}
