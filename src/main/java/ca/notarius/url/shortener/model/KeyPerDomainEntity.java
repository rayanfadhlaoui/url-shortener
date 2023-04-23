package ca.notarius.url.shortener.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * Table that hold all the keys per domain.
 */
@Entity
@Table(name = "key_domain")
@Getter
@Setter
public class KeyPerDomainEntity {
    /**
     * The primary id.
     */
    @Id
    private BigInteger id;
    /**
     * The domain.
     */
    private String domain;
}
