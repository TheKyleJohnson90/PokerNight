package com.kdjstudios.pokernight;

public class Player {
	public int pot;
	public int bet;
	
	Player(){
		pot = 100;
		bet = 2;
	}
	public boolean calcBet(int c, int b){
		int coinvalue=0;
		if(c==0)
			coinvalue=5;
		else if(c==1)
			coinvalue=10;
		else if(c==2)
			coinvalue=25;
		else if(c==3)
			coinvalue=100;
		bet=(coinvalue)*(b+1);
		
		if (bet<=pot)
			return true;
		return false;
	}

}
