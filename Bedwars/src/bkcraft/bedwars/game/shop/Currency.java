package bkcraft.bedwars.game.shop;

import java.util.ArrayList;
import java.util.HashMap;

public class Currency {

    private int Iron;
    private int Gold;
    private int Diamonds;
    private int Emeralds;

    public Currency(int IRON, int GOLD, int DIAMONDS, int EMERALDS) {
	this.Iron = IRON;
	this.Gold = GOLD;
	this.Diamonds = DIAMONDS;
	this.Emeralds = EMERALDS;
    }

    public boolean has(Currency currency) {
	return (this.Iron >= currency.Iron && this.Gold >= currency.Gold && this.Diamonds >= currency.Diamonds
		&& this.Emeralds >= currency.Emeralds);
    }

    public HashMap<String, Integer> differenceTo(Currency currency) {
	HashMap<String, Integer> difference = new HashMap<String, Integer>();
	if (this.Iron - currency.Iron < 0)
	    difference.put("Iron", -(this.Iron - currency.Iron));
	if (this.Gold - currency.Gold < 0)
	    difference.put("Gold", -(this.Gold - currency.Gold));
	if (this.Diamonds - currency.Diamonds < 0)
	    difference.put("Diamond", -(this.Diamonds - currency.Diamonds));
	if (this.Emeralds - currency.Emeralds < 0)
	    difference.put("Emerald", -(this.Emeralds - currency.Emeralds));
	return difference;
    }

    public String toString() {
	ArrayList<String> list = new ArrayList<String>();
	if(this.Iron != 0) list.add(this.Iron + " Iron");
	if(this.Gold != 0) list.add(this.Gold + " Gold");
	if(this.Diamonds != 0) list.add(this.Diamonds + " Diamond");
	if(this.Emeralds != 0) list.add(this.Emeralds + " Emerald");

	return String.join(", ", list);
    }

    public int getIron() {
        return Iron;
    }

    public void setIron(int iron) {
        Iron = iron;
    }

    public int getGold() {
        return Gold;
    }

    public void setGold(int gold) {
        Gold = gold;
    }

    public int getDiamonds() {
        return Diamonds;
    }

    public void setDiamonds(int diamonds) {
        Diamonds = diamonds;
    }

    public int getEmeralds() {
        return Emeralds;
    }

    public void setEmeralds(int emeralds) {
        Emeralds = emeralds;
    }
}
