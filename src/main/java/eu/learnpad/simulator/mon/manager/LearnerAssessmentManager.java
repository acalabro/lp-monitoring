package eu.learnpad.simulator.mon.manager;

import org.w3c.dom.Document;

public abstract class LearnerAssessmentManager extends Thread{

	public abstract Document setBPModel(String xmlMessagePayload);
	public abstract void ExploreBPSavePathsGenerateAndSaveRules(Document dom);
	
}
