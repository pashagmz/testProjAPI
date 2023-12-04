package com.kulbitski.restaurant.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kulbitski.restaurant.converters.RestaurantConverter;
import com.kulbitski.restaurant.dto.RestaurantDTO;
import com.kulbitski.restaurant.services.RestaurantsService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantsControllerTest {

    @Mock
    private RestaurantsService restaurantsService;

    @Mock
    private RestaurantConverter restaurantConverter;

    private RestaurantsController restaurantsController;

    @Before
    public void setUp() {
        restaurantsController = new RestaurantsController(restaurantsService, restaurantConverter);
    }

    @Test
    public void getAllRestaurants_shouldReturnListOfRestaurants() {
        // todo:
    }
}
