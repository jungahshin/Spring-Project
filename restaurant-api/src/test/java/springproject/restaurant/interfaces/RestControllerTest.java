package springproject.restaurant.interfaces;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import springproject.restaurant.application.RestaurantService;
import springproject.restaurant.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//control+option+o -> 쓸데 없는 것들을 다 없애 준다.
@RunWith(SpringRunner.class)
@WebMvcTest(RestController.class)
public class RestControllerTest {

    @Autowired
    private MockMvc mvc;

    //RestaurantService의 가짜객체를 만들어주었기 때문에 -> 원래 있었던 SpyBean(MenuItemRepositoryImpl, RestRepositoryImpl)가 팔요 없다.
    @MockBean
    private RestaurantService restaurantService;

//    // RestaurantService를 만들어주었기 때문에 RestRepository와 MenuItemRepository가 없어도 된다.
//    @SpyBean(RestaurantService.class)
//    private RestaurantService restaurantService;
//
//    // SpyBeam : 의존성 주입!(Spring의 특징) -> 사용해야 하는 객체를 다양하게 변경할 수 있다.
//    // 기존에 controller는 직접적으로 repository에 의존을 하고 있었는데 이러한 의존성을 분리해주었다.
//    // 직접 구현체인 'RestRepositoryImpl'를 만들고 그 안에 있던 메소드를 interface인 'RestRepository'로 분리해주었다.
//    @SpyBean(RestRepositoryImpl.class)
//    private RestRepository restaurantRepository;
//
//    @SpyBean(MenuItemRepositoryImpl.class)
//    private MenuItemRepository menuItemRepository;

    @Test
    public void list() throws Exception {
        //따라서, controller는 restaurantService를 활용한다는 자체에 집중할 뿐이지, restaurantService가 어떻게 작동하는 지는 controller의 관심사가 아니다!
        List<Rest> restaurants = new ArrayList<>();
        restaurants.add(new Rest(1004L, "Joker", "Seoul"));

        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Joker\"")
                ));

    }

    @Test
    public void detail() throws Exception {
        Rest restaurant1 = new Rest(1004L, "Bob zip", "Seoul");
        Rest restaurant2 = new Rest(2020L, "Cyber Food", "Seoul");

        restaurant1.addMenuItem(new MenuItem("Kimchi"));
        restaurant2.addMenuItem(new MenuItem("Kimchi"));

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);
        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                 ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\"")
                ))
                .andExpect(content().string(
                        containsString("Kimchi"
                )));

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":2020")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber Food\"")
                ))
                .andExpect(content().string(
                        containsString("Kimchi"
                )));
    }

    @Test
    public void create() throws Exception {
        mvc.perform(post("/restaurants"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1234"))
                .andExpect(content().string("{}"));

    }

}