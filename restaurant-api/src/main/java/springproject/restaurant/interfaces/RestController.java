package springproject.restaurant.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springproject.restaurant.application.RestaurantService;
import springproject.restaurant.domain.MenuItem;
import springproject.restaurant.domain.MenuItemRepository;
import springproject.restaurant.domain.Rest;
import springproject.restaurant.domain.RestRepository;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private RestaurantService restaurantService;

//    이제는 RestaurantService만을 가지고 메뉴와 레스토랑을 처리할 것이기 때문에, RestRepository와 MenuItemRepository는 쓰이지 않는다.

//    @Autowired // 알아서 RestRepository로 가서 객체를 생성해준다. (구현체가 아닌 interface를 쓴다.)
//    private RestRepository restaurantRepository;
//
//    @Autowired
//    private MenuItemRepository menuItemRepository;

    @GetMapping("/restaurants")
    public List<Rest> list(){
        List<Rest> restaurants = restaurantService.getRestaurants();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Rest detail(@PathVariable("id") Long id){

        // 기본 레스토랑 정보 + 메뉴 정보 모두 제공
        Rest restaurant = restaurantService.getRestaurant(id);

        // 일일히 객체를 생성해서 ArrayList에 넣는 것이 아니라 따로 repository를 만들어 거기에서 찾는다.
//        Rest restaurant = restaurantRepository.finalById(id);
//
//        List<MenuItem> meuItems = menuItemRepository.findAllByRestaurantId(id);
//        restaurant.setMenuItems(meuItems);

//        restaurant.addMenuItem(new MenuItem("Kimchi"));

        return restaurant;
    }

}
