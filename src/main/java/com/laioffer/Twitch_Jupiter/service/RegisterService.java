package com.laioffer.Twitch_Jupiter.service;

import com.laioffer.Twitch_Jupiter.entity.db.User;
import com.laioffer.Twitch_Jupiter.util.Util;
import com.laioffer.Twitch_Jupiter.dao.RegisterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RegisterService {

    @Autowired
    private RegisterDao registerDao;

    public boolean register(User user) throws IOException {
        user.setPassword(Util.encryptPassword(user.getUserId(), user.getPassword()));
        return registerDao.register(user);
    }
}
