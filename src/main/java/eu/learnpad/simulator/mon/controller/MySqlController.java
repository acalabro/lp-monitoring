package eu.learnpad.simulator.mon.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.net.ntp.TimeStamp;
import org.w3c.dom.Document;

import eu.learnpad.simulator.mon.utils.DebugMessages;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;

public class MySqlController implements DBController {

	private Properties connectionProp;
	
	public MySqlController(Properties databaseConnectionProperties) {
		connectionProp = databaseConnectionProperties;
	}

	@Override
	public boolean connectToDB() {

		try (Connection connection = DriverManager.getConnection(
				connectionProp.getProperty("database.host"),
				connectionProp.getProperty("database.username"),
				connectionProp.getProperty("database.password"))) {
		    System.out.println("Database connected!");
		} catch (SQLException e) {
		    throw new IllegalStateException(TimeStamp.getCurrentTime() + " "
		    		+ MySqlController.class.getSimpleName() +
		    		" - Could not connect to db " + connectionProp.getProperty("database.host"), e);
		}
		DebugMessages.println(TimeStamp.getCurrentTime(), MySqlController.class.getSimpleName(),"Connected to db " + connectionProp.getProperty("database.host"));
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
	public void saveBPMNPaths(int idBPMN, List<List<String>> paths) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<List<String>> getBPMNPaths(int idBPMN) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveSingleBPMNPath(int idBPMN, List<String> path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getSingleBPMNPaths(int idBPMN, int idSinglePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveLearnerProfile(String name, String surname) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getLearnerProfile(int idLearner) {
		// TODO Auto-generated method stub

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
