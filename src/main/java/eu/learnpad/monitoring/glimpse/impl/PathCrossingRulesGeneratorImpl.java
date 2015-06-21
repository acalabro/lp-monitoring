package eu.learnpad.monitoring.glimpse.impl;

import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionType;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleType;

import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import eu.learnpad.monitoring.glimpse.BPMNExplorer.BPMNPathExplorer;
import eu.learnpad.monitoring.glimpse.rulesGenerator.PathCrossingRulesGenerator;
import eu.learnpad.monitoring.glimpse.rulesGenerator.RuleElements;

public class PathCrossingRulesGeneratorImpl implements PathCrossingRulesGenerator {

	ComplexEventRuleActionListDocument rulesToLoad;
	
	@Override
	public ComplexEventRuleActionListDocument generateRules(
			List<String[]> theUnfoldedBusinessProcess) {

		rulesToLoad = ComplexEventRuleActionListDocument.Factory.newInstance();
		
		ComplexEventRuleActionType ilDoc = rulesToLoad.addNewComplexEventRuleActionList();
		ComplexEventRuleType aInsert;
		
		for(int i = 0; i<theUnfoldedBusinessProcess.size(); i++) {
			
			aInsert = ilDoc.addNewInsert();
			aInsert.setRuleName("Path-Crossing-Check-"+ i +"of"+theUnfoldedBusinessProcess.size());
			aInsert.setRuleType("drools");
			
			
			String concat = "";
			for(int j = 0; j<theUnfoldedBusinessProcess.get(i).length; j++) {
				if (j == 0) {
					concat = "\t\t\t$"+j+"Event : GlimpseBaseEventBPMN("+
							"this.isConsumed == false, this.isException == false, this.getEventName == \"" +
							theUnfoldedBusinessProcess.get(i)[j] + "\");\n";
				} else {
					concat = concat +
							"\t\t\t$"+j+"Event : GlimpseBaseEventBPMN(" +
							"this.isConsumed == false, this.isException == false, this.getEventName == \"" +
							theUnfoldedBusinessProcess.get(i)[j] +
							"\", this after $" + (j-1) + "Event);\n";
				}
			}
			aInsert.setRuleBody(RuleElements.getHeader(aInsert.getRuleName(),  "java") +
					RuleElements.getWhenClause() + 
					concat + 
					RuleElements.getThenClause() +
					RuleElements.getEnd());
		}

		System.out.println(rulesToLoad.toString());
		return rulesToLoad;
	}

	public static void main(String[]args) {
		BPMNPathExplorer asd = new BPMNPathExplorerImpl();
		PathCrossingRulesGenerator cross = new PathCrossingRulesGeneratorImpl();
		
		try {
			DocumentBuilderFactory gg = DocumentBuilderFactory.newInstance();
			Document theDoc = gg.newDocumentBuilder().newDocument();
			
			cross.generateRules(asd.getUnfoldedBPMN(theDoc));
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
