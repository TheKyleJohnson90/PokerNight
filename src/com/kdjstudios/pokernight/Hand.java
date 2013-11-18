package com.kdjstudios.pokernight;

public class Hand {
	private Card[] cards;
	HandEval eval;
	
	Hand(Deck d)
	{
		cards = new Card[5];
		for (int x=0; x<5; x++)
		{
			cards[x] = d.drawFromDeck();
		}
		eval = new HandEval(cards);
	}
	public void newHand(boolean card[],Deck d){
		for (int x=0; x<5; x++)
		{
			if(card[x]==true)//if we need a new card
				cards[x] = d.drawFromDeck();
		}
		eval = new HandEval(cards);
		
	}
	public String displayCard(int x){
		return cards[x].toString();
		
	}
	public String display()
	{
		
		return eval.getPokerHandAsString();
		
	}
	public String compareTo(Hand that)
	{
		double p1Value,p2Value;
		p1Value = eval.getPokerHandAsValued();
		p2Value = that.eval.getPokerHandAsValued();
		if (p1Value > p2Value) {
			return "Player 1 wins";
		} else if (p2Value > p1Value) {
			return "Player 2 wins";
		} else {
			return "Tie";
		}
	}
}