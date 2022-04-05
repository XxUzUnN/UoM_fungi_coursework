package board;

import cards.Card;

import java.util.ArrayList;

public class CardList {
    private ArrayList<Card> cList;

    public CardList(){

    }

    public void add(Card add){

    }

    public int size(){
        return 0;
    }

    public Card getElementAt(int index){
        return cList.get(index);
    }

    public Card removeCardAt(int index){
        return cList.remove(index);
    }
}
