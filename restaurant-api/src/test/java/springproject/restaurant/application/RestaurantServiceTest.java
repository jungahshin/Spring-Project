package springproject.restaurant.application;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import springproject.restaurant.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    // Autowired를 통해 객체를 주입할 수 있지만, 일반적인 test환경에서는 의존 관계를 주입할 수 가 없다.
    // 따라서, 직접 restaurantService가 repository를 연결할 수 있도록 만들어줘야 한다.
    // @Before -> 모든 test가 실행되기 전에 반드시 실행되는 부분이다.
    @Before
    public void setUp(){
        //가짜 객체(@Mock)들을 초기화한다.
        MockitoAnnotations.initMocks(this);

        // 마찬가지로, RestaurantService에서 Repository의 가짜 객체를 만들어서 사용한다.
//        restaurantRepository = new RestRepositoryImpl();
//        menuItemRepository = new MenuItemRepositoryImpl();


        mockRestaurantRepository();
        mockMenuItemRepository();

        restaurantService = new RestaurantService(
                restaurantRepository ,menuItemRepository);

    }

    private void mockRestaurantRepository() {
        List<Rest> restaurants = new ArrayList<>();
        Rest restaurant = new Rest(1004L, "Bob zip", "Seoul");
        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);

        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.of(restaurant));
    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Kimchi"));

        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
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

    @Test
    public void addRestaurant(){
        Rest restaurant = new Rest("BeRyong", "Seoul");
        Rest saved = new Rest(1234L, "BeRyong", "Seoul");

        given(restaurantRepository.save(any())).willReturn(saved);

        Rest created = restaurantService.addRestaurant(restaurant);

        assertThat(created.getId(), is(1234L));
    }

}