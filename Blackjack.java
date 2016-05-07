package blackjack;

import java.io.IOException;

public class Blackjack {

	public static void main(String[] args) throws IOException{
		java.io.BufferedReader in = new java.io.BufferedReader( new java.io.InputStreamReader(System.in));
		GetTrump allTrump = new GetTrump();
		Human player = new Human("player");
		Human dealer = new Human("dealer");
		String select = null;
		boolean playing = true;
		int playerWin = 0;
		int dealerWin = 0;
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
			dealer.ShowNowState();
			player.ShowNowState();
			//プレイヤーのターン！
			if(player.GetNowPoint() == 21){
				endPlayer = true;
			}
			while(!endPlayer){
				//ヒットするか否かのコンソール入力処理
				while(true){
					System.out.println("ヒットしますか？ y/n");
					select = in.readLine();
					if(select.equals("y") || select.equals("n")){
						break;
					}
				}
				if(select.equals("y")){
					player.GetTrump(allTrump.outTrump());
					if(player.GetNowState()){
						System.out.println("バストしました");
						endPlayer = true;
						pBust = true;
					}
					else if(player.GetNowPoint() == 21){
						endPlayer = true;
					}
					player.ShowNowState();
				}
				else{
					endPlayer = true;
				}
				select = null;
			}
			//ディーラーのターン！
			while(!endEealer && !pBust){
				if(dealer.GetNowPoint() < 18 || dealer.GetNowPoint() < player.GetNowPoint()){
					dealer.GetTrump(allTrump.outTrump());
					if(dealer.GetNowState()){
						System.out.println("バストしました");
						endEealer = true;
						dBust = true;
					}
					dealer.ShowNowState();
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
			System.out.println("現在の成績 "+playerWin+"勝 "+dealerWin+"敗");
			player.Init();
			dealer.Init();
			allTrump.init();
			while(true){
				System.out.println("続けてプレイしますか？ y/n");
				select = in.readLine();
				if(select.equals("y") || select.equals("n")){
					if(select.equals("n")){
						playing = false;
					}
					break;
				}
			}
		}
	}
}
