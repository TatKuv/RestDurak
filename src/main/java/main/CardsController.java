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

    public ResponseEntity ArrayList(@RequestBody HashMap <String,String> paramsFromFront) {
        // return "paramsFromFront";
        if (paramsFromFront.get("method").equals("start_game")) {            
            return new ResponseEntity(GamePlay.startGame(paramsToFront), HttpStatus.OK);
        }
        else if (paramsFromFront.get("method").equals("step") && GamePlay.isSameId(paramsToFront, paramsFromFront)) {
            
        }
        return null;

    }


}
