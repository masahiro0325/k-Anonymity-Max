package tokumei;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;

public class Database {

	 final String URL = "jdbc:mysql://localhost/ktokumei";
	 final String USERNAME = "root";
	 final String PASSWORD = "";


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

		 mysql(str);
	}

	public void mysql(String str) throws Exception{
		Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();

        String sql = "TRUNCATE TABLE data;";
        statement.executeUpdate(sql);

        String[] list= str.split("\n", 0);

        for(int i=0; i<list.length; i++){
        	String[] line = list[i].split(",",0);
        	sql = "INSERT INTO data (c1, c2, c3, z) VALUES ('"+line[0]+"', '"+line[1]+"', '"+line[2]+"','"+line[3]+"');";
        	statement.executeUpdate(sql);
        }

        statement.close();
        connection.close();
	}
}
