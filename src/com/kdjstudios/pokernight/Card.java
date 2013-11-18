package com.kdjstudios.pokernight;

public class Card {
	private short rank, suit;

	private static String[] suits = { "\u2661", "\u2660", "\u2662", "\u2663" };
	private static String[] ranks  = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

	Card(short suit, short rank)
	{
		this.rank=rank;
		this.suit=suit;
	}


	public @Override String toString()
	{
		  return ranks[rank] + " of " + suits[suit];
	}

	public short getRank() {
		 return rank;
	}

	public short getSuit() {
		return suit;
	}
}
