package com.kulbitski.restaurant.converters;

import com.kulbitski.restaurant.dto.RestaurantDTO;
import com.kulbitski.restaurant.models.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantConverter {

    public RestaurantDTO convertToDTO(Restaurant restaurant) {
        return RestaurantDTO.builder()
            .id(restaurant.getId())
            .name(restaurant.getName())
            .city(restaurant.getCity())
            .averageRating(restaurant.getAverageRating().toString())
            .votes(restaurant.getVotes())
            .estimatedCost(restaurant.getEstimatedCost())
            .build();
    }
}
