package com.kulbitski.restaurant.controllers;

import com.kulbitski.restaurant.converters.RestaurantConverter;
import com.kulbitski.restaurant.dto.RestaurantDTO;
import com.kulbitski.restaurant.dto.requests.RestaurantCreateRequest;
import com.kulbitski.restaurant.dto.requests.RestaurantUpdateRequest;
import com.kulbitski.restaurant.services.RestaurantsService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RestaurantsController {

    private final RestaurantsService restaurantsService;
    private final RestaurantConverter restaurantConverter;

    @Autowired
    public RestaurantsController(
        RestaurantsService restaurantsService,
        RestaurantConverter restaurantConverter
    ) {
        this.restaurantsService = restaurantsService;
        this.restaurantConverter = restaurantConverter;
    }

    @GetMapping
    public List<RestaurantDTO> getAll() {
        return restaurantsService.findAll().stream()
            .map(restaurantConverter::convertToDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/sort")
    public List<RestaurantDTO> getAllSortedByRating() {
        Sort sort = Sort.by(Sort.Direction.DESC, "averageRating");
        return restaurantsService.findAll(sort).stream()
            .map(restaurantConverter::convertToDTO)
            .collect(Collectors.toList());
    }

    @GetMapping( params = "id")
    public RestaurantDTO getRestaurantById(@RequestParam(value = "id", required = false) Integer id) {
        return restaurantConverter.convertToDTO(restaurantsService.findById(id));
    }

    @GetMapping(params = "city")
    public List<RestaurantDTO> getRestaurantsByCity(@RequestParam(value = "city", required = false) String city) {
        return restaurantsService.findAllByCity(city).stream()
            .map(restaurantConverter::convertToDTO)
            .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody RestaurantCreateRequest createRequest) {
        restaurantsService.save(createRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(
        @PathVariable Integer id,
        @RequestBody RestaurantUpdateRequest updateRequest
    ) {
        restaurantsService.update(id, updateRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        restaurantsService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
