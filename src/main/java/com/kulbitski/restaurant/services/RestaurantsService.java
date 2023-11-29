package com.kulbitski.restaurant.services;

import com.kulbitski.restaurant.dto.requests.RestaurantCreateRequest;
import com.kulbitski.restaurant.dto.requests.RestaurantUpdateRequest;
import com.kulbitski.restaurant.models.Restaurant;
import java.util.List;
import org.springframework.data.domain.Sort;

public interface RestaurantsService {

    List<Restaurant> findAll();

    List<Restaurant> findAll(Sort sort);

    Restaurant findById(Integer id);

    List<Restaurant> findAllByCity(String city);

    void save(RestaurantCreateRequest createRequest);

    void update(Integer id, RestaurantUpdateRequest updateRequest);

    void delete(Integer id);
}
