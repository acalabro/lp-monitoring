package eu.learnpad.simulator.mon.manager;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;

public abstract class LearnerAssessmentManager extends Thread {

	public abstract Document setBPModel(String xmlMessagePayload) throws ParserConfigurationException, SAXException, IOException;
	public abstract ComplexEventRuleActionListDocument ExploreBPSavePathsGenerateAndSaveRules(Document dom);
	public abstract ComplexEventRuleActionListDocument elaborateModel(String xmlMessagePayload);
}
