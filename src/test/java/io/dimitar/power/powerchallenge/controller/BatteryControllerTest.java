package io.dimitar.power.powerchallenge.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import io.dimitar.power.powerchallenge.BaseTest;

//@WebMvcTest(BatteryController.class)
@ExtendWith(SpringExtension.class)
//@AutoConfigureMockMvc
//@ContextConfiguration(classes = {BatteryController.class, Battery.class, BatteryService.class, BatteryRange.class,
//		PostcodeRange.class})
@SpringBootTest
public class BatteryControllerTest extends BaseTest{
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void saveBatteries() throws Exception {
        MvcResult result = createSampleBattery(mockMvc, getSampleBattery1Json());
        System.out.println("BatteryControllerTest.saveBatteries()"+result.getResponse().getContentAsString());
        assertEquals(getSampleBattery1Json(), result.getResponse().getContentAsString());
        assertTrue(true);
    }

    @Test
    public void findBatteriesByPostcodeRange() throws Exception {
        createSampleBattery(mockMvc, getSampleBattery1Json());
        createSampleBattery(mockMvc, getSampleBattery2Json());
        createSampleBattery(mockMvc, getSampleBattery3Json());
        MvcResult result = retrieveBatteriesByRange(mockMvc, getRetrieveBatteriesJson());

        assertEquals(getRetrieveBatteriesJsonExpected(), result.getResponse().getContentAsString());
    }

    @Test
    public void findBatteriesByPostcodeRange2() throws Exception {
        createSampleBattery(mockMvc, getSampleBattery1Json());
        createSampleBattery(mockMvc, getSampleBattery2Json());
        createSampleBattery(mockMvc, getSampleBattery3Json());
        MvcResult result = retrieveBatteriesByRange(mockMvc, getRetrieveBatteries2Json());

        assertEquals(getRetrieveBatteries2JsonExpected(), result.getResponse().getContentAsString());
    }

}
