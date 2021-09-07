package bupt.cs.shop.sso.service;

import bupt.cs.shop.buyer.pojo.Member;
import bupt.cs.shop.common.cache.CachePrefix;
import bupt.cs.shop.common.context.ThreadContextHolder;
import bupt.cs.shop.common.enums.ClientType;
import bupt.cs.shop.common.security.AuthUser;
import bupt.cs.shop.common.security.Token;
import bupt.cs.shop.common.security.UserEnums;
import bupt.cs.shop.common.utils.token.TokenUtils;
import bupt.cs.shop.common.vo.Result;
import bupt.cs.shop.sso.mapper.MemberMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class MemberService {

    @Resource
    private MemberMapper memberMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    public Result<Token> usernameLogin(String username, String password) {
        /**
         * 1. 根据用户名查询member信息
         * 2. 如果为null，则用户不匹配
         * 3. 密码进行匹配，如果不匹配 密码不正确
         * 4. token 生成token
         * 5. jwt生成token，token放入redis当中，accessToken 过期短， refreshToken过期长
         */
        LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Member::getUsername,username).eq(Member::getDisabled,false);
        Member member = memberMapper.selectOne(queryWrapper);
        if (member == null){
            return Result.fail(-999,"用户不存在");
        }
        //用security的密码类
        if (!new BCryptPasswordEncoder().matches(password,member.getPassword())){
            return Result.fail(-999,"用户名或密码错误");
        }

        Token token = genToken(member);
        return Result.success(token);
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("e10adc3949ba59abbe56e057f20f883e"));
    }
    private Token genToken(Member member) {
        //获取客户端类型
        String clientType = ThreadContextHolder.getHttpRequest().getHeader("clientType");
        Integer clientTypeInt = 0;
        try {
            if (StringUtils.isBlank(clientType)){clientType = "0";}
            clientTypeInt = Integer.parseInt(clientType);
            ClientType type = ClientType.codeOf(clientTypeInt);
            if (type != null){
                member.setClientEnum(clientTypeInt);
            }
        }catch (Exception e){
            e.printStackTrace();
            member.setClientEnum(ClientType.UNKNOWN.getCode());
        }
        //一般登录的时候 会记录用户的最后一次登录的时间
        //MQ 考虑使用mq 把信息发到mq当中，由mq的消费者 来去更新
        member.setLastLoginDate(System.currentTimeMillis());
        this.memberMapper.updateById(member);

        AuthUser authUser = new AuthUser(member.getUsername(), String.valueOf(member.getId()),member.getNickName(), UserEnums.MEMBER);

        Token token = new Token();

        String jwtToken = TokenUtils.createToken(member.getUsername(), authUser, 7 * 24 * 60L);
        token.setAccessToken(jwtToken);
        redisTemplate.opsForValue().set(CachePrefix.ACCESS_TOKEN.name() + UserEnums.MEMBER.name() + jwtToken, "1",7, TimeUnit.DAYS);
        //设置刷新token，当accessToken过期的时候，可以通过refreshToken来 重新获取accessToken 而不用访问数据库
        String refreshToken = TokenUtils.createToken(member.getUsername(), authUser, 15 * 24 * 60L);
        redisTemplate.opsForValue().set(CachePrefix.REFRESH_TOKEN.name() + UserEnums.MEMBER.name() + jwtToken, "1",15, TimeUnit.DAYS);
        token.setRefreshToken(refreshToken);
        return token;
    }
}