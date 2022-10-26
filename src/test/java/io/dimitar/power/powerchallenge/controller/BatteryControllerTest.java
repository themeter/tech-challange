package io.dimitar.power.powerchallenge.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;

import io.dimitar.power.powerchallenge.BaseTest;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BatteryControllerTest extends BaseTest{

    @Test
    public void saveBatteries() throws Exception {
        MvcResult result = createSampleBattery( getSampleBattery4Json());

        assertEquals(getSampleBattery4Json(), result.getResponse().getContentAsString());
    }

    @Test
    public void saveBatteries2() throws Exception {
        MvcResult result = createSampleBattery( getSampleBattery1Json());

        assertEquals("[]", result.getResponse().getContentAsString());
    }

    @Test
    public void findBatteriesByPostcodeRange() throws Exception {
        MvcResult result = retrieveBatteriesByRange( getRetrieveBatteriesJson());

        assertEquals(getRetrieveBatteriesJsonExpected(), result.getResponse().getContentAsString());
    }

    @Test
    public void findBatteriesByPostcodeRange2() throws Exception {
        MvcResult result = retrieveBatteriesByRange( getRetrieveBatteries2Json());

        assertEquals(getRetrieveBatteries2JsonExpected(), result.getResponse().getContentAsString());
    }

}
