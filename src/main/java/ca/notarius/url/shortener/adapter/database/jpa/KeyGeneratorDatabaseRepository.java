package ca.notarius.url.shortener.adapter.database.jpa;

import ca.notarius.url.shortener.model.KeyPerDomainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface KeyGeneratorDatabaseRepository extends JpaRepository<KeyPerDomainEntity, BigInteger> {

    @Query("SELECT k.id FROM KeyPerDomainEntity k WHERE k.domain = :domain")
    Optional<BigInteger> getCurrent(@Param("domain") String domain);

}
