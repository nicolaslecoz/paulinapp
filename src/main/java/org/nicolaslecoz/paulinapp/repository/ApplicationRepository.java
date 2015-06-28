package org.nicolaslecoz.paulinapp.repository;

import org.nicolaslecoz.paulinapp.domain.EtatApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends CrudRepository<EtatApplication, Long> {
}
