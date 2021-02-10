package main;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/cards")
public class CardsController {

    @PostMapping(value = "/c", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })

    public ResponseEntity ArrayList(@RequestBody ParamsFromFront paramsFromFront) {
        // return "paramsFromFront";
        if (paramsFromFront.getMethod().equals("start_game")) {
            ArrayList<Card> cards = new ArrayList<>();
            cards.add(new Card(2, "piki"));
            cards.add(new Card(3, "piki"));
            return new ResponseEntity(cards, HttpStatus.OK);
        }

        return null;

    }

    // } //создать метод который возвращает карты
    // Класс для хранения состояний, тек. колода??

}
