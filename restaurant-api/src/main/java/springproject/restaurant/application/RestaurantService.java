package springproject.restaurant.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springproject.restaurant.domain.Rest;
import springproject.restaurant.domain.RestRepository;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestRepository restaurantRepository;

    public RestaurantService(RestRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Rest> getRestaurants() {
        List<Rest> restaurants = restaurantRepository.finalAll();

        return restaurants;
    }

    public Rest getRestaurant(Long id){
        Rest restaurant = restaurantRepository.finalById(id);

        return restaurant;
    }

}
