package com.codehunter.springhibernatetestcontainerchecking.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Producer {
    private Long id;
    private String name;
}
