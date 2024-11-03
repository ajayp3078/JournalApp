package com.journalapp.service;

import com.fasterxml.jackson.databind.util.Annotations;
import com.journalapp.entity.User;
import com.journalapp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    @Disabled
    public void testfindByUsername() {
        assertNotNull(userRepository.findByUsername("ajay"));
    }

    @Test
    @Disabled
    public void testUserJournalEntries(){
        User user = userRepository.findByUsername("ajay");
        assertTrue(!user.getJournalEntries().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,6,8",
            "3,4,6"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected,a+b);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "ram",
            "mahesh",
            "ajay"
    })
    public void testMultipleUserJournalEntries(String username){
        assertNotNull(userRepository.findByUsername(username),"failed for : "+username);
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userService.saveNewUser(user));
    }

}

// Annotations
// @BeforeEach, @BeforeAll, @AfterEach , @AfterAll
