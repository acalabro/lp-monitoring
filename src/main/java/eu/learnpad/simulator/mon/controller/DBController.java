package eu.learnpad.simulator.mon.controller;

import java.util.Date;
import java.util.List;

import org.w3c.dom.Document;

import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;

public interface DBController {
	
	public boolean connectToDB();
		
	//global saving methods
	//TODO:save a single path, the absolute session score and the path rule
	
	//BPMN methods
	public int saveBPMN(Document theBPMN, Date extractionDate, String category);
	public Document getBPMN(int theBPMNid);
	
	public void saveBPMNCoverageRules(int idBPMN, ComplexEventRuleActionListDocument theRulesSet);
	public ComplexEventRuleActionListDocument getBPMNCoverageRules(int idBPMN);
	
	public String getBPMNCategory(int theBPMNid);
	public void setBPMNCategory(int theBPMNid, int theCategoryID);
	
	public float getBPMNAbsoluteScore(int theBPMNid);
	public void setBPMNAbsoluteScore(int theBPMNid, float absoluteScore);
	
	public Date getBPMNExtractionDate(int theBPMNid);
	public void updateBPMNExtractionDate(int theBPMNid, Date extractionDatefloat);
	
	//Category methods
	public String getCategoryName(int theCategoryID);
	public void setCategoryName(int theCategoryID, String theCategoryName);
	
	public int getCategoryID(String theCategoryName);
	public int createCategory(String category);
	
	
	//Paths methods
	public void saveBPMNPaths(int idBPMN, List<List<String>> paths);
	public List<List<String>> getBPMNPaths(int idBPMN);
	
	public int saveSingleBPMNPath(int idBPMN, List<String> path);
	public List<String> getSingleBPMNPaths(int idBPMN, int idSinglePath);
	
	
	//Learner methods
	public int saveLearnerProfile(String name, String surname);
	public void getLearnerProfile(int idLearner);
	
	public float getLearnerBPScore(int idLearner, int idBPMN);
	public void setLearnerBPScore(int idLearner, int idBPMN, float BPScore);
	
	public float getLearnerSessionScore(int idLearner, int idBPMN);
	public void setLearnerSessionScore(int idLearner, int idBPMN, float SessionScore);
	
	public float getLearnerRelativeBPScore(int idLearner, int idBPMN);
	public void setLearnerRelativeBPScore(int idLearner, int idBPMN, float BPScore);
	
	public float getLearnerGlobalScore(int idLearner);
	public void setLearnerGlobalScore(int idLearner,float GlobalScore);
	
	public float getLearnerRelativeGlobalScore(int idLearner);
	public void setLearnerRelativeGlobalScore(int idLearner,float RelativeGlobalScore);
	
	public float getLearnerAbsoluteGlobalScore(int idLearner);
	public void setLearnerAbsoluteGlobalScore(int idLearner,float AbsoluteGlobalScore);
}
