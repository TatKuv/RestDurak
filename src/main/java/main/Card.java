package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Card {
    private String masti;
    private String name;
    private int rank;

    Card (){}

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

    boolean isSameRank (ArrayList<Card> a){            
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
        Collections.shuffle(cards); 
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

    static HashMap <String, ArrayList> startGame(HashMap <String, ArrayList> paramsToFront) {

        // HashMap <String, ArrayList> paramsToFront = new HashMap <String, ArrayList>();
        CardsActions action = new CardsActions();
        ArrayList <Card> cards = new ArrayList<>();
        ArrayList<Card> upTable = new ArrayList<>();
        ArrayList<Card> downTable = new ArrayList<>();  
        ArrayList<String> state = new ArrayList<>();   
        
        state.add("player_attak");        

        ArrayList<String> userId = new ArrayList<>();
        double id = Math.random();
        userId.add(Double.toString(id));

        cards = CardsActions.createCards(cards);
                                
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

        for (int j = 102,i=0;i<cards.size();i++){
            // Card minTrump = cards.get(i);
            Card checker = new Card (j,trumpCard.getMasti()); 

            if (checker.isSameRank(cards)) {j++;}            
            else { 
                if (checker.isSameRank(compHand)) {
                    System.out.println("Комп ходит первый");
                    turn.compAttak (playerHand,compHand,table,cards);                    
                } 
                else {
                     turn.humanAttak(playerHand,compHand,table,cards);
                } 
            }
        }
       
        paramsToFront.put("player_hand", playerHand);
        paramsToFront.put("up_table", upTable);
        paramsToFront.put("down_table", downTable);
        paramsToFront.put("trump_cards", trumpCards);
        paramsToFront.put("deck", deck);
        paramsToFront.put("comp_hand", quantaty);
        paramsToFront.put("user_id", userId);
        paramsToFront.put("state", state);

        return paramsToFront;
    }

    static void step (HashMap <String, ArrayList> paramsToFront, HashMap <String, ArrayList> paramsFromFront){

    }

}



class Turns {
    static void humanAttak (ArrayList<Card> playerHand,ArrayList<Card> compHand, ArrayList<Card> table, ArrayList <Card> cards){
        
        CardsActions action = new CardsActions();
        Card move = new Card();    
        System.out.println("");
        
        if (table.size() == 0){   
            
            System.out.println("Ваш ход");
            action.takeCards(playerHand, cards);
            action.takeCards(compHand, cards);
            System.out.print("Ваши карты: ");
            action.showCards(playerHand);
            int number = c.humanInput(playerHand)-1;
            move = playerHand.get(number);     
            
            table.add(move);
            playerHand.remove(move);
            if (action.isWinner(playerHand,cards)){
                System.out.println("Победа");
                    return;
            }
            compDefeat(playerHand,compHand,table,cards);

        }
        else { 
            System.out.print("На столе: ");
            action.showCards(table); 
            Card take = new Card();
            take.getName = "Бита";
            playerHand.add(0, take);

            System.out.print("Ваши карты: ");
            action.showCards(playerHand); 
            int number = action.humanInput(playerHand);
            move = playerHand.get(number);
            Card moveTrump = new Card (move.getRank-100,null);         
            
            if (move.equals(playerHand.get(0))){
                playerHand.remove(playerHand.get(0));
                table.clear();
                // c.takeCards(compHand,cards);
                compAttak(playerHand,compHand,table,cards);
            }

            else if (move.isSameCard(table) || moveTrump.isSameCards(table)){                
                table.add(move);
                playerHand.remove(move);
                playerHand.remove(playerHand.get(0));
                
                if (action.isWinner(playerHand,cards)){
                    System.out.println("Победа");
                    return;
                }
                compDefeat(playerHand,compHand,table,cards);            
            }

            else {
                System.out.println("Это не по правилам, выбери другую");
                playerHand.remove(0);
                humanAttak(playerHand,compHand, table,cards);
            }                 
        }
    }

    static void humanDefeat (ArrayList<Card> playerHand,ArrayList<Card> compHand, ArrayList<Card> table,ArrayList <Card> cards){
        CardsActions action = new CardsActions();
        Card a = table.get(table.size()-1);
        Card take = new Card();
        take.getName = "Забрать";
        playerHand.add(0, take); 
        System.out.println("");
        Card move = new Card();     
        System.out.print("На столе: ");
        action.showCards(table);
        System.out.print("Ваши карты: ");
        action.showCards(playerHand);                  
        
        // Scanner scan = new Scanner(System.in);    // Вывести 
        int number = action.humanInput(playerHand);            
        
        move = playerHand.get(number); // эксепшн
               

        if (number<playerHand.size() && move.equals(playerHand.get(0))){
            playerHand.remove(0);
            playerHand.addAll(table);
            table.clear();
            compAttak(playerHand,compHand,table,cards);      
        }

        else if (number<playerHand.size() && a.isBiggerCard(move)){
            
            table.add(move);
            playerHand.remove(move);
            playerHand.remove(0);

            if (action.isWinner(playerHand,cards)){
                    System.out.println("Победа");
                    return;
                }
            compAttak(playerHand,compHand,table,cards);
        }
        else {
            System.out.println("Это не по правилам, выбери другую");
            playerHand.remove(0);
            humanDefeat(playerHand,compHand, table,cards);
        }
    }



    static void compAttak (ArrayList<Card> playerHand,ArrayList<Card> compHand, ArrayList<Card> upTable,ArrayList<Card> upTable, ArrayList <Card> cards){
        CardsActions action = new CardsActions();
        Card cardToTheTable = new Card();   

        if (upTable.size()==0){
            action.takeCards(playerHand, cards);
            action.takeCards(compHand, cards);           
            cardToTheTable = action.getMinCard(compHand);                        
        }
        else { 
            for (Card a : compHand){ 
                if (a.isSameRank(upTable) || a.isSameRank(downTable)){               
                    cardToTheTable = a;
                    break;            
                }
            }
        } 

        if (cardToTheTable.getName() == null){
            upTable.clear();
            System.out.println("Бита");            
            humanAttak(playerHand,compHand,upTable,cards);        
        }
        else {
            upTable.add(cardToTheTable);
            compHand.remove(cardToTheTable);            
            if (action.isWinner(compHand,cards)){
                    // System.out.println("Лузер"); ДОБАВИТЬ КЛЮЧЬ WINNER ДЛЯ ФРОНТА - ФАЛСЕ
                    return;
            }
            humanDefeat(playerHand,compHand,upTable,cards);             
        }
    }

    static void compDefeat (ArrayList<Card> playerHand,ArrayList<Card> compHand, ArrayList<Card> table, ArrayList <Card> cards){
        CardsActions action = new CardsActions();
        ArrayList <Card> beatCards = new ArrayList <Card>();
        Card cardToTheTable = new Card();

        Card a = table.get(table.size()-1);

        for (int i = 0;i<compHand.size(); i++){
            if (a.isBiggerCard(compHand.get(i))){
                beatCards.add(compHand.get(i));
            }            
        }

        if (beatCards.size() == 0){
            compHand.addAll(table);
            table.clear(); 
            System.out.print("Беру!");
            humanAttak(playerHand,compHand,table,cards);
        }
        else {
            cardToTheTable = action.getMinCard(beatCards); 
            table.add(cardToTheTable);
            compHand.remove(cardToTheTable);
           
            if (c.isWinner(compHand,cards)){
                    System.out.println("Лузер");
                    return;
            }
            humanAttak(playerHand, compHand, table,cards);
        }  
    }
}