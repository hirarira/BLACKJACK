package blackjack;

public class Trump {
	//このトランプにおいて、ジョーカーは考慮しないものとする
	//トランプのタイプ
	private String c_type;
	//トランプの数字
	private int number;
	//トランプが既に引かれたかどうかの判定
	private boolean use;
	//タイプの数字をコンストラクタで導入
	Trump(String t_c_type,int t_number){
		this.c_type = t_c_type;
		this.number = t_number;
		use = false;
	}
	//private型なので中の値を開示するメソッド共
	String gettype(){
		return c_type;
	}
	int getnumber(){
		return number;
	}
	boolean getuse(){
		return use;
	}
	//トランプを使用する際、使用状態をtrueにする。
	boolean used(){
		if(this.use){
			return false;
		}
		else{
			this.use = true;
			return true;
		}
	}
	// 使用状態をfalseにする。初期化
	void init(){
		this.use = false;
	}
}
