package eu.learnpad.simulator.mon.manager;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public abstract class LearnerAssessmentManager extends Thread {

	public abstract Document setBPModel(String xmlMessagePayload) throws ParserConfigurationException, SAXException, IOException;
	public abstract void ExploreBPSavePathsGenerateAndSaveRules(Document dom);
	public abstract void elaborateModel(String xmlMessagePayload);
}
