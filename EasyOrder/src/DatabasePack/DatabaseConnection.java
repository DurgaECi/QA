package DatabasePack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import DriverPack.TestConfig;
import ReportPack.TestReport;

public class DatabaseConnection {
	private TestConfig value = new TestConfig();
	public  ExtentReports reporter;
	private TestReport report = new TestReport();
	
	public String getDBData(String SQL,ExtentTest logger)
	{
		String url = value.dbUrlDEV1;
		String username = value.dbUserName;
		String password = value.dbPassword;
		String environmentName = value.environmentName;
		String data = null;  
		try {
			String driver = "com.ibm.as400.access.AS400JDBCDriver";			
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {				
				e.printStackTrace();
				report.LogFAILmsg(logger, "Error in loading DB driver");
			}		
			Connection table = DriverManager.getConnection(url, username, password);
			table.setSchema(environmentName);			
			Statement sql = table.createStatement();		
			ResultSet result = sql.executeQuery(SQL);	
			while(result.next())
			   {
				  data= result.getString(1);		   
			   }		
		} catch (SQLException e) {			
			e.printStackTrace();			
			report.LogFAILmsg(logger, "Error in DB Connection credentials/ in SQL");
		}
		return data;				 
	}

}
