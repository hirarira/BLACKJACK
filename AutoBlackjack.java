package blackjack;


public class AutoBlackjack {

	public static void main(String[] args){
		final int LIMIT = 10000;
		final boolean SHOWPROSESS = false;
		GetTrump allTrump = new GetTrump();
		Human player = new Human("player");
		Human dealer = new Human("dealer");
		boolean playing = true;
		int playerWin = 0;
		int dealerWin = 0;
		int count = 0;
		while(playing){
			boolean endPlayer = false;
			boolean endEealer = false;
			boolean pBust = false;
			boolean dBust = false;
			boolean winner = false;
			boolean draw = false;
			//初めにディーラー・プレイヤー共2回づつカードを引く
			dealer.GetTrump(allTrump.outTrump());
			dealer.GetTrump(allTrump.outTrump());
			player.GetTrump(allTrump.outTrump());
			player.GetTrump(allTrump.outTrump());
			if(SHOWPROSESS){
				dealer.ShowNowState();
				player.ShowNowState();
			}
			//プレイヤーのターン！
			if(player.GetNowPoint() == 21){
				endPlayer = true;
			}
			while(!endPlayer){
				if(player.GetNowPoint() < 18 || player.GetNowPoint() < dealer.GetNowPoint()){
					player.GetTrump(allTrump.outTrump());
					if(player.GetNowState()){
						if(SHOWPROSESS){
							System.out.println("バストしました");
						}
						endPlayer = true;
						pBust = true;
					}
					else if(player.GetNowPoint() == 21){
						endPlayer = true;
					}
					if(SHOWPROSESS){
						player.ShowNowState();
					}
				}
				else{
					endPlayer = true;
				}
			}
			//ディーラーのターン！
			while(!endEealer && !pBust){
				if(dealer.GetNowPoint() < player.GetNowPoint()){
					dealer.GetTrump(allTrump.outTrump());
					if(dealer.GetNowState()){
						if(SHOWPROSESS){
							System.out.println("バストしました");
						}
						endEealer = true;
						dBust = true;
					}
					if(SHOWPROSESS){
						dealer.ShowNowState();
					}
				}
				else{
					endEealer = true;
				}
			}
			//勝敗決着
			if(pBust){
				winner = false;
				draw = false;
			}
			else if(dBust){
				winner = true;
				draw = false;
			}
			else{
				if(player.GetNowPoint() == dealer.GetNowPoint()){
					draw = true;
				}
				else if(player.GetNowPoint() >dealer.GetNowPoint() ){
					winner = true;
					draw = false;
				}
				else{
					winner = false;
					draw = false;
				}
			}
			//勝敗表示
			System.out.println("プレイヤー:"+player.GetNowPoint());
			System.out.println("ディーラー:"+dealer.GetNowPoint());
			if(draw){
				System.out.println("引き分けです。");
			}
			else if(winner){
				System.out.println("プレイヤーの勝利です。");
				playerWin++;
			}
			else{
				System.out.println("プレイヤーの敗北です。");
				dealerWin++;
			}
			System.out.println("現在の成績 "+playerWin+"勝 "+dealerWin+"敗 "+(count-(playerWin+dealerWin))+"分け");
			System.out.println("----------------------------------------");
			player.Init();
			dealer.Init();
			allTrump.init();
			count++;
			if(count > LIMIT){
				playing = false;
			}
		}
	}
}
