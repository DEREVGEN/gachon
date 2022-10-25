package com.project.gachon.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class aprioriServiceTest {

    @Test
    @DisplayName("test - apriori")
    public void test_apri() throws Exception {
        ArrayList[] stringInput = new ArrayList[5];
        for (int i = 0; i < stringInput.length; i++)
            stringInput[i] = new ArrayList<String>();
        stringInput[0].add("Rain.blue");
        stringInput[0].add("Rain.green");
        stringInput[0].add("Drizzle.yellow");
        stringInput[0].add("Rain.purple");
        stringInput[0].add("Rain.blue");
        stringInput[1].add("Thunderstorm.blue");
        stringInput[1].add("Rain.green");
        stringInput[1].add("Thunderstorm.purple");
        stringInput[2].add("Rain.blue");
        stringInput[2].add("Drizzle.yellow");
        stringInput[2].add("Rain.purple");
        stringInput[3].add("Drizzle.blue");
        stringInput[3].add("Atmosphere.white");
        stringInput[3].add("Clouds.orange");
        stringInput[3].add("Clouds.purple");
        stringInput[4].add("Clouds.orange");
        stringInput[4].add("Clear.white");
        stringInput[4].add("Clear.green");


        aprioriService service = new aprioriService(stringInput);

        service.apriori();
    }
}