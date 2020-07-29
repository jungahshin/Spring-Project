package springproject.restaurant.application;

import org.junit.Before;
import org.junit.Test;
import springproject.restaurant.domain.*;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    private RestRepository restaurantRepository;

    private MenuItemRepository menuItemRepository;

    // Autowired를 통해 객체를 주입할 수 있지만, 일반적인 test환경에서는 의존 관계를 주입할 수 가 없다.
    // 따라서, 직접 restaurantService가 repository를 연결할 수 있도록 만들어줘야 한다.
    // @Before -> 모든 test가 실행되기 전에 반드시 실행되는 부분이다.
    @Before
    public void setUp(){
        restaurantRepository = new RestRepositoryImpl();
        menuItemRepository = new MenuItemRepositoryImpl();

        restaurantService = new RestaurantService(restaurantRepository ,menuItemRepository);

    }

    @Test
    public void getRestaurants(){
        List<Rest> restaurants = restaurantService.getRestaurants();

        Rest restaurant = restaurants.get(0);
        assertThat(restaurant.getId(), is(1004L));
    }

    @Test
    public void getRestaurant(){
        Rest restaurant = restaurantService.getRestaurant(1004L);

        assertThat(restaurant.getId(), is(1004L));

        MenuItem menuItem = restaurant.getMenuItems().get(0);

        assertThat(menuItem.getName(), is("Kimchi"));
    }


}