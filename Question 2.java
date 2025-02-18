import java.util.*;

class Card {
    private String symbol;
    private String name;

    public Card(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Card{" + "symbol='" + symbol + '\'' + ", name='" + name + '\'' + '}';
    }
}

class CardCollection {
    private Collection<Card> cards;

    public CardCollection() {
        cards = new ArrayList<>();
    }

    public void addCard(String symbol, String name) {
        cards.add(new Card(symbol, name));
    }

    public List<Card> findCardsBySymbol(String symbol) {
        List<Card> result = new ArrayList<>();
        for (Card card : cards) {
            if (card.getSymbol().equalsIgnoreCase(symbol)) {
                result.add(card);
            }
        }
        return result;
    }
}

public class CardFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection collection = new CardCollection();

        // Sample data
        collection.addCard("Heart", "Ace of Hearts");
        collection.addCard("Spade", "King of Spades");
        collection.addCard("Heart", "Queen of Hearts");
        collection.addCard("Diamond", "Jack of Diamonds");

        // User input
        System.out.print("Enter symbol to search: ");
        String symbol = scanner.nextLine();
        List<Card> foundCards = collection.findCardsBySymbol(symbol);
        
        if (foundCards.isEmpty()) {
            System.out.println("No cards found for symbol: " + symbol);
        } else {
            System.out.println("Cards found:");
            for (Card card : foundCards) {
                System.out.println(card);
            }
        }

        scanner.close();
    }
}
