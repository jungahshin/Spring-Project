package springproject.restaurant.interfaces;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import springproject.restaurant.domain.MenuItemRepository;
import springproject.restaurant.domain.MenuItemRepositoryImpl;
import springproject.restaurant.domain.RestRepository;
import springproject.restaurant.domain.RestRepositoryImpl;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//control+option+o -> 쓸데 없는 것들을 다 없애 준다.
@RunWith(SpringRunner.class)
@WebMvcTest(RestController.class)
public class RestControllerTest {

    @Autowired
    private MockMvc mvc;

    // SpyBeam : 의존성 주입!(Spring의 특징) -> 사용해야 하는 객체를 다양하게 변경할 수 있다.
    // 기존에 controller는 직접적으로 repository에 의존을 하고 있었는데 이러한 의존성을 분리해주었다.
    // 직접 구현체인 'RestRepositoryImpl'를 만들고 그 안에 있던 메소드를 interface인 'RestRepository'로 분리해주었다.
    @SpyBean(RestRepositoryImpl.class)
    private RestRepository restaurantRepository;

    @SpyBean(MenuItemRepositoryImpl.class)
    private MenuItemRepository menuItemRepository;

    @Test
    public void list() throws Exception {
        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\"")
                ));

    }

    @Test
    public void detail() throws Exception {
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

}