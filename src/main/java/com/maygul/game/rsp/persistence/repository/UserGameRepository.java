package com.maygul.game.rsp.persistence.repository;

import com.maygul.game.rsp.persistence.entity.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGameRepository extends JpaRepository<UserGame, Long>,
    JpaSpecificationExecutor<UserGame> {
}
