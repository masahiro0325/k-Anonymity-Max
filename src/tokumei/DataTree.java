package tokumei;

import java.util.ArrayList;
import java.util.Comparator;


public class DataTree {
	private SameStageNode ssn[][][]; //同じ階層のノード(属性が三つある場合)
	private int zokuseisu; //属性数
	private int[] ketasu; //各属性の桁数
	private int k; //何匿名か
	private ArrayList<SameStageNode> ssnlist = new ArrayList<SameStageNode>();
	private DataList list = new DataList();

	public DataTree(DataList datalist, int k){
		zokuseisu =datalist.getData(0).getZokuseisu();
		ketasu = new int[zokuseisu];
		for(int i=0; i<zokuseisu; i++){
			ketasu[i] = datalist.getData(0).getZoku(i).getKetaSu();
		}
		this.k = k;

		ssn = new SameStageNode[ketasu[0]+1][ketasu[1]+1][ketasu[2]+1]; //属性が三つの場合

		makeSSN(datalist);
		sortSSN();

		this.refreshSSN(ssnlist);

		DataList tmp = makeNewDataList(datalist.getListSize(),ssnlist);
		list = makeNewDataList(datalist.getListSize(),list,ssnlist,tmp.getTokumei()+0.0,tmp.deepCopy());
		//list = this.makeNewDataList(datalist.getListSize(),ssnlist);
	}

	public void refreshSSN(ArrayList<SameStageNode> ssn1){
		for(int i=0; i<ssn1.size();){
			for(int j=0; j<ssn1.get(i).getNode().size();){
				if(ssn1.get(i).getNode().get(j).getOutputDataSize()<k){
					ssn1.get(i).getNode().remove(j);
				}else{
					j++;
				}
			}
			if(ssn1.get(i).getNode().size() == 0){
				ssn1.remove(i);
			}else{
				i++;
			}
		}
	}

	public ArrayList<SameStageNode> deepCopySSNList(ArrayList<SameStageNode> ssn1){
		ArrayList<SameStageNode> ssntmp = new ArrayList<SameStageNode>();

		for(int i=0; i<ssn1.size();i++){
			ssntmp.add(ssn1.get(i).deepCopy());
		}

		return ssntmp;
	}

	private void makeSSN(DataList datalist){ //属性が3つある場合
		for(int h=0; h<ketasu[0]+1; h++)
			for(int i=0; i<ketasu[1]+1;i++)
				for(int j=0; j<ketasu[2]+1; j++)
					ssn[h][i][j]= new SameStageNode(h,i,j,ketasu,k);

		for(int g=0; g<datalist.getListSize();g++){
			for(int h=0; h<ketasu[0]+1; h++)
				for(int i=0; i<ketasu[1]+1;i++)
					for(int j=0; j<ketasu[2]+1; j++)
						ssn[h][i][j].addData(datalist.getData(g));
		}
	}

	private void sortSSN(){
		for(int h=0; h<ketasu[0]+1; h++)
			for(int i=0; i<ketasu[1]+1;i++)
				for(int j=0; j<ketasu[2]+1; j++){
					ssn[h][i][j].output();
					ssnlist.add(ssn[h][i][j]);
				}


		ssnlist.sort(Comparator.comparing(SameStageNode::getTokumeido));
	}

	public DataList makeNewDataList(int size, ArrayList<SameStageNode> ssn){
		DataList list = new DataList();
		ArrayList<SameStageNode> ssnlist = this.deepCopySSNList(ssn);

		for(int i=0; i<ssnlist.size();i++){
			for(int j=0; j<ssnlist.get(i).getNode().size();j++){
				if(ssnlist.get(i).getNode().get(j).tokumeiCheckOutput() &&
						(k<=size-ssnlist.get(i).getNode().get(j).getOutputDataSize()-list.getListSize() ||
								0==size-ssnlist.get(i).getNode().get(j).getOutputDataSize()-list.getListSize())){
					list.addData(ssnlist.get(i).getNode().get(j).getOutputData());

					for(int m=j;m<ssnlist.get(i).getNode().size();m++){
						if(!( m==j))
							ssnlist.get(i).getNode().get(m).removeOutputData(ssnlist.get(i).getNode().get(j).getOutputData());
					}
					for(int l=i+1;l<ssnlist.size() ;l++){
						for(int m=0;m<ssnlist.get(l).getNode().size();m++){
							ssnlist.get(l).getNode().get(m).removeOutputData(ssnlist.get(i).getNode().get(j).getOutputData());
						}
					}
				}
			}
		}

		return list;
	}

	public DataList makeNewDataList(int size, DataList list2, ArrayList<SameStageNode> ssn2,double num,DataList num2){

		if(size == list2.getListSize() || ssn2.size()==0){
			if(num < list2.getTokumei()){
				num = list2.getTokumei();
				num2 = list2.deepCopy();
			}
			return list2;
		}

		ArrayList<DataList> datalist = new ArrayList<DataList>();

		if(list2.getListSize()==0){
			datalist.add(num2.deepCopy());
		}

		for(int i=0; k<=ssn2.get(0).getNode().get(0).getOutputDataSize()-i; i++){
			ArrayList<SameStageNode> ssntmplist = this.deepCopySSNList(ssn2);
			DataList tmpdl = list2.deepCopy();

			for(int j=0; j<i; j++){
					ssntmplist.get(0).getNode().get(0).removeOutputData(ssn2.get(0).getNode().get(0).getOutputData().get(j));
			}

			if(ssntmplist.get(0).getNode().get(0).tokumeiCheckOutput() &&
					(k<=size-ssntmplist.get(0).getNode().get(0).getOutputDataSize()-list2.getListSize() ||
							0==size-ssntmplist.get(0).getNode().get(0).getOutputDataSize()-list2.getListSize())){
				tmpdl.addData(ssntmplist.get(0).getNode().get(0).getOutputData());

				for(int m=1;m<ssntmplist.get(0).getNode().size();m++){
					ssntmplist.get(0).getNode().get(m).removeOutputData(ssntmplist.get(0).getNode().get(0).getOutputData());
					for(int o=i; o<ssn2.get(0).getNode().get(0).getOutputData().size();o++){
						ssntmplist.get(0).getNode().get(m).removeOutputData(ssn2.get(0).getNode().get(0).getOutputData().get(o));
					}
				}
				for(int l=1;l<ssntmplist.size() ;l++){
					for(int m=0;m<ssntmplist.get(l).getNode().size();m++){
						ssntmplist.get(l).getNode().get(m).removeOutputData(ssntmplist.get(0).getNode().get(0).getOutputData());
						for(int o=i; o<ssn2.get(0).getNode().get(0).getOutputData().size();o++){
							ssntmplist.get(l).getNode().get(m).removeOutputData(ssn2.get(0).getNode().get(0).getOutputData().get(o));
						}
					}
				}

				ssntmplist.get(0).getNode().remove(0);
				this.refreshSSN(ssntmplist);

				if(ssntmplist.size()!=0){
					if(num<tmpdl.getTokumei())
						datalist.add(makeNewDataList(size, tmpdl,ssntmplist,num,num2));
				}else{
					datalist.add(makeNewDataList(size, tmpdl,ssntmplist,num,num2));
				}
			}
		}

//*
		if(ssn2.size() >1){
			ArrayList<SameStageNode> ssntmplist = this.deepCopySSNList(ssn2);
			DataList tmpdl = list2.deepCopy();

			ssntmplist.get(0).getNode().remove(0);
			this.refreshSSN(ssntmplist);

			if(num<tmpdl.getTokumei())
				datalist.add(this.makeNewDataList(size, tmpdl, ssntmplist,num,num2));
		}
//*/
		if(datalist.size() == 0){
			return new DataList();
		}
		DataList dltmp = datalist.get(0);

		for(int i=0; i<datalist.size(); i++)
			if(dltmp.getTokumei()<datalist.get(i).getTokumei())
				dltmp = datalist.get(i);

		return dltmp;
	}

	public DataList outputDataList(){
		return list;
	}
}
