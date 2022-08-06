package com.example.usedstaffsaleapplication.repository;

import com.example.usedstaffsaleapplication.model.Entity.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface AdvertRepository extends JpaRepository<Advert,Long> {
        //JPQL
    Optional<Advert> findAdvertByTitle(String title);



    //List<Advert> GetAllByDetailsContainingIgnoreCase(String title);

    //List<Advert> getAllByPriceIsLessThan (Float price);



    //private List<Advert> advertList= new ArrayList<>();


// NAtive SQL
// @Query("SELECT a FROM Advert a WHERE a.title= :title AND a.price= : price")
   // List<Advert> findAllByTitleAndPrice(String title, Float price);



   /* public List<Advert>findAll(){
        return advertList;
    }


    public Optional<Advert> getById(Long id) {
        Optional<Advert> advertOptional = advertList.stream()
               .filter(advert -> advert.getId().equals(id)).findFirst();

       return advertOptional;
           for (Advert advert:advertList) {
            if (advert.getId().equals(id)){
                return advert;            }
        }

    public Advert create(Advert advert){
        boolean addStatus = advertList.add(advert);
        if (!addStatus){
            return null;
        }
        return advert;*/

    }


