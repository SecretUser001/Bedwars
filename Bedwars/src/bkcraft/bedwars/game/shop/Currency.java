package bkcraft.bedwars.game.shop;

import java.util.HashMap;

public class Currency {

	public int Iron;
	public int Gold;
	public int Diamond;
	public int Emerald;

	public Currency(int IRON, int GOLD, int DIAMOND, int EMERALD) {
		this.Iron = IRON;
		this.Gold = GOLD;
		this.Diamond = DIAMOND;
		this.Emerald = EMERALD;
	}

	public boolean has(Currency currency) {
		return (this.Iron >= currency.Iron && this.Gold >= currency.Gold && this.Diamond >= currency.Diamond
				&& this.Emerald >= currency.Emerald);
	}

	public HashMap<String, Integer> differenceTo(Currency currency) {
		HashMap<String, Integer> difference = new HashMap<String, Integer>();
		if (this.Iron - currency.Iron < 0)
			difference.put("Iron", -(this.Iron - currency.Iron));
		if (this.Gold - currency.Gold < 0)
			difference.put("Gold", -(this.Gold - currency.Gold));
		if (this.Diamond - currency.Diamond < 0)
			difference.put("Diamond", -(this.Diamond - currency.Diamond));
		if (this.Emerald - currency.Emerald < 0)
			difference.put("Emerald", -(this.Emerald - currency.Emerald));
		return difference;
	}
}
