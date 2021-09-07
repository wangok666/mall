package bupt.cs.shop.sso.controller;

import bupt.cs.shop.common.enums.VerificationEnums;
import bupt.cs.shop.common.security.Token;
import bupt.cs.shop.common.vo.Result;
import bupt.cs.shop.sso.service.MemberService;
import bupt.cs.shop.sso.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MembersController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private VerificationService verificationService;



    @PostMapping("/userLogin")
    public Result<Token> userLogin(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestHeader String uuid) {
        /**
         * 1. 登录之前进行了 滑块验证 登录的时候需要再验证一下滑块的结果
         * 2. 调用service进行登录
         */
        if (verificationService.check(uuid, VerificationEnums.LOGIN)) {
            return this.memberService.usernameLogin(username, password);
        } else {
            return Result.fail(-999,"请重新验证");
        }
    }
}