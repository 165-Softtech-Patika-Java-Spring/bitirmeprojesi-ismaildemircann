package com.softtech.bitirmeprojesiismaildemircann.app.prd.entity;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "PRD_PRODUCT")
public class PrdProduct extends BaseEntity {

    @Id
    @SequenceGenerator(name = "PrdProduct", sequenceName = "PRD_PRODUCT_ID_SEQ")
    @GeneratedValue(generator = "PrdProduct")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "TAX_FREE_PRICE", nullable = false, precision = 19, scale = 2)
    private BigDecimal taxFreePrice;

    @Column(name = "VAT_PRICE", precision = 19, scale = 2)
    private BigDecimal vatPrice;

    @Column(name = "LAST_PRICE", precision = 19, scale = 2)
    private BigDecimal lastPrice;

    @Column(name = "ID_PCT_PRODUCT_CATEGORY", nullable = false)
    private Long productCategoryId;
}
