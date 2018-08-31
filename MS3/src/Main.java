
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.Connection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.io.FileNotFoundException;


public class Main {

	private static String JDBC_CONNECTION_URL = 
			"jdbc:sqlite:/Users/arifaziz/sqlite/arif.db";    // saved the connection URL in a variable

	@SuppressWarnings("resource")   // to remove unnecessary warnings
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Running..."); // first statement beginning of the process
		
		String csv = "/Users/arifaziz/Desktop/MS3/ms3interview.csv"; // file location that i want to parse in a variable
	        
		  	BufferedReader br = null;  
	       	String line = "";
	       	String SplitBy = ",";
	       	boolean HeadRowExists = true;
	       	int ColumnCount = 10;   // number of column count is set
	       	int failed= 0;         // count that is failed to save into the database
	       	int count=0;          // count that is processed
	       	FileWriter fileWriter = null;   // created an object to 
	       	
	     	
        	try {    //try and catch to create another CSV file for bad data

        		 fileWriter = new FileWriter("badDataCSV.csv");
          		
        	}
       
        	catch(Exception e) {
        		e.printStackTrace();
 }
       
	        String[] column = null;
	        
	      // create List for holding data objects that will get saved in the database
	        
            List<Data> DataList = new ArrayList<Data>();
            

	        try {    // try and catch to read the file which i want to parse
				br = new BufferedReader(new FileReader(csv));
				           
	            if(HeadRowExists) {
	            	String HeadRow = br.readLine();
	         
	            	if(HeadRow==null || HeadRow.isEmpty()) {
	            		throw new FileNotFoundException("File not found");
	            	}
	            }
	            
				 while ((line = br.readLine()) != null) {

		                // use comma as separator
					 
					 column = line.split(SplitBy);   
		             	count++;   // counting the number of processed data
		        
		                if(column.length == ColumnCount)
		                {
		                    // save the details in the object
		                	
		                    Data data = new Data(column[0],
		                    		column[1],column[2],column[3],
		                    		column[4],column[5],column[6],
		                    		column[7],column[8],column[9]);
		                    DataList.add(data);
		                              
		                }
		                
		                else {  // this else is for bad data
		                	
		                	 String COMMA_DELIMITER = ",";
		                	 String NEW_LINE_SEPARATOR = "\n";
		                	
		                	
		                	try {  // try and catch to save the bad data in CSV file
		                	
		                		for (int i=0; i<column.length; i++) {
		          
		                	//	System.out.print(column[i]+"\t");  // to print the bad data in the console, i have tried this for testing
		                		
		                		fileWriter.append(String.valueOf(column[i]));   // parsing the bad data into the CSV file
		                		fileWriter.append(COMMA_DELIMITER);
		                		
		                		
		                	}
		                		
		                	fileWriter.append(NEW_LINE_SEPARATOR);  // changing the line in CSV file
		                // 	System.out.println("");  // to print the bad data in the console, i have tried this for testing
		               
		                	failed++; // counting the number of bad data
		 
		                }   
		                	
				 catch(Exception e) {
					 e.printStackTrace();
				 }
		                	finally {  // finally block to flush the CSV file
		                		
		                		try {
		                			fileWriter.flush();
		                			
		                		}catch(IOException e) {
		                			e.printStackTrace();
		                		}
		                		
		                	}
		                 
		                } 
		                
		                CSVintoDatabase(DataList); // copying the good data into the database
			
				 }
	                
				 } catch (FileNotFoundException e) {  
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	              
	        System.out.println("\n"+    // printing the counting numbers
	        		count+" of records received/processed"+", "+
	        		DataList.size()+" of records successful"+", "+
	        		failed+" of records failed");
	}

	private static void CSVintoDatabase(List<Data> dataList) {   
		// TODO Auto-generated method stub
		
		
		Connection connection = null;
		boolean tableExists = true;
		boolean truncateTable = true;
		
		try {

			connection = DriverManager.getConnection(JDBC_CONNECTION_URL); // connect the database
			
			if(tableExists != true) {
				connection.createStatement().execute("create table X(A, B, C, D, E, F, G, H, I, J)"); // created a table called X using SQL statement
			}
			
			if(truncateTable == true) {
				connection.createStatement().execute("delete from X"); // if table X was there
			}
					
			// inserting the data in the database using insert statement
			
			PreparedStatement insertSQL =
					connection.prepareStatement("insert into X (A, B, C, D, E, F, G, H, I, J) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			for(Data e : dataList)   // for loop to run the insert statement
            {
				
				insertSQL.setString(1, e.getA());
				insertSQL.setString(2, e.getB());
				insertSQL.setString(3, e.getC());
				insertSQL.setString(4, e.getD());
				insertSQL.setString(5, e.getE());
				insertSQL.setString(6, e.getF());
				insertSQL.setString(7, e.getG());
				insertSQL.setString(8, e.getH());
				insertSQL.setString(9, e.getI());
				insertSQL.setString(10, e.getJ());
				
				insertSQL.executeUpdate();
            }
		 
			ResultSet cn = connection.createStatement().executeQuery("select * from X"); // i have printed the good data that were stored in the database-
			// -in the console
			
			while(cn.next()) {  // while loop to print the good data in the console
				String A = cn.getString(1);
				String B = cn.getString(2);
				String C = cn.getString(3);
				String D = cn.getString(4);
				String E = cn.getString(5);
				String F = cn.getString(6);
				String G = cn.getString(7);
				String H = cn.getString(8);
				String I = cn.getString(9);
				String J = cn.getString(10);
				
				System.out.println(A+"\t"+B+"\t"+C+"\t"+D+"\t"+E+"\t"   // printed the good data in the console
				+F+"\t"+G+"\t"+H+"\t"+I+"\t"+J);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
