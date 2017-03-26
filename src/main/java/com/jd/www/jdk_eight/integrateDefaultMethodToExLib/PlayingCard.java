package com.jd.www.jdk_eight.integrateDefaultMethodToExLib;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:17/2/14 下午5:08</li>
 * <li>function:</li>
 * </ul>
 */
public class PlayingCard implements Card {

    private Card.Rank rank;
    private Card.Suit suit;

    public PlayingCard(Card.Rank rank,Card.Suit suit){
        this.rank = rank;
        this.suit = suit;
    }


    @Override
    public Suit getSuit() {
        return suit;
    }

    @Override
    public Rank getRank() {
        return rank;
    }

    @Override
    public int compareTo(Card o) {
        return this.hashCode() - o.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  Card){
            if(((Card)obj).getRank() == this.rank && ((Card)obj).getSuit()==this.suit){
                return true;
            }else
                return false;
        }else
            return false;
    }

    @Override
    public int hashCode() {
        return ((suit.value()-1)*13)+rank.value();
    }

    @Override
    public String toString() {
         return this.rank.text()+" of "+this.suit.text();
    }


}
