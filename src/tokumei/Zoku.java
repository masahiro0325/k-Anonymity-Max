package tokumei;

public class Zoku {

	private int zoku;   //何番目の属性なのか
	private int ketaSu; //何桁あるのか
	private int[] keta;  //1234ならば、keta[0]=4, keta[1]=2, keta[2]=3, keta[3]=1 keta[4]=-1となる
	private int tokumeido; //どれぐらい匿名化されているのか
	private int su; //数字

	public Zoku(int zoku, int su, int ketaSu){//何番目の属性なのか、数字、何桁なのか
		this.zoku = zoku;
		this.su =su;
		this.ketaSu = ketaSu;
		keta = new int[ketaSu+1];
		this.tokumeido = 0;

		int waru = 10;
		int waru2=1;

		for(int i=0;i<ketaSu;i++){
			keta[i] = su%waru; waru*=10;
			keta[i]/=waru2; waru2*=10;
		}
		keta[ketaSu]=-1;
	}
	public Zoku(){}

	public Zoku deepCopy(){
		Zoku zoku1 = new Zoku();

		zoku1.zoku = zoku+0;
		zoku1.ketaSu = ketaSu+0;
		zoku1.keta = new int[keta.length];
		for(int i=0; i<keta.length;i++)
			zoku1.keta[i] = keta[i]+0;
		zoku1.tokumeido = tokumeido+0;
		zoku1.su = su+0;
		return zoku1;
	}

	public int getZoku(){
		return zoku;
	}

	public int getKetaSu(){
		return ketaSu;
	}

	public int getTokumeido(){
		return tokumeido;
	}

	public void setTokumeido(int a){
		tokumeido = a;
	}

	public int[] getKeta(){
		return keta;
	}

	public int getSu(){
		return su;
	}

	public String toString(){
		String str="";
		for(int i=0; i<ketaSu; i++){
			if(i<tokumeido)
				str="*"+str;
			else
				str=keta[i]+str;
		}
		return str;
	}

	public double getTokumei(){  //匿名度を数字で出す
		return ((double)(ketaSu-tokumeido))/ketaSu;
	}
}
