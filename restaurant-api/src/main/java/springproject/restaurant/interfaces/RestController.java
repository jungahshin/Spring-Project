package springproject.restaurant.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springproject.restaurant.domain.Rest;
import springproject.restaurant.domain.RestRepository;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired // 알아서 RestRepository로 가서 객체를 생성해준다. (구현체가 아닌 interface를 쓴다.)
    private RestRepository repository;

    @GetMapping("/restaurants")
    public List<Rest> list(){
        List<Rest> restaurants = repository.finalAll();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Rest detail(@PathVariable("id") Long id){

        // 일일히 객체를 생성해서 ArrayList에 넣는 것이 아니라 따로 repository를 만들어 거기에서 찾는다.
        Rest restaurant = repository.finalById(id);

        return restaurant;
    }

}
