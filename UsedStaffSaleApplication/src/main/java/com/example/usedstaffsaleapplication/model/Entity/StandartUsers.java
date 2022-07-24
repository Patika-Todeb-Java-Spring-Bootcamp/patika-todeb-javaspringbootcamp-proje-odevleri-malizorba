package com.example.usedstaffsaleapplication.model.Entity;


import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "Users")
public class StandartUsers extends Users {
  @Id
  private Long id;

  @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "Users_Advert",
        joinColumns = {
          @JoinColumn(name = "Users_id")
},
        inverseJoinColumns = {
          @JoinColumn(name = "Advert_id")
        }
)
  private List<Advert> advertList;


  /*@OneToMany(cascade = CascadeType.ALL)
 @JoinTable(
         name = "FavoriteIdList_adverts",
         joinColumns = {
                 @JoinColumn(name = "FavoritedIdList_StandartUsers_id")
         },
         inverseJoinColumns = {
                 @JoinColumn(name = "adverts_id")
         }
 )

  private List<Long> FavoriteIdList; */


}
