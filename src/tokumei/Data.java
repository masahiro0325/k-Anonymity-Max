package tokumei;

import java.util.ArrayList;

public class Data {
	private int zokuseisu;
	private Zoku[] zoku;
	private String nzoku;

	public Data(ArrayList<String> gyo){
		zokuseisu = gyo.size()-1;
		zoku = new Zoku[zokuseisu];

		for(int i=0; i<zokuseisu; i++){
			zoku[i] =new Zoku(i,Integer.parseInt(gyo.get(i)),gyo.get(i).length());
		}
		nzoku = gyo.get(gyo.size()-1);
	}

	public Data(Data data){
		zokuseisu = data.getZokuseisu();
		zoku = new Zoku[zokuseisu];
		for(int i=0; i<data.getZokuseisu();i++)
			zoku[i] = new Zoku(i,data.getZoku(i).getSu(),data.getZoku(i).getKetaSu());
		nzoku = data.getNzoku();

	}
	public Data(){}

	public Data deepCopy(){
		Data data1 = new Data();

		data1.zokuseisu = zokuseisu+0;
		data1.zoku = new Zoku[zoku.length];
		for(int i=0; i<zoku.length; i++){
			data1.zoku[i] = zoku[i].deepCopy();
		}
		data1.nzoku = nzoku+"";
		return data1;
	}

	public Zoku getZoku(int i){//i属性目を返す
		return zoku[i];
	}

	public String getNzoku(){
		return nzoku;
	}

	public int getZokuseisu(){//何属性あるかを返す
		return zokuseisu;
	}

	public double getTokumei(){ //匿名度を数字で出す
		double num=0;
		for(int i=0; i<zokuseisu; i++)
			num+=zoku[i].getTokumei();
		num/=zokuseisu;

		return num;
	}

	public String toString(){ //1行を表示する
		String str="";

		str+=zoku[0].toString();
		for(int i=1; i< zokuseisu;i++){
			str+=","+zoku[i].toString();
		}

		str+=","+nzoku;

		return str;
	}
}
