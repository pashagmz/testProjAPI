package com.kulbitski.restaurantCollectionAPI.controllers;

import com.kulbitski.restaurantCollectionAPI.dto.RestaurantDTO;
import com.kulbitski.restaurantCollectionAPI.models.Restaurant;
import com.kulbitski.restaurantCollectionAPI.services.RestaurantsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
public class RestaurantsController {

    private final RestaurantsService restaurantsService;
    private final ModelMapper modelMapper;

    @Autowired
    public RestaurantsController(RestaurantsService restaurantsService, ModelMapper modelMapper) {
        this.restaurantsService = restaurantsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantsService.findAll();
    }


    @GetMapping(value = "/query", params = "city")
    public List<Restaurant> getRestaurantsByCity(@RequestParam(value = "city", required = false) String city) {
        return restaurantsService.findByCity(city);
    }


    @GetMapping(value = "/query", params = "id")
    public Optional<Restaurant> getRestaurantById(@RequestParam(value = "id", required = false) int id) {
        return restaurantsService.findById(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody RestaurantDTO restaurantDTO) {
        restaurantsService.save(convertToRestaurant(restaurantDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody RestaurantDTO restaurantDTO, @PathVariable int id) {
        Restaurant updateRestaurant = modelMapper.map(restaurantDTO, Restaurant.class);
        updateRestaurant.setCity(restaurantsService.findById(id).get().getCity());
        updateRestaurant.setName(restaurantsService.findById(id).get().getName());
        updateRestaurant.setEstimatedCost(restaurantsService.findById(id).get().getEstimatedCost());
        restaurantsService.update(id, updateRestaurant);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Restaurant convertToRestaurant(RestaurantDTO restaurantDTO) {
        return modelMapper.map(restaurantDTO, Restaurant.class);
    }


}
