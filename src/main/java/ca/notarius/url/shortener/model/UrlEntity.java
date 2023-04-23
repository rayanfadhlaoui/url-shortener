package ca.notarius.url.shortener.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * The domain
 */
@Entity
@Table(name = "url")
@Getter
@Setter
public class UrlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * The domain value.
     */
    private String rootValue;
}
