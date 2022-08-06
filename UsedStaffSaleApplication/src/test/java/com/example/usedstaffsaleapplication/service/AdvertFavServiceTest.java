package com.example.usedstaffsaleapplication.service;

import com.example.usedstaffsaleapplication.model.DTO.FavoriteAdvertDTO;
import com.example.usedstaffsaleapplication.model.Entity.FavoriteAdvert;
import com.example.usedstaffsaleapplication.model.Mapper.AdvertFavMapper;
import com.example.usedstaffsaleapplication.repository.AdvertFavRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AdvertFavServiceTest {

    @Mock
    AdvertFavRepository advertFavRepository;

    @Mock

    AdvertFavMapper advertFavMapper;

    @InjectMocks
    AdvertFavService advertFavService;


    @Test
    void getAllFavAdvert() {
        //init
        List<FavoriteAdvert> expectedFavAdvertDB = getSampleAdvertsTest();

        //stub
        Mockito.when(advertFavRepository.findAll()).thenReturn(expectedFavAdvertDB);

        //then
        List<FavoriteAdvert> actualAlladverts = advertFavService.getAllFavAdvert();

    }

    @Test
    void getByAdvertId() {
        //init
        FavoriteAdvert expectedFavAdvertDB = getSampleAdvertsTest().get(0);
        Optional<FavoriteAdvert> optionalExpFavoriteAdvert=Optional.of(expectedFavAdvertDB);

        //stub
        Mockito.when(advertFavRepository.findFavoriteAdvertByAdvert_Id(1L)).thenReturn(optionalExpFavoriteAdvert);

        //then
        FavoriteAdvert actualFavoriteAdvert=advertFavService.getByAdvertId(1L);
        Assert.assertEquals(actualFavoriteAdvert.getId(),expectedFavAdvertDB.getId());
    }

    @Test
    void getByFavUserId() {
        FavoriteAdvert expectedFavAdvertDB = getSampleAdvertsTest().get(0);
        Optional<FavoriteAdvert> optionalExpFavoriteAdvert=Optional.of(expectedFavAdvertDB);

        //stub
        Mockito.when(advertFavRepository.findFavoriteAdvertByStandartUsers_Id(1L)).thenReturn(optionalExpFavoriteAdvert);

        //then
        FavoriteAdvert actualFavoriteAdvert=advertFavService.getByFavUserId(1L);
        Assert.assertEquals(actualFavoriteAdvert.getId(),expectedFavAdvertDB.getId());
    }

    @Test
    void create() {
        // init step
        FavoriteAdvertDTO favoriteAdvertDTO = new FavoriteAdvertDTO();
        FavoriteAdvert expectedfavAdvert = advertFavMapper.toEntity(favoriteAdvertDTO);


        // stub - when step
        Mockito.when(advertFavRepository.save(expectedfavAdvert)).thenReturn(expectedfavAdvert);

        // then step
        FavoriteAdvert actualFavAdvert = advertFavService.create(favoriteAdvertDTO);


        // validate step
        Assert.assertEquals(actualFavAdvert, expectedfavAdvert);

    }


//    @Test
//    void delete() {
//        // init step
//        FavoriteAdvert favoriteAdvert = getSampleAdvertsTest().get(0);
//        Optional<FavoriteAdvert> optionalFavAdvert = Optional.of(favoriteAdvert);
//        // stub - when step
//        Mockito.when(advertFavRepository.findById(1L)).thenReturn(optionalFavAdvert);
//        // doNothing().when(advertRepository).deleteById(advert.getId());
//        // then - validate step
//        advertFavService.delete(1L);
//        Mockito.verify(advertFavRepository, Mockito.times(1)).deleteById(1L);
//    }

    private List<FavoriteAdvert> getSampleAdvertsTest() {
        List<FavoriteAdvert> expectedFavAdvertDB = new ArrayList<>();
        FavoriteAdvert favoriteAdvert = new FavoriteAdvert(1L,null,null);
        FavoriteAdvert favoriteAdvert1 = new FavoriteAdvert(2L,null,null);
        FavoriteAdvert favoriteAdvert2 = new FavoriteAdvert(3L,null,null);
        expectedFavAdvertDB.add(favoriteAdvert);
        expectedFavAdvertDB.add(favoriteAdvert1);
        expectedFavAdvertDB.add(favoriteAdvert2);
        return expectedFavAdvertDB;
    }

    private Comparator<FavoriteAdvert> getComparator() {
        return (o1, o2) -> {
            if (o1.getId() - o2.getId() < 0)
                return -1;
            if (o1.getId() - o2.getId() == 0)
                return 0;
            return 1;
        };
    }

    private Comparator<FavoriteAdvert> getAdvertComparator() {
        return (o1, o2) -> {
            if (o1.getId() - o2.getId() < 0)
                return -1;
            if (o1.getId() - o2.getId() == 0)
                return 0;
            return 1;
        };
    }
}