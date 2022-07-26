package com.example.usedstaffsaleapplication.model.Entity;


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
          name = "Advert_of_Favorite",
          joinColumns = {
                  @JoinColumn(name = "users_id")
          },
          inverseJoinColumns = {
                  @JoinColumn(name = "favorite_adverts_id")
          }
  )
    private List<Advert> FavoriteIdList;

  @OneToMany(cascade = CascadeType.MERGE)
  @JoinColumn(name = "sold_of_advert",referencedColumnName = "id")
  private List<StandartUsers> advertsSold;

  @OneToMany(cascade = CascadeType.MERGE)
  @JoinColumn(name = "bought_of_advert",referencedColumnName = "id")
  private List<StandartUsers> advertsBought;


  @ManyToMany
  private Collection<Advert> adverts;

  public Collection<Advert> getAdverts() {
    return adverts;
  }

  public void setAdverts(Collection<Advert> adverts) {
    this.adverts = adverts;
  }

  @ManyToMany
  private Collection<Advert> adverts_of_favorites;

  public Collection<Advert> getAdverts_of_favorites() {
    return adverts_of_favorites;
  }

  public void setAdverts_of_favorites(Collection<Advert> adverts_of_favorites) {
    this.adverts_of_favorites = adverts_of_favorites;
  }
}
