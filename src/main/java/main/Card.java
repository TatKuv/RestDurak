package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Card {
    private String masti;
    private String name;
    private int rank;

    Card (int rank, String masti){
        this.rank = rank;
        this.masti = masti;
        this.name = rank + masti; 
    }

    public void setMasti (String masti){
        this.masti = masti;
    }

    public void setRank (int rank){
        this.rank = rank;
    }

    public String getMasti() {
        return(this.masti);
    }

    public String getName() {
        return(this.name);
    }

    public int getRank() {
        return(this.rank);
    }

    boolean isBiggerCard(Card a){
        if (a.rank-this.rank > 86 || (a.masti.equals(this.masti) && a.rank>this.rank)){
            return true;
        }
        return false;        
    }  

    boolean isSameCard (ArrayList<Card> a){            
        for( Card b : a){
            if (b.rank == this.rank || b.rank == this.rank+100){
                return true;
            }
        }
        return false;         
    }

    @Override
    public boolean equals (Object a){
        if (a instanceof Card) {
            Card c2 = (Card)a;
            return (c2.name).equals(this.name);
        }
        return false;
    }
}


class CardsActions {
    void showCards (ArrayList <Card> a){
        if (a.size()!=0){
            for (int i = 0; i < a.size(); i++){ 
            System.out.print (a.get(i).getName() + " ");
            }             
        }
        System.out.println(" ");
    }

    static ArrayList <Card> createCards (ArrayList <Card> cards){
        
        String [] cardSuit = new String [] {"♥", "♠", "♣", "♦"};        
        int rank = 2;
        int quntatyOfCards = 52; // когда будет меняться поменять и создание карт
        
        for (int i = 0, j = 0; i<quntatyOfCards;i++){
            String masti = cardSuit[j];
            cards.add(new Card(rank,masti));            // SET
            rank ++;
            if (rank==15) { rank = 2; j++;  continue; }
        }  
        return cards;
    }
    
    public void takeCards (ArrayList <Card> hand, ArrayList <Card> cards){
        for (hand.size(); hand.size()<6;){ 
           if (cards.size() == 0) {break;}     
           hand.add(cards.get(cards.size()-1));
           cards.remove(cards.size()-1);            
       }    
   } 
   
   Card setTrumpCard (ArrayList <Card> cards){
        Card trumpCard = cards.get(0);  
        for (int i = 0; i<cards.size(); i++){
            Card card = cards.get(i);
            if(card.getMasti().equals(trumpCard.getMasti())){
               card.setRank(card.getRank()+100);
            }           
        }
       return trumpCard;
    }
    
    Card getMinCard (ArrayList <Card> hand){
        Card min = hand.get(0);
        for (int i = 1; i<hand.size(); i++){
            if (min.getRank() > hand.get(i).getRank()) {
                min = hand.get(i);
            }
        }
        return min;
    }   

    boolean isWinner (ArrayList <Card> hand, ArrayList <Card> cards){

        if (hand.size() == 0 && cards.size() == 0){            // Можно вызывать метод картами с руки  
            return true;                                       // и сообщение о победе добавить сюда же
        }
        return false;
    }
    
}

class GamePlay {

    static boolean isSameId (HashMap <String, ArrayList> paramsToFront, HashMap <String, String> paramsFromFront) {
        if(paramsFromFront.containsKey("user_id")){
            String frontId = paramsFromFront.get("user_id");
            String backId = (String) (paramsToFront.get("user_id")).get(0);
            if(frontId.equals(backId)){
                return true;
            }
            return false;
        }
        return false;
    }

    static HashMap <String, ArrayList> startGame() {

        HashMap <String, ArrayList> paramsToFront = new HashMap <String, ArrayList>();
        ArrayList <Card> cards = new ArrayList<>();
        ArrayList<Card> upTable = new ArrayList<>();
        ArrayList<Card> downTable = new ArrayList<>();        
        CardsActions action = new CardsActions();

        ArrayList<String> userId = new ArrayList<>();
        double id = Math.random();
        userId.add(Double.toString(id));

        cards = CardsActions.createCards(cards);
        Collections.shuffle(cards);   
                        
        Card trumpCard = action.setTrumpCard(cards);
        ArrayList <Card> trumpCards = new ArrayList<>();
        trumpCards.add(trumpCard);
        // System.out.println("Козырь:  " + trumpCard.getName());  

        ArrayList <Card> playerHand = new ArrayList<>();
        action.takeCards(playerHand,cards);        
        
        ArrayList <Card> compHand = new ArrayList<>();
        action.takeCards(compHand,cards);

        ArrayList <Integer> quantaty = new ArrayList<>();
        int quantatyComp = compHand.size();
        quantaty.add(quantatyComp);

        ArrayList <Integer> deck = new ArrayList<>();
        int quantatyCards = cards.size();
        deck.add(quantatyCards);
       
        paramsToFront.put("player_hand", playerHand);
        paramsToFront.put("up_table", upTable);
        paramsToFront.put("down_table", downTable);
        paramsToFront.put("trump_cards", trumpCards);
        paramsToFront.put("deck", deck);
        paramsToFront.put("comp_hand", quantaty);
        paramsToFront.put("user_id", userId);

        return paramsToFront;
    }

    static void step (HashMap <String, ArrayList> paramsToFront, HashMap <String, ArrayList> paramsFromFront){

    }

}