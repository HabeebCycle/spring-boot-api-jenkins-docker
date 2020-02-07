package com.habeebcycle.jenkinsdocker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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

}
