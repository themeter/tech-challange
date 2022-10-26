package io.dimitar.power.powerchallenge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.dimitar.power.powerchallenge.rest.BatteryController;

@SpringBootTest
class PowerChallengeApplicationSanityTests {

    @Autowired
    private BatteryController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
