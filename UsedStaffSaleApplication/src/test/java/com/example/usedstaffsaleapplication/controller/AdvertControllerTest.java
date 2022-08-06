package com.example.usedstaffsaleapplication.controller;

import com.example.usedstaffsaleapplication.Exception.handler.GenericExceptionHandler;
import com.example.usedstaffsaleapplication.model.DTO.AdvertDTO;
import com.example.usedstaffsaleapplication.model.Entity.Advert;
import com.example.usedstaffsaleapplication.model.Enums.AdvertCategory;
import com.example.usedstaffsaleapplication.model.Enums.SubCategories;
import com.example.usedstaffsaleapplication.service.AdvertService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@ExtendWith(MockitoExtension.class)
class AdvertControllerTest {

    private MockMvc mvc;

    @Mock
    private AdvertService advertService;

    @InjectMocks
    private AdvertController advertController;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(advertController)
                .setControllerAdvice(new GenericExceptionHandler())
                .build();
    }

    @Test
    void getAllAdverts() throws Exception {
        // init test values / given
        List<Advert> expectedAdvert = getSampleAdvertsTest();

        // stub - when
        when(advertService.getAllAdverts()).thenReturn(expectedAdvert);

        MockHttpServletResponse response =mvc.perform(get("/api/advert/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<Advert> actualAdvert = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<List<Advert>>() {
        });
        Assert.assertEquals(expectedAdvert.size(), actualAdvert.size());

    }



    @Test
    void getAdvertbyId() throws Exception {
        // init test values
        List<Advert> expectedAdvert = getSampleAdvertsTest();

        // stub - given
        when(advertService.getByid(1L)).thenReturn(expectedAdvert.get(0));

        MockHttpServletResponse response = mvc.perform(get("/api/advert/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Advert actualAdvert = new ObjectMapper().readValue(response.getContentAsString(), Advert.class);
        Assert.assertEquals(expectedAdvert.get(0).getTitle(), actualAdvert.getTitle());
    }

    @Test
    void create() throws Exception {
        Advert advert = getSampleAdvertsTest().get(0);
        ObjectMapper enteredJson = new ObjectMapper();
        String enteredAdvert = enteredJson.writeValueAsString(advert);

        // stub - given
        when(advertService.create(Mockito.any(AdvertDTO.class))).thenReturn(advert);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/Advert/create")
                .accept(MediaType.APPLICATION_JSON)
                .content(enteredAdvert)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String createdAdvert = response.getContentAsString();

        // then
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertThat(createdAdvert).isEqualTo(enteredAdvert);
    }




    @Test
    void deleteAdvert() {
    }

    @Test
    void update() {
    }
    private List<Advert> getSampleAdvertsTest() {
        List<Advert> expectedAdvertDB = new ArrayList<>();
        Advert advert = new Advert("title", 1L, "Mersin", 5697654.0F, "explain", AdvertCategory.ELECTRONICDEVICES, SubCategories.DISHWASHER, null, null);
        Advert advert2 = new Advert("title2", 3L, "Mersin2", 985654.0F, "explain2", AdvertCategory.ELECTRONICDEVICES, SubCategories.DISHWASHER, null, null);
        Advert advert1 = new Advert("title1", 2L, "Mersin1", 5698554.0F, "explain1", AdvertCategory.ELECTRONICDEVICES, SubCategories.DISHWASHER, null, null);
        expectedAdvertDB.add(advert);
        expectedAdvertDB.add(advert1);
        expectedAdvertDB.add(advert2);
        return expectedAdvertDB;
    }

    private Comparator<Advert> getComparator() {
        return (o1, o2) -> {
            if (o1.getId() - o2.getId() < 0)
                return -1;
            if (o1.getId() - o2.getId() == 0)
                return 0;
            return 1;
        };
    }

    private Comparator<Advert> getAdvertComparator() {
        return (o1, o2) -> {
            if (o1.getId() - o2.getId() < 0)
                return -1;
            if (o1.getId() - o2.getId() == 0)
                return 0;
            return 1;
        };
    }
}