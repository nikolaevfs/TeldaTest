package com.example.telda;

import com.example.telda.model.Directory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TeldaApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGettingAll() throws Exception {

        this.mockMvc.perform(get("/api/telda/directory/all"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Moscow"))
                .andExpect(jsonPath("$[0].abbreviation").value("msc"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Moscow region"))
                .andExpect(jsonPath("$[1].abbreviation").value("msc_r"))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].name").value("Saint-Petersburs"))
                .andExpect(jsonPath("$[2].abbreviation").value("spb"))
                .andExpect(jsonPath("$[3].id").value(4))
                .andExpect(jsonPath("$[3].name").value("Kursk region"))
                .andExpect(jsonPath("$[3].abbreviation").value("ksk_r"));
    }

    @Test
    public void testUpdatingSuccess() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        String jsonForUpdate = mapper.writeValueAsString(new Directory(1L, "Moscow_new", "msc_new"));

        this.mockMvc.perform(patch("/api/telda/directory/update")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(jsonForUpdate))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/telda/directory/all"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Moscow_new"))
                .andExpect(jsonPath("$[0].abbreviation").value("msc_new"));
    }

    @Test
    public void testUpdatingFault() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        String jsonForUpdate = mapper.writeValueAsString(new Directory(10L, "Moscow_new", "msc_new"));

        this.mockMvc.perform(patch("/api/telda/directory/update")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding("UTF-8")
                        .content(jsonForUpdate))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
