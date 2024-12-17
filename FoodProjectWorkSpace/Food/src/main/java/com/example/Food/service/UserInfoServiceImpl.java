package com.example.Food.service;

import com.example.Food.domain.UserInfo;
import com.example.Food.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserRepository userRepository;


    @Transactional
    @Override
    public UserInfo updateUserInfo(int uuid, double uweight, double uheight, String uallergy, int ustyle) {
        UserInfo user = userRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("수정할 사용자를 찾을 수 없습니다."));
        user.setUweight(uweight);
        user.setUheight(uheight);
        user.setUallergy(uallergy);
        user.setUstyle(ustyle);

        return userRepository.save(user);
    }

    @Override
    public void changePassword(int uid, String currentPassword, String newPassword, String confirmPassword) {
        UserInfo user = userRepository.findById(uid)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (!user.getUpw().equals(currentPassword)) {
            throw new RuntimeException("현재 비밀번호가 일치하지 않습니다.");
        }

        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException("새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        }

        user.setUpw(newPassword);
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(int uuid) {
        userRepository.deleteById(uuid);
    }
}