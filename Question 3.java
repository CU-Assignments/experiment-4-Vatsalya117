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

class TicketBookingSystem {
    private static final int TOTAL_SEATS = 10;
    private boolean[] seats = new boolean[TOTAL_SEATS];

    public synchronized boolean bookSeat(int seatNumber) {
        if (seatNumber < 0 || seatNumber >= TOTAL_SEATS || seats[seatNumber]) {
            return false;
        }
        seats[seatNumber] = true;
        return true;
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem system;
    private int seatNumber;

    public BookingThread(TicketBookingSystem system, int seatNumber, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        setPriority(priority);
    }

    @Override
    public void run() {
        if (system.bookSeat(seatNumber)) {
            System.out.println(Thread.currentThread().getName() + " successfully booked seat " + seatNumber);
        } else {
            System.out.println(Thread.currentThread().getName() + " failed to book seat " + seatNumber);
        }
    }
}

public class Main {
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

        TicketBookingSystem bookingSystem = new TicketBookingSystem();

        Thread vip1 = new BookingThread(bookingSystem, 2, Thread.MAX_PRIORITY);
        Thread vip2 = new BookingThread(bookingSystem, 3, Thread.MAX_PRIORITY);
        Thread normal1 = new BookingThread(bookingSystem, 2, Thread.NORM_PRIORITY);
        Thread normal2 = new BookingThread(bookingSystem, 4, Thread.NORM_PRIORITY);

        vip1.start();
        vip2.start();
        normal1.start();
        normal2.start();
        
        scanner.close();
    }
}
