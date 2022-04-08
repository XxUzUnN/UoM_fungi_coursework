package board;

import cards.Card;
import cards.CardType;

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
        if(position < 1 || position > 8 || h.size() == handlimit) {
            return false;
        }
        else {
            Card card = Board.getForest().getElementAt(8 - position);
            CardType type = card.getType();
            if(type == CardType.DAYMUSHROOM || type == CardType.NIGHTMUSHROOM) {
                if(position ==1 || position == 2) {
                    this.h.add(card);
                }
                else {
                    if(sticks < position - 2){
                        return false;
                    }
                }
            }
            else if(type == CardType.BASKET) {
                this.d.add(card);
                handlimit += 2;
            }
            else if(type == CardType.STICK) {
                this.sticks += 1;
            }

        }
        Board.getForest().removeCardAt(position);
        return true;
    }

    public boolean takeFromDecay(){

        return false;
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
