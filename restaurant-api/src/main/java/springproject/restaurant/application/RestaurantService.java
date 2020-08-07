package springproject.restaurant.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springproject.restaurant.domain.MenuItem;
import springproject.restaurant.domain.MenuItemRepository;
import springproject.restaurant.domain.Rest;
import springproject.restaurant.domain.RestRepository;

import java.util.List;

// RestaurantService는 웹이 어떻게 작동하는 지에 대해 알 필요 없이, 단순히 repository를 이용해서 어떠한 결과를 돌려주는 'application'의 기능을 하고 있다.
@Service
public class RestaurantService {

    @Autowired
    RestRepository restaurantRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    public RestaurantService(RestRepository restaurantRepository, MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<Rest> getRestaurants() {
        List<Rest> restaurants = restaurantRepository.finalAll();

        return restaurants;
    }

    public Rest getRestaurant(Long id){
        Rest restaurant = restaurantRepository.finalById(id);

        List<MenuItem> meuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(meuItems);

        return restaurant;
    }

    public void addRestaurant(Rest restaurant) {
        //
    }
}
