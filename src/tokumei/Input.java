package tokumei;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Input {
	private String str;
	private ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

	public Input(String st) throws Exception{
		str = st;

		FileInputStream fis = new FileInputStream(str);
        InputStreamReader isr = new InputStreamReader((fis),"UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String input;

        while(null != (input = br.readLine())){
        	String[] s = input.split(",");
        	ArrayList<String> sl = new ArrayList<String>();
        	for(int i=0; i<s.length;i++){
        		int x=s[i].length();
        		for(int j=0; j<4-x;j++)
        			s[i]="0"+s[i];
        		sl.add(s[i]);
        	}
        	list.add(sl);
        }


        br.close();
	}

	public ArrayList<ArrayList<String>> getList(){
		return list;
	}
}
