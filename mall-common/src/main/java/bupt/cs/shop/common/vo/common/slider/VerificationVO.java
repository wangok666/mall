package bupt.cs.shop.common.vo.common.slider;

import bupt.cs.shop.common.vo.common.VerificationSourceVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationVO implements Serializable {

    /**
     * 资源
     */
    List<VerificationSourceVo> verificationResources;

    /**
     * 滑块资源
     */
    List<VerificationSourceVo> verificationSlider;
}
