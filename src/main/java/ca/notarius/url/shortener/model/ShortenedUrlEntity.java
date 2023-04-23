package ca.notarius.url.shortener.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Table(name = "shortened_url")
@Getter
@Setter
public class ShortenedUrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    private BigInteger pathKey;

    @Column(name = "original_path")
    private String originalPath;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "root_id", referencedColumnName = "id")
    private UrlEntity root;

}
