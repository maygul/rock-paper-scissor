package com.maygul.game.rsp.persistence.repository;

import com.maygul.game.rsp.persistence.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
  List<User> findAllByUsername(String username);
}
