package com.example.usedstaffsaleapplication.service;


import com.example.usedstaffsaleapplication.Exception.EntityNotFoundException;
import com.example.usedstaffsaleapplication.model.Entity.Advert;
import com.example.usedstaffsaleapplication.model.DTO.AdvertDTO;
import com.example.usedstaffsaleapplication.model.Mapper.AdvertMapper;
import com.example.usedstaffsaleapplication.repository.AdvertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertService {
  //1-Field Injection
   //@Autowired
    private final AdvertRepository advertRepository;

   //2- Constructor Injection
   //public AdvertService(AdvertRepository advertRepository) {
       //this.advertRepository = advertRepository;
   //}

    //3- Setter Injection
        //public void setAdvertRepository(AdvertRepository advertRepository) {
        //this.advertRepository = advertRepository;
    //}

    public List<Advert> getAllAdverts(){
       List<Advert>Alladverts = advertRepository.findAll();
       return Alladverts;
    }
    public Advert getByid (Long id){
        Optional<Advert> byId = advertRepository.findById(id);
        return byId.orElseThrow(()-> new EntityNotFoundException("Advert not found"));

    }
    public Advert create(AdvertDTO advertDTO){
        Advert advert = AdvertMapper.toEntity(advertDTO);
        return  advertRepository.save(advert);
    }
    public void delete(Long id){
        getByid(id);
        advertRepository.deleteById(id);

    }
    public Advert update(String title,AdvertDTO advert) {
        Optional <Advert> advertByTitle = advertRepository.findAdvertByTitle(title);
        if (!advertByTitle.isPresent())
            throw new EntityNotFoundException("There is nothing to update");
            Advert updated = advertByTitle.get();
            if (!StringUtils.isEmpty(advert.getTitle())) {
                updated.setTitle(advert.getTitle());
            }
            //if (!advertDTO.getExplanation().isEmpty()) {
              //  UpdatedAdvert.setExplanation(advertDTO.getExplanation());
            //}

        return advertRepository.save(updated);
        }

    }


