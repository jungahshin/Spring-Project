package springproject.restaurant.domain;

import org.springframework.cglib.core.TypeUtils;

import java.util.ArrayList;
import java.util.List;

public class Rest {

    private String name;
    private long id;
    private String city;
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    public Rest(){ }

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

    public List<MenuItem> getMenuItems(){
        return menuItems;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);

    }

    public void setMenuItems(List<MenuItem> meuItems) {
        for(MenuItem menuItem:meuItems){
            addMenuItem(menuItem);
        }
    }
}
