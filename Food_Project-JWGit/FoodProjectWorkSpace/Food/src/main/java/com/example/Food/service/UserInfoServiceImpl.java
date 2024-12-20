package com.example.Food.service;

import com.example.Food.domain.UserInfo;
import com.example.Food.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserRepository userRepository;


    @Transactional
    @Override
    public UserInfo updateUserInfo(int uuid, double uweight, double uheight, String uphone, int uallergy, int ustyle, int ugoal) {
        UserInfo userinfo = userRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("수정할 사용자를 찾을 수 없습니다."));

        userinfo.setUweight(uweight);
        userinfo.setUheight(uheight);
        userinfo.setUphone(uphone);
        userinfo.setUallergy(uallergy);
        userinfo.setUstyle(ustyle);
        userinfo.setUgoal(ugoal);


        return userRepository.save(userinfo);
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

    @Autowired
    private UserRepository userinfoRepo;

    // 회원 로그인
    // 리턴값: 1 - ID, Pw 일치, 0: Pw 불일치, -1: ID가 존재하지 않음.
    @Override
    public int loginUid(UserInfo vo) {
        System.out.println("입력된 ID: " + vo.getUid());
        System.out.println("입력된 비밀번호: " + vo.getUpw());
        int result = -1;

        UserInfo userinfo = userinfoRepo.findByUid(vo.getUid());

        if(userinfo == null) {
            result = -1;
        } else if(vo.getUpw().equals(userinfo.getUpw())) {
            result = 1;	// id, pw가 모두 일치
        } else {
            result = 0; // 비밀번호 불일치
        }

        return result;
    }

    // 회원 ID 확인
    // id가 존재하면 1, 존재하지 않으면 -1
    @Override
    public int confirmUid(String uid) {
        UserInfo userinfo = userinfoRepo.findByUid(uid);

        if(userinfo == null) {
            return -1;
        } else {
            return 1;
        }
    }

    // 회원정보 상세 조회
    @Override
    public UserInfo getUserInfo(String uid) {
        return userinfoRepo.findByUid(uid);
    }

    @Override
    public void insertUserInfo(UserInfo vo) {
        userinfoRepo.save(vo);

    }

    @Override
    public UserInfo getUidByUnameAndUphone(String uname, String uphone) {
        return userinfoRepo.findByUnameAndUphone(uname, uphone);
    }

    @Override
    public UserInfo getUpwByUidUnameUphone(String uid, String uname, String uphone) {
        return userinfoRepo.findByUidAndUnameAndUphone(uid, uname, uphone);
    }

    @Override
    public void changePassword(UserInfo vo) {
        userinfoRepo.changePassword(vo.getUid(), vo.getUpw());
    }

    @Override
    public List<UserInfo> getUserInfoList(String uname) {

        return userinfoRepo.getUserInfoList(uname);
    }

}