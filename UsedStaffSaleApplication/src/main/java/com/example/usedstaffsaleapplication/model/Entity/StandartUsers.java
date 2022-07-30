package com.example.usedstaffsaleapplication.model.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
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

  @Column(name = "name")
  private String name;

  @Column(name = "surname")
  private String surname;

  @Column(name = "email")
  private String email;
  @Column(name = "phone_number")
  private long phoneNumber;

    //@JsonIgnore
    //@OneToMany(mappedBy = "StandartUsers", cascade = CascadeType.MERGE)
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "Adverts_of_Users",
            joinColumns = {
                    @JoinColumn(name = "users_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "adverts_id")
            }
    )
    private List<Advert> advertList;


  @ManyToMany(cascade = CascadeType.MERGE)
  @JoinTable(
          name = "Adverts_of_fav",
          joinColumns = {
                  @JoinColumn(name = "users_id")
          },
          inverseJoinColumns = {
                  @JoinColumn(name = "favorite_adverts_id")
          }
  )
  private List<Advert> adversoffav;



  /*@JsonBackReference
  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "users_id_that_bought")
  private  Advert advertofbought; */



  //@JsonIgnore
  //@OneToMany(cascade = CascadeType.MERGE)
  //@JoinColumn(name = "sold_of_advert",referencedColumnName = "id")
  //private List<Advert> advertsSold;

 // @OneToMany(cascade = CascadeType.MERGE)
  //@JoinColumn(name = "bought_of_advert",referencedColumnName = "id")
  //private List<StandartUsers> advertsBought;

  //@OneToMany(fetch = FetchType.LAZY, mappedBy = "favoriteadverts", cascade = CascadeType.ALL)
  //private List<Advert> advertsoffav;


  /*@OneToMany(cascade = CascadeType.MERGE)
  @JoinColumn(name = "favorite_advert",referencedColumnName = "id")
  private List<Advert> adverts; */

}
