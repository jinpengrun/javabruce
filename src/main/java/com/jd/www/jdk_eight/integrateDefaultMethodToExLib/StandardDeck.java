package com.jd.www.jdk_eight.integrateDefaultMethodToExLib;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:17/2/14 下午5:18</li>
 * <li>function:</li>
 * </ul>
 */
public class StandardDeck implements Deck {

    private List<Card> entireDeck;

    public StandardDeck(List<Card> existingList) {
        this.entireDeck = existingList;
    }

    public StandardDeck() {
        this.entireDeck = new ArrayList<>();
        for (Card.Suit s : Card.Suit.values()) {
            for (Card.Rank r : Card.Rank.values()) {
                this.entireDeck.add(new PlayingCard(r, s));
            }
        }
    }


    @Override
    public List<Card> getCards() {
        return null;
    }

    @Override
    public Deck deckFactory() {
        return new StandardDeck(new ArrayList<Card>());
    }

    @Override
    public int size() {
        return entireDeck.size();
    }

    @Override
    public void addCard(Card card) {
        entireDeck.add(card);
    }

    @Override
    public void addCards(List<Card> cards) {
        entireDeck.addAll(cards);
    }

    @Override
    public void addDeck(Deck deck) {
        List<Card> listToAdd = deck.getCards();
        entireDeck.addAll(listToAdd);
    }

    @Override
    public void shuffle() {
        Collections.shuffle(entireDeck);
    }

    @Override
    public void sort() {
        Collections.sort(entireDeck);
    }

    @Override
    public void sort(Comparator<Card> c) {
        Collections.sort(entireDeck, c);
    }

    @Override
    public String deckToString() {
        return null;
    }

    @Override
    public Map<Integer, Deck> deal(int players, int numberOfCards) throws IllegalArgumentException {
        int cardsDealt = players * numberOfCards;
        int sizeOfDeck = entireDeck.size();
        if (cardsDealt > sizeOfDeck) {
            throw new IllegalArgumentException(
                    "Number of players (" + players +
                            ") times number of cards to be dealt (" + numberOfCards +
                            ") is greater than the number of cards in the deck (" +
                            sizeOfDeck + ").");
        }

        //entireDeck.stream().collect(Collectors.groupingBy())
        return null;
    }

    @Override
    public String toString() {
        return "StandardDeck{" +
                "entireDeck=" + entireDeck +
                '}';
    }

    public static void main(String[] args) {
        StandardDeck standardDeck = new StandardDeck();
        standardDeck.shuffle();


        //this approach is too verbose , it would be better if you could specify what you want to sort
        System.out.println("after shuffled:"+standardDeck.toString());

        standardDeck.sort(new SortByRankThenSuit());

        System.out.println("after sorted:"+standardDeck.toString());

        //simple method
        StandardDeck myDeck = new StandardDeck();
        myDeck.shuffle();
        //sort by rank value  because the interface comparator is a functional interface(函数式接口)
        //you can use a lambda expression as an argument for the sort method ,in this example the lambda expressioncompares two integer values;
        myDeck.sort((firstCard,secondCard)->firstCard.getRank().value()-secondCard.getRank().value());

        System.out.println("sort by simple method:"+myDeck.toString());

        myDeck.shuffle();
        myDeck.sort(Comparator.comparing((card)->card.getRank()));
        System.out.println("after sotred by Comparator.comparing:"+myDeck.toString());

    }
}
