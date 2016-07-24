package tokumei;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Input {
	private String str;
	private ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

	final String URL = "jdbc:mysql://localhost/ktokumei";
	 final String USERNAME = "root";
	 final String PASSWORD = "";

	public Input(String st) throws Exception{
		//notMysql(st);
		mysql();
	}

	public ArrayList<ArrayList<String>> getList(){
		return list;
	}

	public void notMysql(String st) throws Exception{
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

	public void mysql() throws Exception{
		Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();

        String sql = "SELECT c1, c2, c3, z FROM data;";

        ResultSet rset = statement.executeQuery(sql);

        while ( rset.next() ) {
        	ArrayList<String> sl = new ArrayList<String>();
        	for(int i=0; i<4;i++){
        		String s =rset.getString(i+1);
        		int x=s.length();
        		for(int j=0; j<4-x;j++)
        			s="0"+s;
        		sl.add(s);
        	}
        	list.add(sl);
        }

        rset.close();
        statement.close();
        connection.close();
	}
}
