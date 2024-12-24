package com.example.Food.service;

import com.example.Food.persistence.UserChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserChangeServiceImpl implements UserChangeService {

    @Autowired
    private UserChangeRepository userChangeRepository;

    @Transactional
    @Override
    public void backupUserChange(int uuid, double uweight, double uheight) {

        userChangeRepository.backupUserChange(uuid, uweight, uheight);
    }
}