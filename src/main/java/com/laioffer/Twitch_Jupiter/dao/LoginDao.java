package com.laioffer.Twitch_Jupiter.dao;

import com.laioffer.Twitch_Jupiter.entity.db.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDao {

    @Autowired
    private SessionFactory sessionFactory;

    public String verifyLogin(String userId, String password){
        String name = "";

        try (Session session = sessionFactory.openSession()){
            User user = session.get(User.class, userId);
            if(user != null && user.getPassword().equals(password)){
                name = user.getFirstName();
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return name;
    }
}
