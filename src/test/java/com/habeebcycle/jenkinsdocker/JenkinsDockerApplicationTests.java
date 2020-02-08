package com.habeebcycle.jenkinsdocker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JenkinsDockerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void printOutMessage(){
        int var = 5;
        System.out.print("Test Pass");
    }

    @Test
    void makeThisPassFail(){
        String message = "failed";
        assertEquals(message, "failed");
    }

}
