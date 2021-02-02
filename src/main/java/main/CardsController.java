package main;

import response.Card;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class CardsController {
    @RequestMapping(value = "/cards", method = RequestMethod.POST)
    @ResponseBody
    public String editWinner( @RequestBody String pojo) {
        System.out.print(pojo);
        return "pysya";
    }
    
    public ResponseEntity turn(@PathVariable int rank) {

        return (
            
        );
    }                                    //создать метод который возвращает карты
                                        // Класс для хранения состояний, тек. колода??


}