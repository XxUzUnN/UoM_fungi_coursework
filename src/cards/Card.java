package cards;

public class Card {
    protected CardType type;
    protected String cardName;

    public Card(CardType type, String cardName){
        this.type = type;
        this.cardName = cardName;
    }

    public static CardType getType(){
        return null;
    }

    public static String getName(){
        return null;
    }
}
