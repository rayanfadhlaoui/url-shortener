package ca.notarius.url.shortener.adapter.database.jpa;

import ca.notarius.url.shortener.model.ShortenedUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UrlShortenerDatabaseRepository extends JpaRepository<ShortenedUrlEntity, BigInteger> {

    @Query("SELECT s.id FROM ShortenedUrlEntity s WHERE s.root.rootValue = :domain AND s.originalPath = :path")
    Optional<BigInteger> findByDomainAndPath(@Param(value = "domain") String domain,
                                             @Param(value = "path") String path);
}