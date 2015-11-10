package eu.learnpad.simulator.mon.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.net.ntp.TimeStamp;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

import eu.learnpad.simulator.mon.coverage.Activity;
import eu.learnpad.simulator.mon.coverage.Bpmn;
import eu.learnpad.simulator.mon.coverage.Category;
import eu.learnpad.simulator.mon.coverage.Learner;
import eu.learnpad.simulator.mon.coverage.Path;
import eu.learnpad.simulator.mon.coverage.Role;
import eu.learnpad.simulator.mon.coverage.Topic;
import eu.learnpad.simulator.mon.utils.DebugMessages;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionType;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleType;

public class MySqlController implements DBController {

	private Properties connectionProp;
	private Connection conn;	  
    private PreparedStatement preparedStmt;
    private ResultSet resultsSet;
	
	public MySqlController(Properties databaseConnectionProperties) {
		connectionProp = databaseConnectionProperties;
	}

	@Override
	public boolean connectToDB() {
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
			DebugMessages.print(TimeStamp.getCurrentTime(),
					MySqlController.class.getSimpleName(),
					"Connection to db " + connectionProp.getProperty("database.host"));
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
	public boolean disconnectFromDB() {
		return false;
	}
	
	@Override
	public List<Path> getBPMNPaths(int idBPMN) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getLearnerBPScore(int idLearner, int idBPMN) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setLearnerBPScore(int idLearner, int idBPMN, float BPScore) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getLearnerRelativeBPScore(int idLearner, int idBPMN) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setLearnerRelativeBPScore(int idLearner, int idBPMN, float relativeBPScore) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getLearnerBPCoverage(int idLearner, int idBPMN) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setLearnerBPCoverage(int idLearner, int idBPMN, float BPCoverage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getLearnerSessionScore(int idLearner, int idPath, int idRole, Date executionDate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getLearnerSessionScore(Learner theLearner, Path thePath, int idRole, Date executionDate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setLearnerSessionScore(int idLearner, int idPath, int idRole, float sessionScore) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setLearnerSessionScore(Learner theLearner, Path thePath, float sessionScore) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveBPMN(Bpmn theBPMN) {

	      String query = " insert into bpmn (id_bpmn, extraction_date, id_category, absolute_bp_score)"
	    	        + " values (?, ?, ?, ?) ";
	    	 
		try {
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, theBPMN.getId());
		    preparedStmt.setDate(2, new java.sql.Date(theBPMN.getExtractionDate().getTime()));
		    preparedStmt.setInt(3,theBPMN.getIdCategory());
		    preparedStmt.setFloat(4, theBPMN.getAbsoluteBpScore());

		    // execute the prepared statement
		    preparedStmt.execute();
		} catch (SQLException e) {
			return 1;
		}  
		DebugMessages.println(
				TimeStamp.getCurrentTime(), 
				this.getClass().getSimpleName(),
				"BPMN Saved");
		return 0;
	}

	@Override
	public boolean updateBpmn(int theBPMNid, Bpmn theBpmnToUpdate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveCategory(Category theCategory) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Category getCategory(int theCategoryID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateCategory(int theCategoryid, Category theCategoryToUpdate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveLearnerProfile(Learner theLearner) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Learner getLearner(int idLearner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateLearner(int idLearner, Learner theLearnerToUpdate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int savePath(Path thePath) {
		 String query = " insert into path (id, id_bpmn, absolute_session_score, path_rule)"
	    	        + " values (?, ?, ?, ?)";
	    	 
		try {
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, thePath.getId());
		    preparedStmt.setString(2, thePath.getIdBpmn());
		    preparedStmt.setFloat(3,thePath.getAbsoluteSessionScore());
		    preparedStmt.setString(4, thePath.getPathRule());
		 
		    // execute the prepared statement
		    preparedStmt.execute();
		} catch (SQLException e) {
			return 1;
		}  
		DebugMessages.println(
				TimeStamp.getCurrentTime(), 
				this.getClass().getSimpleName(),
				"Path Saved");
		return 0;
	}

	@Override
	public Path getPath(int thePathID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePath(int thePathId, Path thePathToUpdate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveRole(Role theRole) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Role getRole(int theRoleID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateRole(int theRoleId, Role theRoleToUpdate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveTopic(Topic theTopic) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Topic getTopic(int theTopicID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateTopic(int theTopicId, Topic theTopicToUpdate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkIfBPHasBeenAlreadyExtracted(String idBPMN) {
		String query = "select * from path where id_bpmn = \'"+idBPMN+"';";
			
		try {
			preparedStmt = conn.prepareStatement(query);
			resultsSet = preparedStmt.executeQuery();
			if (resultsSet.first()) { 
				DebugMessages.println(
						TimeStamp.getCurrentTime(), 
						this.getClass().getSimpleName(),
						"The BPMN has been already extracted, loading values");
				return true; 
				} 				
			}	catch(SQLException asd) {
				System.err.println("Exception during checkIfBPHasBeenAlreadyExtracted ");
				return false;
			}
		return false;
	}

	@Override
	public ComplexEventRuleActionListDocument getRulesListForASpecificBPMN(String bpmnIDFromXML) {
		
		String query = "select path_rule from path where learnpad_bpmn_id = \'"+bpmnIDFromXML+"';";
		ComplexEventRuleActionListDocument theResult = ComplexEventRuleActionListDocument.Factory.newInstance();
		
		try {
			preparedStmt = conn.prepareStatement(query);
			resultsSet = preparedStmt.executeQuery(); 
			ComplexEventRuleActionType anActionType = theResult.addNewComplexEventRuleActionList();
			ComplexEventRuleType anInsert;
			XmlObject theRuleToLoad;
			
            while ( resultsSet.next() ) {
            	anInsert = anActionType.addNewInsert();
            	theRuleToLoad = XmlObject.Factory.parse(resultsSet.getString("path_rule"));
    			anInsert.set(theRuleToLoad);
            }
            DebugMessages.println(
					TimeStamp.getCurrentTime(), 
					this.getClass().getSimpleName(),
					"Extracted paths loaded from DB");
        	conn.close();
		} catch (SQLException | XmlException e) {
			System.err.println("Exception during getRulesListForASpecificBPMN ");
			System.err.println(e.getMessage());
		}
        return theResult;
	}

	@Override
	public Activity[] getAllDistinctActivityOFaBPMN(Bpmn theBpmn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bpmn getBPMN(int theBPMNid, String learnpad_bpmn_id) {
		// TODO Auto-generated method stub
		return null;
	}

/*	@Override
	public int saveActivity(Activity activityToSave) {
		String query = " insert into activity (id_bpmn, id_path, name, weigth, expected_kpi)"
    	        + " values (?, ?, ?, ?, ?, ?)";
	try {
		preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, activityToSave.get);
	    preparedStmt.setString(2, activityToSave.getPath_id());
	    preparedStmt.setString(3,activityToSave.getName());
	    preparedStmt.setFloat(4, activityToSave.getWeight());
	    preparedStmt.setBlob(5, serializeObject(activityToSave.getExpectedKpi()));
	 
	    // execute the prepared statement
	    preparedStmt.execute();
	} catch (SQLException e) {
		return 1;
	}  
	DebugMessages.println(
			TimeStamp.getCurrentTime(), 
			this.getClass().getSimpleName(),
			"Path Saved");
	return 0;
	}

	private Blob serializeObject(HashMap<String, Float> expectedKpi) {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out;
		try {
			out = new ObjectOutputStream(bos);
			out.writeObject(expectedKpi);
			return new SerialBlob(bos.toByteArray());
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Activity getActivity(int id_bpmn, String learnpad_id_activity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateActivity(int id_bpmn, String learnpad_id_activity, Activity theActivityToUpdate) {
		// TODO Auto-generated method stub
		return false;
	}*/
}
