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

import eu.learnpad.simulator.mon.coverage.Bpmn;
import eu.learnpad.simulator.mon.coverage.Category;
import eu.learnpad.simulator.mon.coverage.Learner;
import eu.learnpad.simulator.mon.coverage.Path;
import eu.learnpad.simulator.mon.coverage.Role;
import eu.learnpad.simulator.mon.coverage.Topic;
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
			DebugMessages.print(TimeStamp.getCurrentTime(), MySqlController.class.getSimpleName(),"Connection to db " + connectionProp.getProperty("database.host"));
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Bpmn getBPMN(int theBPMNid) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
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
}
