package com.jd.www.jdk_eight.integrateDefaultMethodToExLib;

import java.util.Comparator;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:17/2/15 下午2:38</li>
 * <li>function:</li>
 * </ul>
 */
public class SortByRankThenSuit implements Comparator<Card> {


    @Override
    public int compare(Card firstCard, Card secondCard) {
        int compVal = firstCard.getRank().value() - secondCard.getRank().value();
        if(compVal!=0){
            return compVal;
        }else
            return firstCard.getSuit().value()-secondCard.getSuit().value();
    }
}
