package greedyTimes;

import java.util.HashMap;
import java.util.Map;

public class Bag {
    private long capacity;
    private long currentWeight;
    private long totalGold;
    private boolean isAddGold;
    private long totalGems;
    private Map<String, Long> gems;
    private long totalCash;
    private Map<String, Long> cash;

    public Bag(long capacity) {
        this.capacity = capacity;
        this.currentWeight = 0;
        this.totalGold = 0;
        this.totalGems = 0;
        this.gems = new HashMap<> ();
        this.totalCash = 0;
        this.cash = new HashMap<> ();
        this.isAddGold = false;
    }
    public void addCash(String currency, long quantity){
        if(hasFreeSpace ( quantity ) && this.totalGems >= this.totalCash + quantity){
            if(!this.cash.containsKey ( currency )){
                this.cash.put ( currency,quantity );
            } else {
                this.cash.put ( currency,this.cash.get ( currency ) + quantity );
            }
            this.totalCash += quantity;
            this.currentWeight += quantity;
        }
    }

    public void addGems(String gem, long quantity){
        if(hasFreeSpace ( quantity ) && this.totalGems + quantity <= this.totalGold){
            if(!this.gems.containsKey ( gem )){
                this.gems.put ( gem,quantity );
            } else {
                this.gems.put ( gem,this.gems.get ( gem ) + quantity );
            }
            this.totalGems += quantity;
            this.currentWeight += quantity;
        }
    }

    public void addGold(long quantity){
        if(this.hasFreeSpace ( quantity )){
            this.totalGold += quantity;
            this.currentWeight += quantity;
            this.isAddGold = true;
        }
    }

    private boolean hasFreeSpace (long quantity){
        return this.currentWeight + quantity <= this.capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder ();
        if(isAddGold){
            sb.append ( "<Gold> $" ).append ( this.totalGold ).append ( System.lineSeparator () );
            sb.append ( "##Gold - " ).append ( this.totalGold ).append ( System.lineSeparator () );
        }

        if(this.gems.size () >0) {
            sb.append ( "<Gem> $" ).append ( this.totalGems ).append ( System.lineSeparator () );
            this.gems.entrySet ().stream ().sorted ( (f, s) -> {
                int result = s.getKey ().compareTo ( f.getKey () );
                if (result == 0) {
                    result = f.getValue ().compareTo ( s.getValue () );
                }
                return result;
            } ).forEach ( entry -> {
                sb.append ( "##" ).append ( entry.getKey () ).append ( " - " ).append ( entry.getValue () ).append ( System.lineSeparator () );
            } );
        }

        if(this.cash.size ()>0) {
            sb.append ( "<Cash> $" ).append ( this.totalCash ).append ( System.lineSeparator () );
            this.cash.entrySet ().stream ().sorted ( (f, s) -> {
                int result = s.getKey ().compareTo ( f.getKey () );
                if (result == 0) {
                    result = f.getValue ().compareTo ( s.getValue () );
                }
                return result;
            } ).forEach ( entry -> {
                sb.append ( "##" ).append ( entry.getKey () ).append ( " - " ).append ( entry.getValue () ).append ( System.lineSeparator () );
            } );
        }

        return sb.toString ();
    }
}
