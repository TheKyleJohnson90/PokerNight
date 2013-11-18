package com.kdjstudios.pokernight;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class PokerActivity extends Activity {
	
	//Global Player Variable
	Player player = new Player();
	
	//Start Up
	@Override	
	public void onCreate(Bundle savedInstanceState) 
	{
		
		//Gird Based Menu
		super.onCreate(savedInstanceState);
		
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		//Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//Get Text Screen
		setContentView(R.layout.activity_poker);
		
		final SeekBar 	coin = (SeekBar) findViewById(R.id.seekCoin);
		final SeekBar	bet = (SeekBar) findViewById(R.id.seekBet);
		
		//Coin Slider Selector and Adjustment
        coin.setOnSeekBarChangeListener(null);
        coin.setMax(3);
        coin.setProgress(0); // Set it to zero so it will start at the left-most edge
        coin.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(!player.calcBet(coin.getProgress(),bet.getProgress())){
                	Toast.makeText(PokerActivity.this, "You do not have enough creidits",Toast.LENGTH_SHORT).show();
                	coin.setProgress(0);
                }
                DisplayBet();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

        });
        
        
		//Bet amount selector and Adjustment
        bet.setOnSeekBarChangeListener(null);
        bet.setMax(39);
        bet.setProgress(0); // Set it to zero so it will start at the left-most edge
        bet.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            	if(!player.calcBet(coin.getProgress(),bet.getProgress())){
            		Toast.makeText(PokerActivity.this, "You do not have enough creidits",Toast.LENGTH_SHORT).show();
                	bet.setProgress(0);
            	}
            	DisplayBet();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

        });
        
        //Deal button interface
      	final Button deal = (Button) findViewById(R.id.deal);//Deal button interactions
        deal.setOnClickListener(new View.OnClickListener() {
        	Deck deck= new Deck();
        	Hand hand;
     		//Hand hand2;
     		int counter=0;
     		
     		
            public void onClick(View v) {
                // Perform action on click   
            	if (player.pot>=player.bet){
	             	if (counter==0){             		
		         		FirstDeal();//Start off with the first hand of cards
	             	}
	             	else if (counter==1){
		         		SecondDeal();	//Second hand of cards         		
	             	}
	             	counter++;
            	}
            }
            //Allows player to select any of the cards to hold and then disables changing bet
            public void enable(){
            	CheckBox p1c0text = (CheckBox) findViewById(R.id.P1C0);
         		p1c0text.setEnabled(true);
         		p1c0text.setChecked(false);
         		CheckBox p1c1text = (CheckBox) findViewById(R.id.P1C1);
         		p1c1text.setEnabled(true);
         		p1c1text.setChecked(false);
         		CheckBox p1c2text = (CheckBox) findViewById(R.id.P1C2);
         		p1c2text.setEnabled(true);
         		p1c2text.setChecked(false);
         		CheckBox p1c3text = (CheckBox) findViewById(R.id.P1C3);
         		p1c3text.setEnabled(true);
         		p1c3text.setChecked(false);
         		CheckBox p1c4text = (CheckBox) findViewById(R.id.P1C4);
         		p1c4text.setEnabled(true);
         		p1c4text.setChecked(false);
         		
         		coin.setEnabled(false);
         		bet.setEnabled(false);
         		/*
         		CheckBox p2c0text = (CheckBox) findViewById(R.id.P2C0);
         		p2c0text.setEnabled(true);
         		p2c0text.setChecked(false);
         		CheckBox p2c1text = (CheckBox) findViewById(R.id.P2C1);
         		p2c1text.setEnabled(true);
         		p2c1text.setChecked(false);
         		CheckBox p2c2text = (CheckBox) findViewById(R.id.P2C2);
         		p2c2text.setEnabled(true);
         		p2c2text.setChecked(false);
         		CheckBox p2c3text = (CheckBox) findViewById(R.id.P2C3);
         		p2c3text.setEnabled(true);
         		p2c3text.setChecked(false);
         		CheckBox p2c4text = (CheckBox) findViewById(R.id.P2C4);
         		p2c4text.setEnabled(true);
         		p2c4text.setChecked(false);
         		*/
            }
            //Disables player to select cards, but enables the betting sliders
            public void disable(){
            	CheckBox p1c0text = (CheckBox) findViewById(R.id.P1C0);
         		p1c0text.setEnabled(false);
         		CheckBox p1c1text = (CheckBox) findViewById(R.id.P1C1);
         		p1c1text.setEnabled(false);
         		CheckBox p1c2text = (CheckBox) findViewById(R.id.P1C2);
         		p1c2text.setEnabled(false);
         		CheckBox p1c3text = (CheckBox) findViewById(R.id.P1C3);
         		p1c3text.setEnabled(false);
         		CheckBox p1c4text = (CheckBox) findViewById(R.id.P1C4);
         		p1c4text.setEnabled(false);
         		//Allow to change bet
         		coin.setEnabled(true);
         		bet.setEnabled(true);
         		/*
         		CheckBox p2c0text = (CheckBox) findViewById(R.id.P2C0);
         		p2c0text.setEnabled(false);
         		CheckBox p2c1text = (CheckBox) findViewById(R.id.P2C1);
         		p2c1text.setEnabled(false);
         		CheckBox p2c2text = (CheckBox) findViewById(R.id.P2C2);
         		p2c2text.setEnabled(false);
         		CheckBox p2c3text = (CheckBox) findViewById(R.id.P2C3);
         		p2c3text.setEnabled(false);
         		CheckBox p2c4text = (CheckBox) findViewById(R.id.P2C4);
         		p2c4text.setEnabled(false);
         		*/
            }
            //Find out which cards to hold
            public boolean[] SelectCards(int player){
            	boolean[] ret ={true,true,true,true,true};
            	if(player==0){
	            	CheckBox p1c0text = (CheckBox) findViewById(R.id.P1C0);
	         		if(p1c0text.isChecked()){
	         			ret[0]=false;
	         		}
	         		CheckBox p1c1text = (CheckBox) findViewById(R.id.P1C1);
	         		if(p1c1text.isChecked()){
	         			ret[1]=false;
	         		}
	         		CheckBox p1c2text = (CheckBox) findViewById(R.id.P1C2);
	         		if(p1c2text.isChecked()){
	         			ret[2]=false;
	         		}
	         		CheckBox p1c3text = (CheckBox) findViewById(R.id.P1C3);
	         		if(p1c3text.isChecked()){
	         			ret[3]=false;
	         		}
	         		CheckBox p1c4text = (CheckBox) findViewById(R.id.P1C4);
	         		if(p1c4text.isChecked()){
	         			ret[4]=false;
	         		}
         		}
            	/*
            	 else{
            	 
         		
	         		CheckBox p2c0text = (CheckBox) findViewById(R.id.P2C0);
	         		if(p2c0text.isChecked()){
	         			ret[0]=false;
	         		}
	         		CheckBox p2c1text = (CheckBox) findViewById(R.id.P2C1);
	         		if(p2c1text.isChecked()){
	         			ret[1]=false;
	         		}
	         		CheckBox p2c2text = (CheckBox) findViewById(R.id.P2C2);
	         		if(p2c2text.isChecked()){
	         			ret[2]=false;
	         		}
	         		CheckBox p2c3text = (CheckBox) findViewById(R.id.P2C3);
	         		if(p2c3text.isChecked()){
	         			ret[3]=false;
	         		}
	         		CheckBox p2c4text = (CheckBox) findViewById(R.id.P2C4);
	         		if(p2c4text.isChecked()){
	         			ret[4]=false;
	         		}
	         		}
	         		*/
         		
            	return ret;
            }
            public void FirstDeal(){
            	//New Shuffle
             	//if (deck.getTotalCards()<12)
             	deck = new Deck();
             	//Deal
            	hand= new Hand(deck);
         		//hand2= new Hand(deck);
         		//Minus the bet Placed
         		player.pot=player.pot-player.bet;
         		enable();
         		Display();
            }
            public void SecondDeal(){
            	hand.newHand(SelectCards(0), deck);
            	//hand2.newHand(SelectCards(1), deck);
            	//
            	//Calculate winnings and add
            	player.pot=player.pot + player.bet*hand.eval.reward;
            	//
            	disable();
         		Display();
         		DisplayBet();
         		DisplayWinner();
         		counter=-1;
            }
            public void DisplayWinner(){
         		//Winner
         		TextView winner = (TextView) findViewById(R.id.winner);
         		if(hand.eval.reward>0)
         			winner.setText("You Won!");
         		else
         			winner.setText("You Didn't Win.");
            }
            
            public void Display(){
            	//Player One
         		TextView p1c0text = (TextView) findViewById(R.id.P1C0);
         		p1c0text.setText(hand.displayCard(0));
         		TextView p1c1text = (TextView) findViewById(R.id.P1C1);
         		p1c1text.setText(hand.displayCard(1));
         		TextView p1c2text = (TextView) findViewById(R.id.P1C2);
         		p1c2text.setText(hand.displayCard(2));
         		TextView p1c3text = (TextView) findViewById(R.id.P1C3);
         		p1c3text.setText(hand.displayCard(3));
         		TextView p1c4text = (TextView) findViewById(R.id.P1C4);
         		p1c4text.setText(hand.displayCard(4));
         		//Player Two
         		/*
         		TextView p2c0text = (TextView) findViewById(R.id.P2C0);
         		p2c0text.setText(hand2.displayCard(0));
         		TextView p2c1text = (TextView) findViewById(R.id.P2C1);
         		p2c1text.setText(hand2.displayCard(1));
         		TextView p2c2text = (TextView) findViewById(R.id.P2C2);
         		p2c2text.setText(hand2.displayCard(2));
         		TextView p2c3text = (TextView) findViewById(R.id.P2C3);
         		p2c3text.setText(hand2.displayCard(3));
         		TextView p2c4text = (TextView) findViewById(R.id.P2C4);
         		p2c4text.setText(hand2.displayCard(4));
         		*/
         		//Each Hand
         		TextView playeronetext = (TextView) findViewById(R.id.playerone);
         		playeronetext.setText(hand.display());
         		/*
         		TextView playertwotext = (TextView) findViewById(R.id.playertwo);
         		playertwotext.setText(hand2.display()); 
         		*/    		
         		//Winner
         		TextView winner = (TextView) findViewById(R.id.winner);
         		winner.setText("Please Select Your Cards");
         		//Cards Left in Deck
         		TextView cardsLeft = (TextView) findViewById(R.id.cardsLeft);
         		cardsLeft.setText(String.valueOf(deck.getTotalCards()));
         		//Betting Stuff
         		
         		TextView banktext = (TextView) findViewById(R.id.bank);
         		banktext.setText(String.valueOf(player.pot));
         		TextView rewardtext = (TextView) findViewById(R.id.reward);
         		rewardtext.setText(String.valueOf(hand.eval.reward));
            	//
         		
            	//Display Winnings
            	TextView payouttext = (TextView) findViewById(R.id.payout);
         		payouttext.setText(String.valueOf(player.bet*hand.eval.reward));
         		
            }
        });
        
        
	}
	//Display the amount player is betting
	public void DisplayBet(){
    	TextView bettext = (TextView) findViewById(R.id.bet);
 		bettext.setText(String.valueOf(player.bet));
    }
}
