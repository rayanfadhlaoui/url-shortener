package ca.notarius.url.shortener.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * A representation of an url that contains information on both original and shorter version.
 */
@Entity
@Table(name = "shortened_url")
@Getter
@Setter
public class ShortenedUrlEntity {
    /**
     * The primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    /**
     * The shorter version of the full url path.
     */
    private BigInteger pathKey;

    /**
     * The original path
     */
    @Column(name = "original_path")
    private String originalPath;

    /**
     * The domain.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "root_id", referencedColumnName = "id")
    private UrlEntity root;

}
