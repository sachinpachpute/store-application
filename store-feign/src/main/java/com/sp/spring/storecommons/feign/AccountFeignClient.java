package com.sp.spring.storecommons.feign;

import com.sp.spring.storecommons.web.GetUserInfoResponse;
import com.sp.spring.storecommons.web.GetUserResponse;
import com.sp.spring.storecommons.web.GetUserInfoResponse;
import com.sp.spring.storecommons.web.GetUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("account-service")
public interface AccountFeignClient {

    @GetMapping("/user")
    GetUserResponse getUserByUserName(@RequestParam("userName") String userName);

    @GetMapping("/user")
    GetUserResponse getUserById(@RequestParam("userId") String userId);

    @GetMapping("/userInfo")
    GetUserInfoResponse getUserInfo();

}
