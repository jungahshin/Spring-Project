package springproject.restaurant.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RestTests {

    @Test
    public void creation(){
        Rest rest = new Rest(1004L, "Bob zip", "Seoul");
        assertThat(rest.getId(), is(1004L));
        assertThat(rest.getName(), is("Bob zip"));
        assertThat(rest.getCity(), is("Seoul"));
    }

    @Test
    public void information(){
        Rest rest = new Rest(1004L, "Bob zip", "Seoul");
        assertThat(rest.getInformation(), is("Bob zip in Seoul"));

    }
}