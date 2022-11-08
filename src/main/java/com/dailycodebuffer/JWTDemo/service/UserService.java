package com.dailycodebuffer.JWTDemo.service;

import com.dailycodebuffer.JWTDemo.entity.User;
import com.dailycodebuffer.JWTDemo.entity.VerificationToken;
import com.dailycodebuffer.JWTDemo.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(User user, String token);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);
}
