package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Human {
	private String name;
	private int myPoint;
	private int anotherPoint;
	private boolean bust;
	//引いたカードのリスト
	private List<Trump> cardList = new ArrayList<Trump>();
	Human(String nm){
		name = nm;
		myPoint = 0;
		anotherPoint = 0;
		bust = false;
	}
	//初期化
	void Init(){
		myPoint = 0;
		anotherPoint = 0;
		bust = false;
		cardList.clear();
	}
	void GetTrump(Trump tp){
		cardList.add(tp);
		//トランプの数に合わせて自分のポイントを足していく
		if(tp.getnumber()==1){
			myPoint++;
			anotherPoint+=11;
		}
		else if(tp.getnumber()<11){
			myPoint+=tp.getnumber();
			anotherPoint+=tp.getnumber();
		}
		else{
			myPoint+=10;
			anotherPoint+=10;
		}
		if(myPoint>21){
			bust = true;
		}
		if(anotherPoint>21){
			anotherPoint = 0;
		}
	}
	//自分の得点を返す
	int GetNowPoint(){
		if(anotherPoint>myPoint){
			return anotherPoint;
		}
		else{
			return myPoint;
		}
	}
	boolean GetNowState(){
		return bust;
	}
	//現在の状態の表示
	void ShowNowState(){
		System.out.println("名前:"+name);
		System.out.println("現在の得点:"+GetNowPoint());
		System.out.println("最後に引いたカード:"+cardList.get(cardList.size()-1).gettype()+":"+cardList.get(cardList.size()-1).getnumber());
		System.out.println("引いたカードの数:"+cardList.size());
		System.out.println("今までに引いたカード");
		for(int i=0;i<cardList.size();i++){
			Trump nst = cardList.get(i);
			System.out.println(nst.gettype()+":"+nst.getnumber());
		}
		System.out.println("----------------------------------------");
	}
}