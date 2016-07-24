package tokumei;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Output {
	private DataList datalist;
	private String str;

	final String URL = "jdbc:mysql://localhost/ktokumei";
	 final String USERNAME = "root";
	 final String PASSWORD = "";

	public Output(DataList d, String st) throws Exception{
		datalist = d;
		str = st;

		File newfile = new File(str);
        newfile.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(newfile));

        String s = datalist.toString();

        mysql(s);

        bw.write(s);
    	bw.newLine();

    	bw.write("有用性："+datalist.getTokumei());
    	bw.newLine();

    	bw.close();

	}

	public void mysql(String str) throws Exception{
		Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();

        String sql = "TRUNCATE TABLE result;";
        statement.executeUpdate(sql);

        String[] list= str.split("\n", 0);

        for(int i=0; i<list.length; i++){
        	String[] line = list[i].split(",",0);
        	sql = "INSERT INTO result (c1, c2, c3, z) VALUES ('"+line[0]+"', '"+line[1]+"', '"+line[2]+"','"+line[3]+"');";
        	statement.executeUpdate(sql);
        }

        statement.close();
        connection.close();
	}
}
