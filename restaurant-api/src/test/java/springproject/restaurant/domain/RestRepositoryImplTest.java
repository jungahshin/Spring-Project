package springproject.restaurant.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RestRepositoryImplTest {

    @Test
    public void save(){
        RestRepository repository = new RestRepositoryImpl();

        int oldCount = repository.finalAll().size();

        Rest restaurant = new Rest("BeRyong", "Seoul");
        repository.save(restaurant);

        assertThat(restaurant.getId(), is(1234L));

        int newCount = repository.finalAll().size();

        assertThat(newCount-oldCount, is(1));
    }

}