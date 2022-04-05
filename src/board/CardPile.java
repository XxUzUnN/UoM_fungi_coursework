package board;

import cards.Card;

import java.util.ArrayList;

public class CardPile {
    private static ArrayList<Card> cPile;

    public CardPile() {
        cPile = new ArrayList<>();
    }

    public void addCard(Card card) {
        cPile.add(card);
    }

    public Card drawCard() {
        return cPile.remove(0);
    }

    public void shufflePile() {
        for (int i = 0; i < cPile.size(); i++) {
            int r = (int) (Math.random() * cPile.size());
            Card temp = cPile.get(i);
            cPile.set(i, cPile.get(r));
            cPile.set(r, temp);
        }
    }

    public int pileSize() {
        return cPile.size();
    }

    public boolean isEmpty() {
        return cPile.isEmpty();
    }

}
