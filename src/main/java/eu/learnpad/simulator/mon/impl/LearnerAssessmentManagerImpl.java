package eu.learnpad.simulator.mon.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.net.ntp.TimeStamp;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import eu.learnpad.simulator.mon.BPMN.PathExplorer;
import eu.learnpad.simulator.mon.controller.DBController;
import eu.learnpad.simulator.mon.coverage.Activity;
import eu.learnpad.simulator.mon.coverage.Bpmn;
import eu.learnpad.simulator.mon.coverage.ComputeScore;
import eu.learnpad.simulator.mon.coverage.Learner;
import eu.learnpad.simulator.mon.coverage.Path;
import eu.learnpad.simulator.mon.impl.PathExplorerImpl;
import eu.learnpad.simulator.mon.impl.PathGeneratorImpl;
import eu.learnpad.simulator.mon.manager.LearnerAssessmentManager;
import eu.learnpad.simulator.mon.rulesGenerator.PathGenerator;
import eu.learnpad.simulator.mon.utils.DebugMessages;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;

public class LearnerAssessmentManagerImpl extends LearnerAssessmentManager {

	private Document theBPMN;
	private PathExplorer bpmnExplorer;
	private PathGenerator crossRulesGenerator;
	private DBController databaseController;
	private ComplexEventRuleActionListDocument rulesLists;

	public LearnerAssessmentManagerImpl(DBController databaseController) {
		
		//Creation of the BPMNExplorerEngine
		 bpmnExplorer = new PathExplorerImpl();
		 		
		//the instance of DB used
		this.databaseController = databaseController;
		
		//Creation of the PathCrossingRulesGenerator object
		crossRulesGenerator = new PathGeneratorImpl();
	}
		
	public void run() {
		
		databaseController.connectToDB();
	}
	
	@Override
	public DBController getDBController() {
		return this.databaseController;
	}
		
	@Override
	public Document setBPModel(String xmlMessagePayload) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document dom = null;

		DocumentBuilder docBuilder = dbf.newDocumentBuilder();
		dom = docBuilder.parse(new InputSource(new ByteArrayInputStream(xmlMessagePayload.getBytes("utf-8"))));
		this.theBPMN = dom;
		return dom;
	}

	@Override
	public ComplexEventRuleActionListDocument elaborateModel(String xmlMessagePayload, Vector<Learner> usersInvolved, String sessionID) {
		
		try {
			theBPMN = setBPModel(xmlMessagePayload);
			String theBPMNidentifier = getBpmnIDFromXML(theBPMN);
			
			if (!databaseController.checkIfBPHasBeenAlreadyExtracted(theBPMNidentifier)) {
				
				Date now = new Date();
				Bpmn newBpmn = new Bpmn(theBPMNidentifier,now,0);

				Vector<Activity[]> theUnfoldedBPMN = bpmnExplorer.getUnfoldedBPMN(theBPMN);
				Vector<Path> theGeneratedPath = crossRulesGenerator.generatePathsRules(
																	crossRulesGenerator.generateAllPaths(theUnfoldedBPMN, newBpmn.getId()));
				theGeneratedPath = setAllAbsoluteSessionScores(theGeneratedPath);
				
				this.rulesLists = crossRulesGenerator.instantiateRulesSetForUsersInvolved(
						databaseController.savePathsForBPMN(theGeneratedPath),usersInvolved, sessionID);
				
				newBpmn.setAbsoluteBpScore(ComputeScore.absoluteBP(theGeneratedPath));
				
				databaseController.saveBPMN(newBpmn);
			} else {
				this.rulesLists = crossRulesGenerator.instantiateRulesSetForUsersInvolved(
						databaseController.getBPMNPaths(theBPMNidentifier),
						usersInvolved, sessionID);
			}			
		} catch (ParserConfigurationException | SAXException | IOException e ) {
			e.printStackTrace();
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					this.getClass().getSimpleName(),e.getCause().toString());
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					this.getClass().getSimpleName(),"The message contains an INVALID BPMN");
		}
		return rulesLists;
	}

	@Override
	public Vector<Path> setAllAbsoluteSessionScores(Vector<Path> theGeneratedPath) {

		for (int i =0; i< theGeneratedPath.size(); i++) {
			theGeneratedPath.get(i).setAbsoluteSessionScore(ComputeScore.absoluteSession(theGeneratedPath.get(i).getActivities()));
		}
		return theGeneratedPath;
		
	}

	private String getBpmnIDFromXML(Document theBPMN2) {
		//TODO: create correct method
		return "a1446728873453458831";
	}

	@Override
	public void computeAndSaveScores(String learnersID, int idPath, String idBPMN, float sessionScore) {
		
		String[] learnersIDs = learnersID.split("-");
		for(int i = 0; i<learnersIDs.length; i++) {
		
			databaseController.setLearnerSessionScore(Integer.parseInt(learnersIDs[i]), idPath, idBPMN, sessionScore);
			
			databaseController.setLearnerGlobalScore(Integer.parseInt(learnersIDs[i]),
					ComputeScore.learnerGlobal(databaseController.getLearnerBPMNScores(Integer.parseInt(learnersIDs[i]))));
			
			databaseController.setLearnerRelativeGlobalScore(Integer.parseInt(learnersIDs[i]), 
					ComputeScore.learnerRelativeGlobal(
							databaseController.getLearnerRelativeBPScores(Integer.parseInt(learnersIDs[i]))));

			databaseController.setLearnerAbsoluteGlobalScore(Integer.parseInt(learnersIDs[i]), 
					ComputeScore.learnerAbsoluteGlobal(
							databaseController.getBPMNAbsoluteScoresExecutedByLearner(Integer.parseInt(learnersIDs[i]))));
			
			databaseController.setLearnerBPScore(Integer.parseInt(learnersIDs[i]), idBPMN,
					ComputeScore.learnerBP(
							databaseController.getMaxSessionScores(Integer.parseInt(learnersIDs[i]), idBPMN)));

			databaseController.setLearnerRelativeBPScore(Integer.parseInt(learnersIDs[i]), idBPMN, 
					ComputeScore.learnerRelativeBP(databaseController.getPathsExecutedByLearner(Integer.parseInt(learnersIDs[i]), idBPMN)));			
			
System.out.println("asd");
//			databaseController.setLearnerBPCoverage(Integer.parseInt(learnersIDs[i]), idBPMN, 
//					ComputeScore.BPCoverage());
			
															
		}
	}
		
}
