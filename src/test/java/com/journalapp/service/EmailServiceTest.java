package com.journalapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void testEmailService(){
        emailService.sendEmail("ajayp3078@gmail.com",
                "Testing java email sender",
                "Hi, aap kaise ho ?");
    }

}
