package ca.notarius.url.shortener.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Table(name = "key_domain")
@Getter
@Setter
public class KeyPerDomainEntity {

    @Id
    private BigInteger id;

    private String domain;
}
