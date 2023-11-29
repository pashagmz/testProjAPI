package com.kulbitski.restaurant.services.impl;

import com.kulbitski.restaurant.dto.requests.RestaurantCreateRequest;
import com.kulbitski.restaurant.dto.requests.RestaurantUpdateRequest;
import com.kulbitski.restaurant.exceptions.RestaurantNotFoundException;
import com.kulbitski.restaurant.repositories.RestaurantsRepository;
import com.kulbitski.restaurant.models.Restaurant;
import com.kulbitski.restaurant.services.RestaurantsService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RestaurantsServiceImpl implements RestaurantsService {

    private final RestaurantsRepository restaurantsRepository;

    @Autowired
    public RestaurantsServiceImpl(RestaurantsRepository restaurantsRepository) {
        this.restaurantsRepository = restaurantsRepository;
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantsRepository.findAll();
    }

    @Override
    public List<Restaurant> findAll(Sort sort) {
        return restaurantsRepository.findAll(sort);
    }

    @Override
    public Restaurant findById(Integer id) {
        return restaurantsRepository.findById(id)
            .orElseThrow(() -> new RestaurantNotFoundException("Restaurant with ID:" + id + " was not found"));
    }

    @Override
    public List<Restaurant> findAllByCity(String city) {
        return restaurantsRepository.findByCityIgnoreCase(city);
    }

    @Transactional
    @Override
    public void save(RestaurantCreateRequest createRequest) {
        BigDecimal averageRating = new BigDecimal(createRequest.getAverageRating());

        Restaurant newRestaurant = new Restaurant(
            createRequest.getCity(),
            createRequest.getName(),
            averageRating,
            createRequest.getEstimatedCost(),
            createRequest.getVotes()
        );

        restaurantsRepository.save(newRestaurant);
    }

    @Transactional
    @Override
    public void update(Integer id, RestaurantUpdateRequest updateRequest) {
        BigDecimal averageRating = new BigDecimal(updateRequest.getAverageRating());

        Restaurant storedRestaurant = this.findById(id);
        storedRestaurant.setAverageRating(averageRating);
        storedRestaurant.setVotes(updateRequest.getVotes());
        restaurantsRepository.save(storedRestaurant);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        restaurantsRepository.deleteById(id);
    }
}
