package com.jd.www.jdk_eight.integrateDefaultMethodToExLib;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:17/2/14 下午5:05</li>
 * <li>function:</li>
 * </ul>
 */
public interface Deck {
    List<Card> getCards();
    Deck deckFactory();
    int size();
    void addCard(Card card);
    void addCards(List<Card> cards);
    void addDeck(Deck deck);
    void shuffle();
    void sort();
    void sort(Comparator<Card> c);
    String deckToString();

    Map<Integer, Deck> deal(int players, int numberOfCards)
            throws IllegalArgumentException;
}
