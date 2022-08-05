package com.example.usedstaffsaleapplication.service;

import com.example.usedstaffsaleapplication.Exception.EntityNotFoundException;
import com.example.usedstaffsaleapplication.model.DTO.StandardUserDto;
import com.example.usedstaffsaleapplication.model.Entity.StandartUsers;
import com.example.usedstaffsaleapplication.model.Mapper.StandardUserMapper;
import com.example.usedstaffsaleapplication.repository.StandardUserRepository;
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
class StandardUserServiceTest {
    @Mock
    StandardUserRepository standardUserRepository;

    @InjectMocks
    StandardUserService standardUserService;



    @Test
    void getById_succesfull() {
        //init
        StandartUsers expectedStandartUsers = getSampleList().get(0);
        Optional<StandartUsers> optExpectedStandatUsers = Optional.of(expectedStandartUsers);
        //stub
        Mockito.when(standardUserRepository.findById(Mockito.any())).thenReturn(optExpectedStandatUsers);

        //then
        StandartUsers actualStandartUser = standardUserService.getById(10L);

        Assert.assertEquals(actualStandartUser.getId(), expectedStandartUsers.getId());
//        Assert.assertEquals(actualStandartUser.getId(), expectedAvert.getId());

    }
    @Test
    void getByid_NotFoundId() {
        // stub - when step
        Mockito.when(standardUserRepository.findById(1L)).thenReturn(Optional.empty());
        // then - validate step
        Assert.assertThrows(EntityNotFoundException.class,
                () -> {
                    StandartUsers actStandardUser = standardUserService.getById(1L);
                });
    }

    @Test
    void getByName() {
        //init
        StandartUsers expectedStandartUsers = getSampleList().get(0);
        Optional<StandartUsers> optExpectedStandatUsers = Optional.of(expectedStandartUsers);
        //stub
        Mockito.when(standardUserRepository.findStandartUsersByName(Mockito.any())).thenReturn(optExpectedStandatUsers);

        //then
        StandartUsers actualStandartUser = standardUserService.getByName("maho");

        Assert.assertEquals(actualStandartUser.getName(), expectedStandartUsers.getName());
//        Assert.assertEquals(actualStandartUser.getId(), expectedAvert.getId());


    }

    @Test
    void getAllstandartUsers() {
        //init
        List<StandartUsers> expectedStandardUser = getSampleList();

        //stub
        Mockito.when(standardUserRepository.findAll()).thenReturn(expectedStandardUser);

        //then
        List<StandartUsers> actualAllStandardUser = standardUserService.getAllstandartUsers();

        Assert.assertEquals(expectedStandardUser.size(), actualAllStandardUser.size());
        for (int i = 0; i < expectedStandardUser.size(); i++) {
            StandartUsers curexcpectedStandardUser = expectedStandardUser.get(i);
            StandartUsers curactualAllStandardUser= actualAllStandardUser.get(i);
            Assert.assertEquals(curexcpectedStandardUser.getId(), curactualAllStandardUser.getId());
            Assert.assertEquals(curexcpectedStandardUser.getName(), curactualAllStandardUser.getName());
            Assert.assertEquals(curexcpectedStandardUser.getAdvertList(), curactualAllStandardUser.getAdvertList());
        }
    }

    @Test
    void create() {
        // init step
        StandardUserDto standardUserDto = new StandardUserDto();
        StandartUsers expectedStandartUser = StandardUserMapper.toEntity(standardUserDto);



        // stub - when step
        Mockito.when(standardUserRepository.save(expectedStandartUser)).thenReturn(expectedStandartUser);

        // then step
        StandartUsers actualStandardUser = standardUserService.create(standardUserDto);


        // validate step
        Assert.assertEquals(actualStandardUser, expectedStandartUser);

    }



    @Test
    void delete() {
        // init step
        StandartUsers standartUsers = getSampleList().get(0);
        Optional<StandartUsers> optionalStandartUsers = Optional.of(standartUsers);
        // stub - when step
        Mockito.when(standardUserRepository.findById(standartUsers.getId())).thenReturn(optionalStandartUsers);
        // doNothing().when(advertRepository).deleteById(advert.getId());
        // then - validate step
        standardUserService.delete(standartUsers.getId());
        Mockito.verify(standardUserRepository, Mockito.times(1)).deleteById(standartUsers.getId());
    }

    @Test
    void update() {
        StandartUsers standartUsers = getSampleList().get(0);

        StandardUserDto standardUserDto = new StandardUserDto();
        standardUserDto.setEmail("asd");
        // stub - when step
        BDDMockito.given(standardUserRepository.findStandartUsersByEmail(standartUsers.getEmail())).willReturn(Optional.of(standartUsers));
        standardUserService.update(standartUsers.getEmail(), standardUserDto);

        // then - validate step
        Assert.assertEquals(standartUsers.getEmail(), standardUserDto.getEmail());

    }

    @Test
    void addFavoriteAdvertList() {

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