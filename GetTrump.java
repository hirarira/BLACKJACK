package blackjack;

import java.util.Random;

public class GetTrump {
	private Random rnd = new Random();
	private Trump pTrump [][] = new Trump[4][13];
	private int remainTrumpNum;
	private String inname;
	//トランプの束を作成
	GetTrump(){
		remainTrumpNum = 52;
		for(int i=0;i<4;i++){
			switch(i){
			case 0:
				inname = "Clubs";
				break;
			case 1:
				inname = "Diamonds";
				break;
			case 2:
				inname = "Hearts";
				break;
			case 3:
				inname = "Spades";
				break;
			}
			for(int j=0;j<13;j++){
				pTrump[i][j] = new Trump(inname,j+1);
			}
		}
	}
	//作り上げたトランプを全て表示(デバッグ用)
	void Showall(){
		for(int i=0;i<4;i++){
			for(int j=0;j<13;j++){
				System.out.println(pTrump[i][j].gettype() + ":" +pTrump[i][j].getnumber());
			}
		}
	}
	//山札のトランプの中から1枚ランダムで引く
	//残りのトランプが無い場合はnullが返る
	Trump outTrump(){
		if(remainTrumpNum<=0){
			return null;
		}
		int n_type;
		int n_number;
		while(true){
			n_type = rnd.nextInt(4);
			n_number = rnd.nextInt(13);
			if(pTrump[n_type][n_number].used()){
				break;
			}
		}
		remainTrumpNum--;
		return pTrump[n_type][n_number];
	}
	//山札を初期化
	void init(){
		remainTrumpNum = 52;
		for(int i=0;i<4;i++){
			for(int j=0;j<13;j++){
				pTrump[i][j].init();
			}
		}
	}
}
