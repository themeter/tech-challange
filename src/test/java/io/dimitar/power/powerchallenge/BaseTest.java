package io.dimitar.power.powerchallenge;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import io.dimitar.power.powerchallenge.model.BatteryRepository;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class BaseTest {

    @Autowired
    protected BatteryRepository batteryRepository;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        createSampleBattery( getSampleBattery1Json());
        createSampleBattery( getSampleBattery2Json());
        createSampleBattery( getSampleBattery3Json());
    }

    @AfterEach
    public void teardown() {
        batteryRepository.deleteAll();
    }

    public MvcResult createSampleBattery(String battery) throws Exception {
        MvcResult result =  mockMvc.perform(
                post("/api/battery").contentType("application/json").accept("application/json").content(battery)).andReturn();
        result.getResponse().getContentAsString();
        return result;
    }

    public MvcResult retrieveBatteriesByRange( String range) throws Exception {
        MvcResult result =  mockMvc.perform(
                get("/api/battery").contentType("application/json").accept("application/json").content(range)).andReturn();
        result.getResponse().getContentAsString();
        return result;
    }

    public String getSampleBattery1Json() {
        return "[{\"id\":1,\"name\":\" bat1\",\"postcode\":1000,\"capacity\":12.5}]";
    }

    public String getSampleBattery2Json() {
        return "[{\"id\":2,\"name\":\" bat2\",\"postcode\":1001,\"capacity\":9.5}]";
    }

    public String getSampleBattery3Json() {
        return "[{\"id\":3,\"name\":\" bat3\",\"postcode\":6722,\"capacity\":12.5}]";
    }

    public String getSampleBattery4Json() {
        return "[{\"id\":10,\"name\":\" bat4\",\"postcode\":8000,\"capacity\":12.5}]";
    }

    public String getRetrieveBatteriesJson() {
        return "{\"start\": 6720, \"end\": 6723}";
    }

    public String getRetrieveBatteriesJsonExpected() {
        return "{\"startPostcode\":6720,\"endPostcode\":6723,\"names\":[\" bat3\"],\"totalCapacity\":12.5,\"averageCapacity\":12.5}";
    }

    public String getRetrieveBatteries2Json() {
        return "{\"start\": 1000, \"end\": 1001}";
    }

    public String getRetrieveBatteries2JsonExpected() {
        return "{\"startPostcode\":1000,\"endPostcode\":1001,\"names\":[\" bat1\",\" bat2\"],\"totalCapacity\":22.0,\"averageCapacity\":11.0}";
    }

}
