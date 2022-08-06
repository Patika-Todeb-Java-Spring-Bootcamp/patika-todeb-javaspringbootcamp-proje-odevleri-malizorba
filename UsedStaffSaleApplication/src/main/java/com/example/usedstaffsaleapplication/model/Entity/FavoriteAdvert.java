package com.example.usedstaffsaleapplication.model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Favorite_advert")

public class FavoriteAdvert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private StandartUsers standartUsers;


    @ManyToOne(cascade=CascadeType.ALL)
//    @JoinColumn(name = "advert_id",referencedColumnName = "id")
    private Advert advert;







}