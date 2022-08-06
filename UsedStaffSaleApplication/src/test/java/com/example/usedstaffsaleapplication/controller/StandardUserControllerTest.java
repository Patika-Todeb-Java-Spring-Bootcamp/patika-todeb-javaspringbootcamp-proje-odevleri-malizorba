package com.example.usedstaffsaleapplication.controller;

import com.example.usedstaffsaleapplication.Exception.handler.GenericExceptionHandler;
import com.example.usedstaffsaleapplication.model.Entity.StandartUsers;
import com.example.usedstaffsaleapplication.service.StandardUserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor

class StandardUserControllerTest {

    private MockMvc mockMvc;





    @Mock
    private StandardUserService standardUserService;

    @InjectMocks
    private StandardUserController standardUserController;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(standardUserController)
                .setControllerAdvice(new GenericExceptionHandler())
                .build();
    }



    @Test
    void getById  () throws Exception  {
        // init test values
        List<StandartUsers> expectedStandardUser = getSampleList();

        // stub - given
        Mockito.when(standardUserService.getById(1L)).thenReturn(expectedStandardUser.get(0));

        MockHttpServletResponse response = mockMvc.perform(get("/usedstaffsale-app/api/StandartUser/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        StandartUsers actualStandardUser = new ObjectMapper().readValue(response.getContentAsString(), StandartUsers.class);
        Assert.assertEquals(expectedStandardUser.get(0).getName(), actualStandardUser.getName());
    }

    @Test
    void getByName () throws Exception {
        // init test values
        List<StandartUsers> expectedStandardUsers = getSampleList();

        // stub - given
        Mockito.when(standardUserService.getByName("mali")).thenReturn(expectedStandardUsers.get(0));

        MockHttpServletResponse response = mockMvc.perform(get("/usedstaffsale-app/api/StandartUser/mali")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

        List<StandartUsers> actualStandardUsers= standardUserService.getAllstandartUsers();
        //StandartUsers actualStandardUser = new ObjectMapper().readValue(response.getContentAsString(), StandartUsers.class);

        Assert.assertEquals(expectedStandardUsers.get(0).getName(), actualStandardUsers.get(0).getName());
//        assertEquals(expectedPrelectors.get(0).getLastName(), actualPrelector.getLastName());
//        assertEquals(expectedPrelectors.get(0).getEmail(), actualPrelector.getEmail());

    }

    @Test
    void getAllstandardusers() throws  Exception{
       //init test value
        List<StandartUsers> expectedStandartUser = getSampleList();


        //stub given
        Mockito.when(standardUserService.getAllstandartUsers()).thenReturn(expectedStandartUser);

        MockHttpServletResponse response = mockMvc.perform(get("/usedstaffsale-app/api/StandartUser/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<StandartUsers> actualStandardUSer = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<List<StandartUsers>>() {
        });

        Assert.assertEquals(expectedStandartUser.size(), actualStandardUSer.size());


    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void addFavoriteList() {
    }

    @Test
    void addAdvert() {
    }
    private List<StandartUsers> getSampleList() {
        List<StandartUsers> sampleList = new ArrayList<>();
        StandartUsers standartUsers0 = new StandartUsers(1L, "mali", "zorba", "malizorba@hotmail.com", 33221466963L,null, null);
        StandartUsers standartUsers1 = new StandartUsers(2L, "mehmet", "kolayba", "mmehmetkolayba@hotmail.com", 652365466963L, null, null);
        StandartUsers standartUsers2 = new StandartUsers(3L, "example", "4example", "example@hotmail.com", 33228566963L, null, null);

        sampleList.add(standartUsers0);
        sampleList.add(standartUsers1);
        sampleList.add(standartUsers2);

    return sampleList;

    }
    private Comparator<StandartUsers> getComparator() {
        return (o1, o2) -> {
            if (o1.getId() - o2.getId() < 0)
                return -1;
            if (o1.getId() - o2.getId() == 0)
                return 0;
            return 1;
        };
    }

    private Comparator<StandartUsers> getStandardUserComparator() {
        return (o1, o2) -> {
            if (o1.getId() - o2.getId() < 0)
                return -1;
            if (o1.getId() - o2.getId() == 0)
                return 0;
            return 1;
        };
    }
}