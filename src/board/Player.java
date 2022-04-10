package board;

import cards.Card;
import cards.CardType;
import cards.Pan;
import cards.Stick;

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
        for(int i = 0; i < stick; i++){
            getDisplay().add(new Stick());
        }
    }

    public void removeSticks(int stick) {
        sticks -= stick;
        for (int i = 0; i < stick; i++) {
            for(int j = 0; j < getDisplay().size(); j++){
                if (getDisplay().getElementAt(j).getType() == CardType.STICK){
                    getDisplay().removeElement(j);
                    break;
                }
            }
        }
    }

    public Hand getHand(){
        return h;
    }

    public Display getDisplay(){
        return d;
    }

    public void addCardtoHand(Card card){
        if (card.getType()== CardType.BASKET) {
            addCardtoDisplay(card);
            handlimit+=2;
        }else{
            h.add(card);
        }

    }

    public void addCardtoDisplay(Card card){
        d.add(card);
    }

    public boolean takeCardFromTheForest(int position){
        if (position < 1 || position > 8) {
            return false;
        }
        Card card = Board.getForest().getElementAt(8 - position);
        CardType type = card.getType();
        if (type == CardType.BASKET) {
            if (position > 2) {
                if (getStickNumber() < position - 2) {
                    return false;
                }
                removeSticks(position - 2);
            }
            this.handlimit += 2;
            addCardtoDisplay(card);
        } else if (type == CardType.STICK) {
            if (position > 2) {
                if (getStickNumber() < position - 2) {
                    return false;
                }
                removeSticks(position - 2);
            }
            addSticks(1);
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
            addCardtoHand(card);
        }
        Board.getForest().removeCardAt(position);
        return true;
    }

    public boolean takeFromDecay() {
        int decaySize = Board.getDecayPile().size();
        int numBasket = 0;
        if (getHand().size()>handlimit) {
            return false;
        }
        for (int i = 0; i < decaySize; i++) {
            if (Board.getDecayPile().get(i).getType() == CardType.BASKET) {
                numBasket++;
            }
        }
        if (handlimit != getHand().size()) {
            if (getHand().size() + Board.getDecayPile().size() - numBasket > handlimit + numBasket * 2) {
                return false;
            }
            for (int i = 0; i < decaySize; i++) {
                if (Board.getDecayPile().get(0).getType() == CardType.BASKET) {
                    addCardtoDisplay(Board.getDecayPile().remove(0));
                    handlimit += 2;
                } else if (Board.getDecayPile().get(0).getType() == CardType.STICK) {
                    addSticks(1);
                    Board.getDecayPile().remove(0);
                } else {
                    addCardtoHand(Board.getDecayPile().remove(0));
                }
            }
            return true;
        } else {
            if (numBasket == 0) {
                return false;
            } else if (numBasket == 1) {
                if (Board.getDecayPile().size() == 4) {
                    return false;
                }
            }
            for (int i = 0; i < decaySize; i++) {
                if (Board.getDecayPile().get(0).getType() == CardType.BASKET) {
                    addCardtoDisplay(Board.getDecayPile().remove(0));
                    System.out.println("before: " + handlimit);
                    handlimit += 2;
                    System.out.println("after: " + handlimit);
                } else if (Board.getDecayPile().get(0).getType() == CardType.STICK) {
                    addSticks(1);
                    Board.getDecayPile().remove(0);
                } else {
                    addCardtoHand(Board.getDecayPile().remove(0));
                }
            }
            return true;
        }
    }

    public boolean cookMushrooms(ArrayList<Card> cards){

        return false;
    }

    public boolean sellMushrooms(String name, int number){

        return false;
    }
    public boolean putPanDown(){
        boolean result = false;
        int panPosition = 0;
        for(int i = 0; i < this.h.size(); i++){
            if(this.h.getElementAt(i).getType() == CardType.PAN){
                result = true;
                panPosition = i;
            }
        }
        if(!result){
            return false;
        }
        else{
            addCardtoDisplay(this.h.removeElement(panPosition));
        }
        return true;
    }

}
