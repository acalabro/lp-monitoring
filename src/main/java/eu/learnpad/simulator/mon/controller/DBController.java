package eu.learnpad.simulator.mon.controller;

import java.util.Date;
import java.util.List;


import eu.learnpad.simulator.mon.coverage.Bpmn;
import eu.learnpad.simulator.mon.coverage.Category;
import eu.learnpad.simulator.mon.coverage.Learner;
import eu.learnpad.simulator.mon.coverage.Path;
import eu.learnpad.simulator.mon.coverage.Role;
import eu.learnpad.simulator.mon.coverage.Topic;

public interface DBController {
	
	public boolean connectToDB();
	
	//global methods
	public boolean checkIfBPHasBeenAlreadyExtracted(int idBPMN);
	public List<Path> getBPMNPaths(int idBPMN);
	
	public float getLearnerBPScore(int idLearner, int idBPMN);
	public int setLearnerBPScore(int idLearner, int idBPMN, float BPScore);
	
	public float getLearnerRelativeBPScore(int idLearner, int idBPMN);
	public int setLearnerRelativeBPScore(int idLearner, int idBPMN, float relativeBPScore);
	
	public float getLearnerBPCoverage(int idLearner, int idBPMN);
	public int setLearnerBPCoverage(int idLearner, int idBPMN, float BPCoverage);
	
	public float getLearnerSessionScore(int idLearner, int idPath, int idRole, Date executionDate);
	public float getLearnerSessionScore(Learner theLearner, Path thePath, int idRole, Date executionDate);
	public int setLearnerSessionScore(int idLearner, int idPath, int idRole, float sessionScore);
	public int setLearnerSessionScore(Learner theLearner, Path thePath, float sessionScore);
	
	//BPMN methods
	public int saveBPMN(Bpmn theBPMN);
	public Bpmn getBPMN(int theBPMNid);
	public boolean updateBpmn(int theBPMNid, Bpmn theBpmnToUpdate);
	
	//Category methods
	public int saveCategory(Category theCategory);
	public Category getCategory(int theCategoryID);
	public boolean updateCategory(int theCategoryid, Category theCategoryToUpdate);
	
	//Learner methods
	public int saveLearnerProfile(Learner theLearner);
	public Learner getLearner(int idLearner);
	public boolean updateLearner(int idLearner, Learner theLearnerToUpdate);
		
	//Path methods
	public int savePath(Path thePath);
	public Path getPath(int thePathID);
	public boolean updatePath(int thePathId, Path thePathToUpdate);
	
	//Role methods
	public int saveRole(Role theRole);
	public Role getRole(int theRoleID);
	public boolean updateRole(int theRoleId, Role theRoleToUpdate);
	
	//Topic methods
	public int saveTopic(Topic theTopic);
	public Topic getTopic(int theTopicID);
	public boolean updateTopic(int theTopicId, Topic theTopicToUpdate);
	
}