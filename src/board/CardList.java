package board;

import cards.Card;

import java.util.ArrayList;

public class CardList {
    private ArrayList<Card> cList;

    public CardList(){
        cList = new ArrayList<Card>();
    }

    public void add(Card add){
        cList.add(add);
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
