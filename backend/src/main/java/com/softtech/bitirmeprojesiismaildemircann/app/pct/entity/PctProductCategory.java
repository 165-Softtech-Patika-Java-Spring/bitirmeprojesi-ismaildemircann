package com.softtech.bitirmeprojesiismaildemircann.app.pct.entity;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.entity.BaseEntity;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.enums.PctProductType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "PCT_PRODUCT_CATEGORY")
public class PctProductCategory extends BaseEntity {

    @Id
    @SequenceGenerator(name = "PctProductCategory", sequenceName = "PCT_PRODUCT_CATEGORY_ID_SEQ")
    @GeneratedValue(generator = "PctProductCategory")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRODUCT_TYPE", length = 30, nullable = false)
    private PctProductType productType;

    @Column(name = "VAT_RATE", nullable = false)
    private Integer vatRate;
}
