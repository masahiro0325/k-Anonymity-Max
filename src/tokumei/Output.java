package tokumei;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Output {
	private DataList datalist;
	private String str;

	public Output(DataList d, String st) throws Exception{
		datalist = d;
		str = st;

		File newfile = new File(str);
        newfile.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(newfile));

        bw.write(datalist.toString());
    	bw.newLine();

    	bw.write("有用性："+datalist.getTokumei());
    	bw.newLine();

    	bw.close();

	}
}
