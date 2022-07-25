package com.example.usedstaffsaleapplication.model.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class StandartUsers extends Users {
  @Id
  private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "StandartUsers", cascade = CascadeType.MERGE)
        private List<Advert> advertList;


    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "favorite_adverts",referencedColumnName = "id")
    private List<StandartUsers> FavoriteIdList;

  @OneToMany(cascade = CascadeType.MERGE)
  @JoinColumn(name = "sold_of_advert",referencedColumnName = "id")
  private List<StandartUsers> advertsSold;

  @OneToMany(cascade = CascadeType.MERGE)
  @JoinColumn(name = "bought_of_advert",referencedColumnName = "id")
  private List<StandartUsers> advertsBought;


}
