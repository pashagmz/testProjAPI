package com.kulbitski.restaurant.dto.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestaurantFilter {
    private Integer id;
    private String city;
}
