package sn.diop.babacar.minitodolist.repository;

import sn.diop.babacar.minitodolist.entity.TacheEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepository extends
        JpaRepository<TacheEntity, Long>, QuerydslPredicateExecutor<TacheEntity> {

}
