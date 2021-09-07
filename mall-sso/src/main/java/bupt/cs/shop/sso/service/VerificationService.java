package bupt.cs.shop.sso.service;

import bupt.cs.shop.common.enums.VerificationEnums;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
//滑块验证后，存了一个验证结果，检查有无过期
@Service
public class VerificationService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean check(String uuid, VerificationEnums verificationEnums) {
        Boolean hasKey = redisTemplate.hasKey("VERIFICATION_IMAGE_RESULT_" + verificationEnums.name() + uuid);
        return hasKey != null && hasKey;
    }
}
