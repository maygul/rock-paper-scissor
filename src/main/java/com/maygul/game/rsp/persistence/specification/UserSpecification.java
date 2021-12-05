package com.maygul.game.rsp.persistence.specification;

import com.maygul.game.rsp.persistence.entity.User;
import com.maygul.game.rsp.persistence.specification.criteria.UserSearchCriteria;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

  public static Specification<User> findByCriteria(UserSearchCriteria criteria) {
    return (root, query, cb) -> {
      List<Predicate> predicateList = new ArrayList<>();

      if (criteria.getUsername() != null) {
        Predicate usernamePredicate =
            cb.like(cb.lower(root.get("username")), criteria.getUsername().toLowerCase());
        predicateList.add(usernamePredicate);
      }

      if (criteria.getBeginDate() != null) {
        Predicate beginDatePredicate =
            cb.greaterThanOrEqualTo(root.get("createDate"), criteria.getBeginDate());
        predicateList.add(beginDatePredicate);
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
