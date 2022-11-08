package com.dailycodebuffer.JWTDemo.controller;


import com.dailycodebuffer.JWTDemo.entity.User;
import com.dailycodebuffer.JWTDemo.entity.VerificationToken;
import com.dailycodebuffer.JWTDemo.event.RegistrationCompleteEvent;
import com.dailycodebuffer.JWTDemo.model.UserModel;
import com.dailycodebuffer.JWTDemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class RegistrationController {


    private final UserService userService;

    private final ApplicationEventPublisher publisher;

    @Autowired
    public RegistrationController(UserService userService, ApplicationEventPublisher publisher) {
        this.userService = userService;
        this.publisher = publisher;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "success";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token ){
        String result = userService.validateVerificationToken(token);

        if(result.equalsIgnoreCase("valid"))
            return "User verifies successfully";

        return "Bad User";
    }

    @GetMapping("/resendVerifyToken")
    public String resendVerifyToken(@RequestParam("token") String oldToken ,HttpServletRequest httpServletRequest ){
        VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        resendVerificationTokenMail(user ,applicationUrl(httpServletRequest) ,verificationToken);
        return "Verification Link Sent";
    }

    private void resendVerificationTokenMail(User user ,String applicationUrl ,VerificationToken verificationToken){
        String url = applicationUrl
                + "verifyRegistration?token="
                + verificationToken.getToken();

        log.info("click on link to verify your account : {}", url);
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                "/" +
                request.getContextPath();
    }


}
