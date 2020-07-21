package springproject.restaurant.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/") //http:8080+"/"만 하면 바로 Hello World!가 출력되는 화면이 나온다.
    public String Hello(){
        return "Hello World!!!";
    }

}
