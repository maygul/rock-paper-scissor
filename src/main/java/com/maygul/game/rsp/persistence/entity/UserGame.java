package com.maygul.game.rsp.persistence.entity;

import com.maygul.game.rsp.common.entity.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "USER_MATCH")
public class UserGame extends BaseEntity {

  @Enumerated(EnumType.STRING)
  @Column(name = "USER_ACTION")
  private Action userAction;
  @Enumerated(EnumType.STRING)
  @Column(name = "APP_ACTION")
  private Action appAction;
  @Column(name = "SCORE")
  private int score;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_USER_ID_USER_MATCH"))
  private User user;
}
