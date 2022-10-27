package io.dimitar.power.powerchallenge.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.dimitar.power.powerchallenge.BaseTest;
import io.dimitar.power.powerchallenge.model.Battery;
import io.dimitar.power.powerchallenge.model.BatteryRange;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BatteryServiceTest extends BaseTest {

    @Autowired
    BatteryService batteryService;

    List<String> names;

    @Override
    @BeforeEach
    public void setup() {
        List<Battery> batteries = new ArrayList<Battery>();
        names = new ArrayList<String>();
        for (int i = 1; i < 10; i++) {
            String name = "bat" + i;
            batteries.add( new Battery(name, 1000 + i, 12.5 + i));
            names.add(name);
        }

        batteryService.storeBatteries(batteries);
    }

    @Override
    @AfterEach
    public void teardown() {
        batteryRepository.deleteAll();
        names = null;
    }

    @Test
    public void retrieveBatteries() {
        BatteryRange range = batteryService.retriveBatteries(1000,9999);

        assertTrue(names.equals(range.getNames()));
    }

    @Test
    public void retrieveBatteries2() {
        BatteryRange range = batteryService.retriveBatteries(1000,1008);
        names.remove("bat9");

        assertTrue(names.equals(range.getNames()));
    }

    @Test
    public void storeBatteries() {
        setup();
        BatteryRange range = batteryService.retriveBatteries(1000,9999);
        assertTrue(names.equals(range.getNames()));
    }

    @Test
    public void storeBatteries2() {
        teardown();
        BatteryRange range = batteryService.retriveBatteries(1000,9999);
        assertTrue(range.getNames().isEmpty());
    }

}
