package com.example.usedstaffsaleapplication.model.Entity;

import com.example.usedstaffsaleapplication.model.Enums.AdvertCategory;
import com.example.usedstaffsaleapplication.model.Enums.LanguageOptions;
import com.example.usedstaffsaleapplication.model.Enums.SubCategories;
import com.example.usedstaffsaleapplication.model.Enums.UsingofStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
//@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_Advert")

public  class Advert  {

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




    @ManyToMany(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "standard_users_id")
    private List<StandartUsers> standartUsers;





}
