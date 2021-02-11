package main;

import java.util.ArrayList;
import java.util.HashMap;

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

    HashMap <String, ArrayList> paramsToFront = new HashMap <String, ArrayList>();

    @PostMapping(value = "/c", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })

    public ResponseEntity ArrayList(@RequestBody ParamsFromFront paramsFromFront) {
        // return "paramsFromFront";
        if (paramsFromFront.getMethod().equals("start_game")) {            
            return new ResponseEntity(GamePlay.startGame(), HttpStatus.OK);
        }
        else if (paramsFromFront.getMethod().equals("step") && GamePlay.isSameCard(paramsFromFront,paramsToFront)){
            
        }
        return null;

    }


}
