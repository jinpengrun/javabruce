package com.jd.www.designmode.spring.adapter.commonadapter;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/20 下午1:53</li>
 * <li>function:</li>
 * </ul>
 *
 * adapter
 */
public class PegAdapter extends  SquarePeg {
    private RoundPeg roundPeg;

    public PegAdapter(RoundPeg roundPeg){
        this.roundPeg = roundPeg;
    }

    public PegAdapter(){}


    public void insterIntoHole(String str){
        roundPeg.insterIntohole(str);
    }


    public static void main(String[] args) {
        RoundPeg roundPeg1 = new RoundPeg();
        PegAdapter adapter = new PegAdapter(roundPeg1);

        adapter.insterIntoHole("RoundPeg.........");

        PegAdapter adapter1 = new PegAdapter();

        adapter1.instert("testtest");
    }


}
