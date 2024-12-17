package com.example.demo.service;

import com.example.demo.domain.UserInfo;

public interface UserInfoService {

    UserInfo getUserInfoByUuId(int uuid);
    
    UserInfo getUserInfoByUId(String uid);

    UserInfo createUserInfo(UserInfo userInfo);

    UserInfo updateUserInfo(int uuid, UserInfo userInfo);

    void deleteUserInfo(int uuid);
    
    
}
