package com.desafio.hotmart.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class DataBaseVersion extends BaseEntity {

    private Integer versiondb_dev;

    private Integer versiondb_prod;
}
