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
import eu.learnpad.simulator.mon.coverage.Path;
import eu.learnpad.simulator.mon.impl.PathExplorerImpl;
import eu.learnpad.simulator.mon.impl.PathCrossingRulesGeneratorImpl;
import eu.learnpad.simulator.mon.manager.LearnerAssessmentManager;
import eu.learnpad.simulator.mon.rulesGenerator.PathCrossingRulesGenerator;
import eu.learnpad.simulator.mon.utils.DebugMessages;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;

public class LearnerAssessmentManagerImpl extends LearnerAssessmentManager {

	private Document theBPMN;
	private PathExplorer bpmnExplorer;
	private PathCrossingRulesGenerator crossRulesGenerator;
	private DBController databaseController;
	private ComplexEventRuleActionListDocument rulesLists;

	public LearnerAssessmentManagerImpl(DBController databaseController) {
		
		//Creation of the BPMNExplorerEngine
		 bpmnExplorer = new PathExplorerImpl();
		 		
		//the instance of DB used
		this.databaseController = databaseController;
		
		//Creation of the PathCrossingRulesGenerator object
		crossRulesGenerator = new PathCrossingRulesGeneratorImpl();
	}
		
	public void run() {
		
		databaseController.connectToDB();
	}
		
	public ComplexEventRuleActionListDocument ExploreBPSavePathsGenerateAndSaveRules(Document dom) {
		
		Vector<Activity[]> paths = bpmnExplorer.getUnfoldedBPMN(dom);
		
		rulesLists = crossRulesGenerator.generateAllPathsRules(paths);
		
		Date now = new Date();
		
//		Bpmn newBpmn = new Bpmn(
//				Integer.parseInt(dom.getElementsByTagName("bpmnID").item(0).getFirstChild().getTextContent()), now);
		Bpmn newBpmn = new Bpmn("a"+System.currentTimeMillis(),now);
		
		databaseController.saveBPMN(newBpmn);
		Path pathToSave;
		for (int i = 0; i< paths.size(); i++) {
			pathToSave = new Path(i,newBpmn.getId(),rulesLists.getComplexEventRuleActionList().getInsertArray()[i].toString());
			databaseController.savePath(pathToSave);
		}
		return rulesLists;
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
	public ComplexEventRuleActionListDocument elaborateModel(String xmlMessagePayload) {
		ComplexEventRuleActionListDocument rulesList;
		try {
			theBPMN = setBPModel(xmlMessagePayload);
			if (!databaseController.checkIfBPHasBeenAlreadyExtracted(getBpmnIDFromXML(theBPMN))) {
				rulesList = ExploreBPSavePathsGenerateAndSaveRules(theBPMN);
			} else {
				rulesList = databaseController.getRulesListForASpecificBPMN(getBpmnIDFromXML(theBPMN));
			}			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			DebugMessages.println(
					TimeStamp.getCurrentTime(), 
					this.getClass().getSimpleName(),e.getCause().toString());
			DebugMessages.println(
					TimeStamp.getCurrentTime(), 
					this.getClass().getSimpleName(),
					"The message contains an INVALID BPMN");
		}
		return rulesLists;
	}

	private String getBpmnIDFromXML(Document theBPMN2) {
		//TODO: fix it
		return "asd";
	}
}
