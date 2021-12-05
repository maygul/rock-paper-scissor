package com.maygul.game.rsp.persistence.entity;

import com.maygul.game.rsp.common.entity.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_")
public class User extends BaseEntity {

  @Column(name = "USER_NAME")
  private String username;

  @OneToMany(
      mappedBy = "user",
      fetch = FetchType.LAZY
  )
  private List<UserGame> userGames = new ArrayList<>();
}
