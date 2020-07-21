package springproject.restaurant.domain;

import java.util.ArrayList;
import java.util.List;

public class RestRepository {

    private List<Rest> restaurants = new ArrayList<>();

    // 생성자
    public RestRepository(){
        restaurants.add(new Rest(1004L, "Bob zip", "Seoul"));
        restaurants.add(new Rest(2020L, "Cyber Food", "Seoul"));
    }

    public List<Rest> finalAll() {
        return restaurants;
    }

    public Rest finalById(Long id) {
        return restaurants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null); // id값과 일치하는 객체가 없다면 그냥 null로 반환해준다.
    }
}
