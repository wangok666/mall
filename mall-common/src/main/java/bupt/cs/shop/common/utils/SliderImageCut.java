package bupt.cs.shop.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SliderImageCut implements Serializable {

    private String slidingImage;

    private String backImage;

    private int randomX;

    private int randomY;

    private int originalHeight;

    private int originalWidth;

    private int sliderHeight;

    private int sliderWidth;
}
