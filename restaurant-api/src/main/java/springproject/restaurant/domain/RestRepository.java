package springproject.restaurant.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestRepository extends CrudRepository<Rest, Long> {
    List<Rest> findAll();

    Optional <Rest> findById(Long id);

    Rest save(Rest restaurant);
}
