package main;

import java.util.ArrayList;
import java.util.Collections;
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

    void startGame (ArrayList <Card> cards){
        
        String [] cardSuit = new String [] {"♥", "♠", "♣", "♦"};        
        int rank = 2;
        int quntatyOfCards = 52; // когда будет меняться поменять и создание карт
        
        for (int i = 0, j = 0; i<quntatyOfCards;i++){
            String masti = cardSuit[j];
            cards.add(new Card(rank,masti));            // SET
            rank ++;
            if (rank==15) { rank = 2; j++;  continue; }
        }  
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

    int humanInput (ArrayList<Card> playerHand) {
        CardsActions c = new CardsActions();
        Scanner scan = new Scanner(System.in);
        if(scan.hasNextInt()) { 
            int number = scan.nextInt();

            if (number>=0 && number < playerHand.size()) {
                return number;
            }
        } 
            
        System.out.println("Введено неверное значение, давай еще раз");
        return c.humanInput(playerHand);
         
    }
}