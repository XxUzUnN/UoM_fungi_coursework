package board;

import cards.Card;
import cards.CardType;
import cards.Pan;

import java.util.ArrayList;

public class Player {
    private Hand h;
    private Display d;
    private int score;
    private int handlimit;
    private int sticks;

    public Player(){
        h = new Hand();
        d = new Display();
        score = 0;
        handlimit = 8;
        sticks = 0;
        d.add(new Pan());
    }

    public int getScore(){
        return score;
    }

    public int getHandLimit() {
        return handlimit;
    }

    public int getStickNumber(){
        return sticks;
    }

    public void addSticks(int stick) {
        sticks += stick;
    }

    public void removeSticks(int stick) {
        sticks -= stick;
    }

    public Hand getHand(){
        return h;
    }

    public Display getDisplay(){
        return d;
    }

    public void addCardtoHand(Card card){
        h.add(card);
    }

    public void addCardtoDisplay(Card card){
        d.add(card);
    }

    public boolean takeCardFromTheForest(int position){
        if (position < 1 || position > 8) {
            return false;
        } else {
            Card card = Board.getForest().getElementAt(8 - position);
            CardType type = card.getType();
            if (type == CardType.BASKET) {
                if (position > 2) {
                    if (getStickNumber() < position - 2) {
                        return false;
                    }
                    sticks -= position - 2;
                }
                handlimit += 2;
                this.d.add(card);
            } else if (type == CardType.STICK) {
                if (position > 2) {
                    if (getStickNumber() < position - 2) {
                        return false;
                    }
                    sticks -= position - 2;
                }
                sticks += 1;
            } else {
                if (h.size() == handlimit) {
                    return false;
                }
                if (position > 2) {
                    if (getStickNumber() < position - 2) {
                        return false;
                    }
                    sticks -= position - 2;
                }
                this.h.add(card);
            }
            Board.getForest().removeCardAt(position);
            return true;
        }
    }

    public boolean takeFromDecay(){
        boolean result = false;
        for(int i = 0; i < Board.getDecayPile().size(); i++){
            if(Board.getDecayPile().get(i).getType() == (CardType.BASKET)){
                result = true;
            }
            if(result){
                i += 1;
            }
            else{
                break;
            }
        }
        if(this.h.size() == this.handlimit && result){
            return false;
        }
        else {
            if (Board.getDecayPile().size() == 4) {
                this.h.add(Board.getDecayPile().get(0));
            } else if (Board.getDecayPile().size() == 3 && !result) {
                this.h.add(Board.getDecayPile().get(1));
            } else if (Board.getDecayPile().size() == 4 && !result) {
                this.h.add(Board.getDecayPile().get(2));
            }
        }
        return true;
    }

    public boolean cookMushrooms(ArrayList<Card> cards){

        return false;
    }

    public boolean sellMushrooms(String name, int number){

        return false;
    }
    public boolean putPanDown(){

        return false;
    }

}
