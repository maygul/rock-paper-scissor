package com.maygul.game.rsp.persistence.specification;

import com.maygul.game.rsp.persistence.entity.User;
import com.maygul.game.rsp.persistence.entity.UserGame;
import com.maygul.game.rsp.persistence.specification.criteria.UserGameSearchCriteria;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class UserGameSpecification {

  public static Specification<UserGame> findByCriteria(UserGameSearchCriteria criteria) {
    return (root, query, cb) -> {
      List<Predicate> predicateList = new ArrayList<>();

      if (criteria.getUsername() != null) {
        Join<UserGame, User> userJoin = root.join("user");
        Predicate usernamePredicate = cb.equal(userJoin.get("username"), criteria.getUsername());
        predicateList.add(usernamePredicate);
      }

      if (criteria.getStartDate() != null) {
        Predicate startDatePredicate =
            cb.greaterThanOrEqualTo(root.get("createDate"), criteria.getStartDate());
        predicateList.add(startDatePredicate);
      }

      if (criteria.getEndDate() != null) {
        Predicate endDatePredicate =
            cb.lessThanOrEqualTo(root.get("createDate"), criteria.getEndDate());
        predicateList.add(endDatePredicate);
      }

      return cb.and(predicateList.toArray(new Predicate[] {}));
    };
  }
}
