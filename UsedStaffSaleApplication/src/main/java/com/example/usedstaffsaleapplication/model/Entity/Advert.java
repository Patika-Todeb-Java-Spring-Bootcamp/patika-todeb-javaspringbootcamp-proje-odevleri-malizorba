package com.example.usedstaffsaleapplication.model.Entity;

import com.example.usedstaffsaleapplication.model.Enums.AdvertCategory;
import com.example.usedstaffsaleapplication.model.Enums.LanguageOptions;
import com.example.usedstaffsaleapplication.model.Enums.SubCategories;
import com.example.usedstaffsaleapplication.model.Enums.UsingofStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_Advert")
public  class Advert implements Serializable {
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

    @Enumerated
    private AdvertCategory advertCategory;

    @Enumerated
    private SubCategories subCategories;

    @Enumerated
    private UsingofStatus usingofStatus;

    @Enumerated
    private LanguageOptions languageOptions;



    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "standard_users_id")
    private StandartUsers standartUsers;

    @JsonIgnore
    @ManyToMany(mappedBy = "favoriteAdverts")
    private List<StandartUsers> standartUsersList;

//    @JsonBackReference
//    @ManyToOne
//    private  StandartUsers standartUsersList;


//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "adverts_of_fav")
//    private StandartUsers standartUserss;

    /*OneToMany(mappedBy = "advertofbought",cascade = CascadeType.ALL)
    private List <StandartUsers> users; */

    //@JsonBackReference
    //@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    //@JoinColumn(name = "favorite_adverts")
    //private StandartUsers favoriteadverts;




   /* @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "favorite_adverts")
    private List<StandartUsers> useroffav; */


    //private List<LanguageOptions> languageOptionsList;
    //private int countofReview;



}
