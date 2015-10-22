package eu.learnpad.simulator.mon.impl;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import eu.learnpad.simulator.mon.BPMN.PathExplorer;
import eu.learnpad.simulator.mon.controller.DBController;
import eu.learnpad.simulator.mon.impl.PathExplorerImpl;
import eu.learnpad.simulator.mon.impl.PathCrossingRulesGeneratorImpl;
import eu.learnpad.simulator.mon.manager.LearnerAssessmentManager;
import eu.learnpad.simulator.mon.rulesGenerator.PathCrossingRulesGenerator;

public class LearnerAssessmentManagerImpl extends LearnerAssessmentManager {

	private Document theBPMN;
	private PathExplorer bpmnExplorer;
	private PathCrossingRulesGenerator crossRulesGenerator;
	private DBController databaseController;

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
	
	public boolean checkModel(String xmlMessagePayload) {  
		
		try {
			 this.theBPMN = setBPModel(xmlMessagePayload);
		} catch(ParserConfigurationException|SAXException|IOException gg) {
			theBPMN = null;
			return false;
		} finally {
			
		}
		return true;
	}
	
	public void ExploreBPSavePathsGenerateAndSaveRules(Document dom){
		crossRulesGenerator.generateRules(bpmnExplorer.getUnfoldedBPMN(dom));
		//TODO:
	}

	@Override
	public Document setBPModel(String xmlMessagePayload) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document dom = null;
	
		DocumentBuilder docBuilder = dbf.newDocumentBuilder();
		dom = docBuilder.parse(xmlMessagePayload);
		this.theBPMN = dom;
		return dom;
	}
	
	
}
