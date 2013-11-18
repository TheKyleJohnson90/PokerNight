package com.kdjstudios.pokernight;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandEval {

	private Card cards[];
	private String pattern;
	private float handValue;

	public short reward = 0;
	public boolean bonus = false;
	
	public HandEval(Card cards[]) {
		this.cards = cards;
		evaluateHand();
	}

	public void evaluateHand() {
		byte tallyValues[] = new byte[13];
		byte tallySuits[] = new byte[4];

		byte fours = 0;
		byte threes = 0;
		byte pair1 = 0;
		byte pair2 = 0;

		pattern = "";		
		
		for (int i=0; i<5; i++) {
			tallyValues[cards[i].getRank()]++;
			tallySuits[cards[i].getSuit()]++;
		}


		for (byte i=12; i>=0; i--) {
			if (tallyValues[i] == 4) {
				fours = i;
			} else if (tallyValues[i] == 3) {
				threes = i;
			} else if (tallyValues[i] == 2) {
				if (pair1 == 0){
					pair1 = i;
					pair1++;
				}
				else
					pair2 = i;
			}
		}

		List<Byte> sortedCardValues = new ArrayList<Byte>();

		for (byte i=0; i<5; i++) {
			sortedCardValues.add((byte) cards[i].getRank());
		}
		Collections.sort(sortedCardValues);

		float tempRank = 0;

		if (sortedCardValues.get(0) + 4 == sortedCardValues.get(4)) {
			pattern = "Straight";
			reward = 5;
			tempRank = 56 + sortedCardValues.get(4);
			for (byte x=0; x<4; x++) {
				if (tallySuits[x] == 5) {
					tempRank += 56;
					pattern += " Flush";
					reward = 110;
				
					if((int)(sortedCardValues.get(4))==12)
						pattern = "Royal Flush";
						reward = 600;
				}
			}
			
		}

		if (fours > 0) {
			tempRank = 98 + fours;
			pattern = "Four of a Kind";
			reward = 25;
		} else if (threes > 0) {
			if (pair1 > 0) {
				tempRank = 84 + threes;
				pattern = "Full House";
				reward = 8;
			} else {
				tempRank = 42 + threes;
				pattern = "Three of a Kind";
				reward = 3;
			}
		} else if (pair1 > 0) {
			if (pair2 > 0) {
				tempRank = 28 + pair1 + (float) pair2 / 100 + (float) sortedCardValues.get(4) / 10000;
				pattern = "Two Pair";
				reward = 1;
			} else {
				tempRank = 14 + pair1 + (float) sortedCardValues.get(4) / 100 + (float) sortedCardValues.get(3) / 10000 + (float) sortedCardValues.get(2) / 1000000;
				pattern = "Pair of " + String.valueOf(pair1);
				if(pair1==1){
					reward = 1;
				}
			}
		} 

		if (tempRank == 0) {
			for (byte i=0; i<4; i++) {
				if (tallySuits[i] == 5) {
					tempRank = 70 + sortedCardValues.get(4) + (float) sortedCardValues.get(3) / 100 + (float) sortedCardValues.get(2) / 10000 + (float) sortedCardValues.get(1) / 1000000 + (float) sortedCardValues.get(0) / 100000000;
					pattern = "Flush";
					//TODO Reward 3X and bonus
					reward = 3;
					bonus =true;
				}
			}
		}

		if ((tempRank == 0)) {
			tempRank = sortedCardValues.get(4) + (float) sortedCardValues.get(3) / 100 + (float) sortedCardValues.get(2) / 10000 + (float) sortedCardValues.get(1) / 1000000 + (float) sortedCardValues.get(0) / 100000000;
			pattern = "High Card";
			reward = 0;
		}

		handValue = tempRank;
	}

	public String getPokerHandAsString() {
		return pattern;
	}

	public double getPokerHandAsValued() {
		return handValue;
	}


}
