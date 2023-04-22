package ca.notarius.url.shortener.adapter.database;

import ca.notarius.url.shortener.model.ShortenedUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UrlShortenerDatabaseRepository extends JpaRepository<ShortenedUrlEntity, BigInteger> {

    @Query("SELECT s.id FROM ShortenedUrlEntity s WHERE s.root.rootValue = ?1 AND s.originalPath = ?2")
    Optional<BigInteger> findByDomainAndPath(String domain, String path);
}
