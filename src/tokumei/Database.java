package tokumei;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class Database {

	public Database(int keta, int gyo,String x) throws Exception{
		String str="";
		int num=1;
		Random rnd = new Random();
		for(int i=0; i<gyo; i++){
			for(int j=0; j<3; j++){
				for(int k=0; k<keta;k++){
				//0か1かを生成する
				str+=rnd.nextInt(2);
				}
				str+=",";
			}
			str+=num++;
			str+="\n";
		}

		 File newfile = new File(x);
		 newfile.createNewFile();
		 BufferedWriter bw = new BufferedWriter(new FileWriter(newfile));
		 bw.write(str);
		 bw.close();
	}
}
