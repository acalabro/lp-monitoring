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
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionType;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleType;

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
				Bpmn newBpmn = new Bpmn("a"+System.currentTimeMillis(),now,0);

				Vector<Activity[]> theUnfoldedBPMN = bpmnExplorer.getUnfoldedBPMN(theBPMN);
				Vector<Path> theGeneratedPath = crossRulesGenerator.generatePaths(
												crossRulesGenerator.generateAllPathsRules(theUnfoldedBPMN),
																	theUnfoldedBPMN, theBPMNidentifier);
				
				this.rulesLists = crossRulesGenerator.instantiateRulesSetForUsersInvolved(
						databaseController.savePathsForBPMN(theGeneratedPath),usersInvolved, sessionID);
				
				newBpmn.setAbsoluteBpScore(ComputeScore.absoluteBP(theGeneratedPath));
				databaseController.saveBPMN(newBpmn);
			} else {
				this.rulesLists = crossRulesGenerator.instantiateRulesSetForUsersInvolved(
						databaseController.getBPMNPaths(getBpmnIDFromXML(theBPMN)),
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

	private String getBpmnIDFromXML(Document theBPMN2) {
		
		return "a1446728873453458831";
	}
	
	@Override
	public ComplexEventRuleActionListDocument ExploreBPSavePathsGenerateAndSaveRules(Document dom) {
			
			Date now = new Date();
	//		Bpmn newBpmn = new Bpmn(
	//		Integer.parseInt(dom.getElementsByTagName("bpmnID").item(0).getFirstChild().getTextContent()), now);
					
			Vector<Activity[]> activitiesSet = bpmnExplorer.getUnfoldedBPMN(dom);
			
			Bpmn newBpmn = new Bpmn("a"+System.currentTimeMillis(),now,0);
			
			rulesLists = ComplexEventRuleActionListDocument.Factory.newInstance();
			
			ComplexEventRuleActionType ilDoc = rulesLists.addNewComplexEventRuleActionList();
			ComplexEventRuleType[] theRulesToInsert = new ComplexEventRuleType[activitiesSet.size()];
			
			Vector<Path> thePathOfTheBPMN = new Vector<Path>();
			
			for (int i =0; i<activitiesSet.size();i++) {
				theRulesToInsert[i] = crossRulesGenerator.generateRuleForSinglePath(activitiesSet.get(i),
						"BPMN-ID:" + newBpmn.getId() + " ActivitiesSet: " + (i+1) + " of "+ activitiesSet.size());
				
				Path theCompletePathObject = new Path(i, newBpmn.getId(), ComputeScore.absoluteSession(activitiesSet.get(i)),
														theRulesToInsert[i].toString(), activitiesSet.get(i));
				thePathOfTheBPMN.add(theCompletePathObject);
				databaseController.savePath(theCompletePathObject);			
			}
			newBpmn.setAbsoluteBpScore(ComputeScore.absoluteBP(thePathOfTheBPMN));
			databaseController.saveBPMN(newBpmn);
			ilDoc.setInsertArray(theRulesToInsert);
			return rulesLists;
		}

	
}
