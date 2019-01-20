package pl.coderslab.jstojava.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.coderslab.jstojava.dto.DataContainerDto;
import pl.coderslab.jstojava.dto.DataDto;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testListEndpointAcceptsJsonArrayUseString() throws Exception {
        // Prepare data
        String payloadJson = "{\"data\":[{\"name\":\"Joe\",\"age\":33},{\"name\":\"Mary\",\"age\":18}]}";
        System.out.println(payloadJson);

        // Send request
        mockMvc.perform(post("/view/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testListEndpointAcceptsJsonArray() throws Exception {
        // Prepare test data (the same as testListEndpointAcceptsJsonArrayUseString) but data
        // is generated from actual objects
        List<DataDto> dataList = Lists.newArrayList(
                new DataDto("Joe", 33),
                new DataDto("Mary", 18)
        );

        // Change to json
        String payloadJson = objectMapper.writeValueAsString(dataList);
        System.out.println(payloadJson);

        // Send request
        mockMvc.perform(post("/view/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testContainerEndpointAcceptsJsonObject() throws Exception {
        // Prepare test data
        List<DataDto> dataList = Lists.newArrayList(
                new DataDto("Joe", 33),
                new DataDto("Mary", 18)
        );
        DataContainerDto dataContainerDto = new DataContainerDto(dataList);

        // Change to json
        String payloadJson = objectMapper.writeValueAsString(dataContainerDto);
        System.out.println(payloadJson);

        // Send request
        mockMvc.perform(post("/view/container")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andExpect(status().isOk());
    }
}
