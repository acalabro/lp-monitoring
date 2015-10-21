package eu.learnpad.simulator.mon.manager;

import java.util.List;

import org.w3c.dom.Document;

import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;

public interface DBController {

	public void connectToDB(String connectionString);
	
	public void saveBPMN(Document theBPMN);
	public Document getBPMN(String theBPMNid);
	
	public void saveBPMNPats(String idBPMN, List<String> paths);
	public List<String> getBPMNPaths(String idBPMN);
	
	public void saveBPMNCoverageRules(String idBPMN, ComplexEventRuleActionListDocument theRulesSet);
	public ComplexEventRuleActionListDocument getBPMNCoverageRules(String idBPMN);
	
	public String getBPMNCategory(String theBPMNid);
	public void setBPMNCategory(String theBPMNid, String category);
	
	
	//Learner methods
//
}
