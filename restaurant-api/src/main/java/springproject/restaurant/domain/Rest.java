package springproject.restaurant.domain;

import org.springframework.cglib.core.TypeUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rest {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String city;

    @Transient
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    public Rest(){ }

    public Rest(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public Rest(Long id, String name, String city) {
        this.id = id;
        this.city = city;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
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
