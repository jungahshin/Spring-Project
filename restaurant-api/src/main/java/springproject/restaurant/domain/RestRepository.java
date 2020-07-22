package springproject.restaurant.domain;

import java.util.List;

public interface RestRepository {
    List<Rest> finalAll();

    Rest finalById(Long id);
}
