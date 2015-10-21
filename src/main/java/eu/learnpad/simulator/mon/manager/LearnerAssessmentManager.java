package eu.learnpad.simulator.mon.manager;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import eu.learnpad.simulator.mon.BPMNExplorer.BPMNPathExplorer;
import eu.learnpad.simulator.mon.impl.BPMNPathExplorerImpl;
import eu.learnpad.simulator.mon.impl.PathCrossingRulesGeneratorImpl;
import eu.learnpad.simulator.mon.rulesGenerator.PathCrossingRulesGenerator;

public class LearnerAssessmentManager {

	private Document theBPMN;
	private BPMNPathExplorer bpmnExplorer;
	private PathCrossingRulesGenerator crossRulesGenerator;

	public LearnerAssessmentManager() {
		
		//Creation of the BPMNExplorerEngine
		bpmnExplorer = new BPMNPathExplorerImpl();
		
		//Creation of the PathCrossingRulesGenerator object
		crossRulesGenerator = new PathCrossingRulesGeneratorImpl();
		
	}

	public Document setBPModel(String xmlMessagePayload) {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document dom = null;
		try {
			
			DocumentBuilder docBuilder = dbf.newDocumentBuilder();
			dom = docBuilder.parse(xmlMessagePayload);
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
		this.theBPMN = dom;
		return dom;
	}
	
	public void ExploreBPSavePathsGenerateAndSaveRules(Document dom){
		crossRulesGenerator.generateRules(bpmnExplorer.getUnfoldedBPMN(dom));
		//TODO:
	}
}
