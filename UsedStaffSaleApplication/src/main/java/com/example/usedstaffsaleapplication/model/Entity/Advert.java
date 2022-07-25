package com.example.usedstaffsaleapplication.model.Entity;

import com.example.usedstaffsaleapplication.model.AdvertType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_Advert")
public  class Advert {
    @Column(name = "title")
    private String title;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "location")
    private String location;

    @Column(name = "price")
    private Float price;

    @Column(name = "explain")
    private String explain;


   // private List<AdvertCategory> advertCategoryList;
   // private double advertPoint;
    //private String advertComments;


   // private List<AdvertType> advertTypeList;
    //private List<String>keywords;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "stnadard_users_id", referencedColumnName = "id")
   private StandartUsers StandartUsers;


   //private List<LanguageOptions> languageOptionsList;
    //private int countofReview;



}
