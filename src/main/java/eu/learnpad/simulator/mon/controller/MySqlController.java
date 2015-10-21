package eu.learnpad.simulator.mon.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.net.ntp.TimeStamp;
import org.w3c.dom.Document;

import eu.learnpad.simulator.mon.utils.DebugMessages;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;

public class MySqlController implements DBController {

	private Properties connectionProp;
	private Connection conn;
	private Statement statement;
	
	public MySqlController(Properties databaseConnectionProperties) {
		connectionProp = databaseConnectionProperties;
	}

	@Override
	public boolean connectToDB() {
		DebugMessages.print(TimeStamp.getCurrentTime(), MySqlController.class.getSimpleName(),"Connecting to db " + connectionProp.getProperty("database.host"));

		String url = "jdbc:mysql://"
				+ connectionProp.getProperty("database.host") +
				":" + connectionProp.getProperty("database.port")+"/";
		
		String dbName = connectionProp.getProperty("database.name");
		String driver = "com.mysql.jdbc.Driver";
		String userName = connectionProp.getProperty("username"); 
		String password = connectionProp.getProperty("password");
		try { 
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			statement = conn.createStatement();
			DebugMessages.ok();
		} catch (SQLException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(),
					MySqlController.class.getSimpleName(),
					"Could not connect to db " + connectionProp.getProperty("database.host"));
			e.printStackTrace();
			return false;
		} catch (InstantiationException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(),
					MySqlController.class.getSimpleName(),
					"Could not connect to db " + connectionProp.getProperty("database.host"));
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(),
					MySqlController.class.getSimpleName(),
					"Could not connect to db " + connectionProp.getProperty("database.host"));
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(),
					MySqlController.class.getSimpleName(),
					"Could not connect to db " + connectionProp.getProperty("database.host"));
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public int saveBPMN(Document theBPMN, Date extractionDate, String category) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Document getBPMN(int theBPMNid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveBPMNCoverageRules(int idBPMN, ComplexEventRuleActionListDocument theRulesSet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ComplexEventRuleActionListDocument getBPMNCoverageRules(int idBPMN) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBPMNCategory(int theBPMNid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBPMNCategory(int theBPMNid, int theCategoryID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getBPMNAbsoluteScore(int theBPMNid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBPMNAbsoluteScore(int theBPMNid, float absoluteScore) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getBPMNExtractionDate(int theBPMNid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBPMNExtractionDate(int theBPMNid, Date extractionDatefloat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCategoryName(int theCategoryID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCategoryName(int theCategoryID, String theCategoryName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCategoryID(String theCategoryName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int createCategory(String category) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getIDsBPMNPaths(int idBPMN) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveSingleBPMNPath(int idBPMN, List<String> path, float absoluteSessionScore) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getSingleBPMNPath(int idBPMN, int idSinglePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getSingleBPMNPathAbsoluteSessionScore(int idBPMN) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSingleBPMNPathAbsoluteSessionScore(int idBPMN, float absoluteSessionScore) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPathRules(int idPath, int idBPMN, ComplexEventRuleActionListDocument rules) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ComplexEventRuleActionListDocument getPathRules(int idPath, int idBPMN) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveLearnerProfile(String name, String surname) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLearnerNameSurname(int idLearner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getLearnerBPScore(int idLearner, int idBPMN) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLearnerBPScore(int idLearner, int idBPMN, float BPScore) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getLearnerSessionScore(int idLearner, int idBPMN) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLearnerSessionScore(int idLearner, int idBPMN, float SessionScore) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getLearnerRelativeBPScore(int idLearner, int idBPMN) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLearnerRelativeBPScore(int idLearner, int idBPMN, float BPScore) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getLearnerGlobalScore(int idLearner) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLearnerGlobalScore(int idLearner, float GlobalScore) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getLearnerRelativeGlobalScore(int idLearner) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLearnerRelativeGlobalScore(int idLearner, float RelativeGlobalScore) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getLearnerAbsoluteGlobalScore(int idLearner) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLearnerAbsoluteGlobalScore(int idLearner, float AbsoluteGlobalScore) {
		// TODO Auto-generated method stub
		
	}
}
