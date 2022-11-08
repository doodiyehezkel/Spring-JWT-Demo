package com.dailycodebuffer.JWTDemo.event.listener;

import com.dailycodebuffer.JWTDemo.entity.User;
import com.dailycodebuffer.JWTDemo.event.RegistrationCompleteEvent;
import com.dailycodebuffer.JWTDemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {


    private final UserService userService;

    @Autowired
    public RegistrationCompleteEventListener(UserService userService){
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //Create the verification Token for the user with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(user, token);

        //Send mail to user 7:17:24

        String url = event.getApplicationUrl()
                + "verifyRegistration?token="
                + token;

        log.info("click the link to verify your account: {}" , url);

    }
}
