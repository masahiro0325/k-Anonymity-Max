package tokumei;

import java.util.ArrayList;

public class Node {
	private ArrayList<Data> data = new ArrayList<Data>();
	private ArrayList<Data> outputdata = new ArrayList<Data>();
	private int tokumeido[];
	private int ketasu[];
	private int zokuseisu;
	private int k;

	public Node(){}
	public Node(int a,int[] b, int[] c, int k){//3属性の場合
		zokuseisu = a;
		tokumeido = b;
		ketasu = c;
		this.k=k;
	}

	public Node deepCopy(){
		Node nd1 = new Node();

		for(int i=0; i<data.size(); i++)
			nd1.data.add(data.get(i).deepCopy());
		for(int i=0; i<outputdata.size();i++)
			nd1.outputdata.add(outputdata.get(i).deepCopy());
		nd1.tokumeido = new int[tokumeido.length];
		for(int i=0; i<tokumeido.length; i++)
			nd1.tokumeido[i] = tokumeido[i]+0;
		nd1.ketasu = new int[ketasu.length];
		for(int i=0; i<ketasu.length; i++)
			nd1.ketasu[i] = ketasu[i]+0;
		nd1.zokuseisu = zokuseisu+0;
		nd1.k = k+0;

		return nd1;
	}

	public boolean addData(Data d){
		if(data.size()==0){
			data.add(new Data(d));
			return true;
		}
		for(int i=0; i<zokuseisu; i++){
			for(int j=ketasu[i];j>tokumeido[i]-1;j--){
				if(data.get(0).getZoku(i).getKeta()[j]!=d.getZoku(i).getKeta()[j])
					return false;
			}
		}
		data.add(new Data(d));
		return true;
	}

	public String toString(){
		String str = "";

		for(int i=0; i<data.size();i++)
			str+=data.get(i).toString()+"\n";

		str+="\n";

		return str;
	}

	public boolean findData(Data d){
		if(data.indexOf(d)==-1)
			return false;
		else
			return true;

	}

	public boolean findOutputData(Data d){
		if(outputdata.indexOf(d)==-1)
			return false;
		else
			return true;
	}


	public ArrayList<Data> output(){
		outputdata = new ArrayList<Data>(data);

		for(int i=0; i<outputdata.size();i++){
			for(int j=0; j<zokuseisu;j++)
				outputdata.get(i).getZoku(j).setTokumeido(tokumeido[j]);
		}

		return outputdata;
	}
	public void removeData(Data d){
		for(int i=0; i<data.size(); i++){
			boolean hanntei = false;

			for(int j=0; j<d.getZokuseisu();j++)
				if(data.get(i).getZoku(j).getSu() != d.getZoku(j).getSu())
					hanntei = true;

			if(!data.get(i).getNzoku().contentEquals(d.getNzoku()))
				hanntei = true;

			if(!hanntei){
				data.remove(i);
				return;
			}
		}
	}
	public void removeData(ArrayList<Data> d){
		for(int i=0; i<d.size();i++)
			removeData(d.get(i));
	}
	public void removeOutputData(Data d){
		for(int i=0; i<outputdata.size(); i++){
			boolean hanntei = false;

			for(int j=0; j<d.getZokuseisu();j++)
				if(outputdata.get(i).getZoku(j).getSu() != d.getZoku(j).getSu())
					hanntei = true;

			if(!outputdata.get(i).getNzoku().contentEquals(d.getNzoku()))
				hanntei = true;

			if(!hanntei){
				outputdata.remove(i);
				return;
			}
		}
	}
	public void removeOutputData(ArrayList<Data> d){
		for(int i=0; i<d.size();i++)
			removeOutputData(d.get(i));
	}
	public void removeAllOutoutData(){
		outputdata.clear();
	}
	public ArrayList<Data> getData(){
		return data;
	}
	public ArrayList<Data> getOutputData(){
		return outputdata;
	}
	public int getDataSize(){
		return data.size();
	}
	public int getOutputDataSize(){
		return outputdata.size();
	}
	public boolean tokumeiCheck(){
		if(data.size()>=k){
			return true;
		}
		else
			return false;
	}
	public boolean tokumeiCheckOutput(){
		if(outputdata.size()>=k){
			return true;
		}
		else
			return false;
	}
}
