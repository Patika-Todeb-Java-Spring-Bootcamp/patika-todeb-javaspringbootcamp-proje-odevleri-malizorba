package com.example.usedstaffsaleapplication.service;


import com.example.usedstaffsaleapplication.Exception.EntityNotFoundException;
import com.example.usedstaffsaleapplication.model.DTO.AdvertDTO;
import com.example.usedstaffsaleapplication.model.Entity.Advert;
import com.example.usedstaffsaleapplication.model.Enums.AdvertCategory;
import com.example.usedstaffsaleapplication.model.Enums.SubCategories;
import com.example.usedstaffsaleapplication.model.Mapper.AdvertMapper;
import com.example.usedstaffsaleapplication.repository.AdvertRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AdvertServiceTest {

    @Mock
    AdvertRepository advertRepository;

    @InjectMocks
    AdvertService advertService;
//    private Advert testAdvert;

    @Test
    void getAllAdverts() {
        //init
        List<Advert> expectedAdvertDB = getSampleAdvertsTest();

        //stub
        Mockito.when(advertRepository.findAll()).thenReturn(expectedAdvertDB);

        //then
        List<Advert> actualAlladverts = advertService.getAllAdverts();

        Assert.assertEquals(expectedAdvertDB.size(), actualAlladverts.size());
        for (int i = 0; i < expectedAdvertDB.size(); i++) {
            Advert curexcpectedAdvertDB = expectedAdvertDB.get(i);
            Advert curactualAlladvert = actualAlladverts.get(i);
            Assert.assertEquals(curexcpectedAdvertDB.getId(), curactualAlladvert.getId());
            Assert.assertEquals(curexcpectedAdvertDB.getTitle(), curactualAlladvert.getTitle());
        }
//
//        expectedAdvertDB = expectedAdvertDB.stream().sorted(getAdvertComparator()).collect(Collectors.toList());
//
//        actualAlladverts = actualAlladverts.stream().sorted(getComparator()).collect(Collectors.toList());


    }


    @Test
    void getByid_successfull() {
        //init
        Advert expectedAvert = getSampleAdvertsTest().get(1);
        Optional<Advert> optExpectedAdvert = Optional.of(expectedAvert);
        //stub
        Mockito.when(advertRepository.findById(Mockito.any())).thenReturn(optExpectedAdvert);

        //then
        Advert actualAdvert = advertService.getByid(10L);

        Assert.assertEquals(actualAdvert.getTitle(), expectedAvert.getTitle());
        Assert.assertEquals(actualAdvert.getId(), expectedAvert.getId());


    }

    @Test
    void getByid_NotFoundId() {
        // stub - when step
       Mockito.when(advertRepository.findById(1L)).thenReturn(Optional.empty());
        // then - validate step
        Assert.assertThrows(EntityNotFoundException.class,
                () -> {
                    Advert actAdvert = advertService.getByid(1L);
                });
    }


    @Test
    void create() {
        // init step
        AdvertDTO advertDTO = new AdvertDTO();
        Advert expectedAdvert = AdvertMapper.toEntity(advertDTO);


        // stub - when step
        Mockito.when(advertRepository.save(expectedAdvert)).thenReturn(expectedAdvert);

        // then step
        Advert actualAdvert = advertService.create(advertDTO);


        // validate step
        Assert.assertEquals(actualAdvert, expectedAdvert);

    }

    @Test
    void delete() {
        // init step
        Advert advert = getSampleAdvertsTest().get(0);
        Optional<Advert> optionalAdvert = Optional.of(advert);
        // stub - when step
        Mockito.when(advertRepository.findById(advert.getId())).thenReturn(optionalAdvert);
       // doNothing().when(advertRepository).deleteById(advert.getId());
        // then - validate step
        advertService.delete(advert.getId());
        Mockito.verify(advertRepository, Mockito.times(1)).deleteById(advert.getId());
    }

    @Test
    void update() {
        Advert advert = getSampleAdvertsTest().get(0);

        AdvertDTO advertDTO = new AdvertDTO();
        advertDTO.setTitle("asd");
        // stub - when step
        BDDMockito.given(advertRepository.findAdvertByTitle(advert.getTitle())).willReturn(Optional.of(advert));
        advertService.update(advert.getTitle(), advertDTO);

        // then - validate step
        Assert.assertEquals(advert.getTitle(), advertDTO.getTitle());

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

