package com.yunqi.cloud.cluster;

import com.yunqi.api.user.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCluster {

    @Autowired
    UserApi userApi;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add() {
        return userApi.add(10, 20);
    }

}