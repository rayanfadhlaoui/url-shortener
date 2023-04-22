package ca.notarius.url.shortener.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@Entity
@Table(name = "shortened_url")
public class ShortenedUrlEntity {

    @Id
    private BigInteger id;

    @Column(name = "original_path")
    private String originalPath;

    @ManyToOne
    @JoinColumn(name = "root_id", referencedColumnName = "id")
    private UrlEntity root;

}
