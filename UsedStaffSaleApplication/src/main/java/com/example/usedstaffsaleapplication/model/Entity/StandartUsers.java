package com.example.usedstaffsaleapplication.model.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "standard_users")
public class StandartUsers extends Users implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "surname")
  private String surname;

  @Column(name = "email")
  private String email;
  @Column(name = "phone_number")
  private long phoneNumber;





  @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  private List<Advert> advertList;

  @OneToMany(cascade = CascadeType.ALL)
  private List<AdvertComment> advertCommentList;

//@JsonIgnore
// @ManyToOne( cascade = CascadeType.MERGE)
// @JoinColumn(name = "favorite_advert_id",referencedColumnName = "id")
// private Advert adversoffav;





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
