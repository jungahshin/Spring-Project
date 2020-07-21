package springproject.restaurant.domain;

public class Rest {

    private final String name;
    private final long id;
    private final String city;

    public Rest(long id, String name, String city) {
        this.id = id;
        this.city = city;
        this.name = name;
    }

    public Long getId() { return id; }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getInformation() {
        return name+" in "+city;
    }

}
