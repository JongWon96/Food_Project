package com.example.Food.service;

import com.example.Food.domain.UserInfo;

public interface UserInfoService {

    UserInfo updateUserInfo(int uuid, double uweight, double uheight, String uallergy, int style);

    void changePassword(int uid, String currentPassword, String newPassword, String confirmPassword);

    void deleteUserById(int uuid);
}