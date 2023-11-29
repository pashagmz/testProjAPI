package com.kulbitski.restaurantCollectionAPI.services;

import com.kulbitski.restaurantCollectionAPI.models.Restaurant;
import com.kulbitski.restaurantCollectionAPI.repositories.RestaurantsRepository;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RestaurantsService {

    private final RestaurantsRepository restaurantsRepository;

    @Autowired
    public RestaurantsService(RestaurantsRepository restaurantsRepository) {
        this.restaurantsRepository = restaurantsRepository;
    }

    public List<Restaurant> findAll() {
        return restaurantsRepository.findAll();
    }

    public Optional<Restaurant> findById(int id) {
        return restaurantsRepository.findById(id);
    }

    public List<Restaurant> findByCity(String city) {
        return restaurantsRepository.findByCity(city);
    }

    @Transactional
    public void save(Restaurant restaurant) {
        restaurantsRepository.save(restaurant);
    }

    @Transactional
    public void update(int id, Restaurant updatedRestaurant) {
        updatedRestaurant.setId(id);
        restaurantsRepository.save(updatedRestaurant);
    }



}
