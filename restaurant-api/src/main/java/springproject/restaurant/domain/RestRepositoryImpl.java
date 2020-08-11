package springproject.restaurant.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestRepositoryImpl implements RestRepository {

    private List<Rest> restaurants = new ArrayList<>();

    // 생성자
    public RestRepositoryImpl(){
        restaurants.add(new Rest(1004L, "Bob zip", "Seoul"));
        restaurants.add(new Rest(2020L, "Cyber Food", "Seoul"));
    }

    @Override
    public List<Rest> finalAll() {
        return restaurants;
    }

    @Override
    public Rest finalById(Long id) {
        return restaurants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null); // id값과 일치하는 객체가 없다면 그냥 null로 반환해준다.
    }

    @Override
    public Rest save(Rest restaurant) {
        restaurant.setId(1234);
        restaurants.add(restaurant);
        return restaurant;
    }
}
