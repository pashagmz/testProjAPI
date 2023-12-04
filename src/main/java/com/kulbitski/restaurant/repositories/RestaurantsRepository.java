package com.kulbitski.restaurant.repositories;

import com.kulbitski.restaurant.models.Restaurant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurant, Integer> {
    List<Restaurant> findByCityIgnoreCase(String city);

    @Query("SELECT r FROM Restaurant r WHERE (:id IS NULL OR r.id = :id) AND (:city IS NULL OR LOWER(r.city) = LOWER(:city))")
    List<Restaurant> findByCityIgnoreCaseAndAverageRating(Optional<Integer> id, Optional<String> city);
}
