package tokumei;

import java.util.ArrayList;

public class DataList {
	private ArrayList<Data> datalist = new ArrayList<Data>();

	public DataList(ArrayList<ArrayList<String>> str){
		for(int i=0; i<str.size(); i++){
			datalist.add(new Data(str.get(i)));
		}
	}
	public DataList(){}

	public DataList deepCopy(){
		DataList dl1 = new DataList();

		for(int i=0; i<datalist.size();i++){
			dl1.datalist.add(datalist.get(i).deepCopy());
		}
		return dl1;
	}

	public void addData(Data d){
		datalist.add(d);
	}
	public void addData(ArrayList<Data> d){
		for(int i=0; i<d.size(); i++){
			datalist.add(d.get(i));
		}
	}
	public void removeData(Data a){
		datalist.remove(a);
	}
	public void removeData(ArrayList<Data> d){
		for(int i=0; i<d.size(); i++)
			this.removeData(d.remove(i));
	}
	public Data getData(int a){//a番目のデータを返す
		return datalist.get(a);
	}

	public int getListSize(){//リストの大きさを返す
		return datalist.size();
	}

	public String toString(){
		String str="";

		for(int i=0; i<datalist.size();i++){
			str+=datalist.get(i).toString()+"\n";
		}

		return str;
	}

	public double getTokumei(){
		double num= 0;

		for(int i=0; i<datalist.size(); i++)
			num+=datalist.get(i).getTokumei();

		return num/datalist.size();
	}
}
