package com.example.usedstaffsaleapplication.model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Advert")
public  class Advert {
    private String title;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String location;

    private Float price;
   // private List<AdvertCategory> advertCategoryList;
    private double advertPoint;
    private String advertComments;
   // @ManyToMany(mappedBy ="",cascade = CascadeType.MERGE)
    //private List<AdvertType> advertTypeList;
    //private List<String>keywords;

    @ManyToOne(cascade = CascadeType.ALL)
   private StandartUsers StandartUsers;


   //private List<LanguageOptions> languageOptionsList;
    //private int countofReview;



}
