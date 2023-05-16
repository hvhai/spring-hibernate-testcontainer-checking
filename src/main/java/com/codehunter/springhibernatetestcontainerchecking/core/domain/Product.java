package com.codehunter.springhibernatetestcontainerchecking.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
public class Product {
    private Long id;
    private String code;
    private String name;
    private BigDecimal price;
    private ZonedDateTime priceUpdateTime;
    private Producer producer;
}
