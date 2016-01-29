package eu.learnpad.simulator.mon.impl;

import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionType;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleType;

import java.util.Vector;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

import eu.learnpad.simulator.mon.coverage.Activity;
import eu.learnpad.simulator.mon.coverage.ComputeScore;
import eu.learnpad.simulator.mon.coverage.Learner;
import eu.learnpad.simulator.mon.coverage.Path;
import eu.learnpad.simulator.mon.rulesGenerator.PathGenerator;
import eu.learnpad.simulator.mon.rulesGenerator.RuleElements;

public class PathGeneratorImpl implements PathGenerator {

	ComplexEventRuleActionListDocument rulesToLoad;
	
	@Override
	public ComplexEventRuleActionListDocument generateAllPathsRules(
			Vector<Activity[]> theUnfoldedBusinessProcess) {

		rulesToLoad = ComplexEventRuleActionListDocument.Factory.newInstance();
		
		ComplexEventRuleActionType ilDoc = rulesToLoad.addNewComplexEventRuleActionList();
		ComplexEventRuleType aInsert;
		
		for(int i = 0; i<theUnfoldedBusinessProcess.size(); i++) {
			
			aInsert = ilDoc.addNewInsert();
			ComplexEventRuleType generated = generateRuleForSinglePath(theUnfoldedBusinessProcess.get(i), "autoGenerated-"+i );
			aInsert.setRuleBody(generated.getRuleBody());
			aInsert.setRuleName(generated.getRuleName());
			aInsert.setRuleType(generated.getRuleType());
		}
		return rulesToLoad;
	}
	
	@Override
	public ComplexEventRuleType generateRuleForSinglePath(
			Activity[] anActivitiesSet, String rulesName) {

		ComplexEventRuleType aInsert = ComplexEventRuleType.Factory.newInstance();
		aInsert.setRuleName("Path-Crossing-Check-" + rulesName);
		aInsert.setRuleType("drools");
		
		
		String concat = "";
		for(int j = 0; j<anActivitiesSet.length; j++) {
			if (j == 0) {
				concat = "\t\t\t$"+j+"Event : GlimpseBaseEventBPMN("+
						"this.isConsumed == false, this.sessionID == \"##SESSIONIDPLACEHOLDER##\""
						+ " this.isException == false, this.getEventName == \"" +
						anActivitiesSet[j].getName() + "\");\n";
			} else {
				concat = concat +
						"\t\t\t$"+j+"Event : GlimpseBaseEventBPMN(" +
						"this.isConsumed == false, this.sessionID == \"##SESSIONIDPLACEHOLDER##\""
						+ " this.isException == false, this.getEventName == \"" +
						anActivitiesSet[j].getName() +
						"\", this after $" + (j-1) + "Event);\n";
			}
		}
		aInsert.setRuleBody(RuleElements.getHeader(aInsert.getRuleName(),  "java") +
				RuleElements.getWhenClause() + 
				concat + 
				RuleElements.getThenClause(anActivitiesSet) +
				RuleElements.getEnd());
		return aInsert;
	}

	@Override
	public Vector<Path> generatePaths(ComplexEventRuleActionListDocument generatedRules, Vector<Activity[]> theUnfoldedBPMN, String theBPMNidentifier) {
		
		Vector<Path> thePathOfTheBPMN = new Vector<Path>();
		
		for (int i =0; i<theUnfoldedBPMN.size();i++) {
					
			Path theCompletePathObject = new Path(i, theBPMNidentifier, ComputeScore.absoluteSession(theUnfoldedBPMN.get(i)),
													generatedRules.getComplexEventRuleActionList().getInsertArray()[i].toString(), theUnfoldedBPMN.get(i));
			thePathOfTheBPMN.add(theCompletePathObject);
		}	
		return thePathOfTheBPMN;
	}
	
	@Override
	public ComplexEventRuleActionListDocument instantiateRulesSetForUsersInvolved(Vector<Path> thePathsToInstantiate, Vector<Learner> usersInvolved, String sessionID) {
		rulesToLoad = ComplexEventRuleActionListDocument.Factory.newInstance();
		
		String updatedPath;
		
		for (int i = 0; i<thePathsToInstantiate.size(); i++) {
			
			updatedPath = thePathsToInstantiate.get(i).getPathRule().replaceAll("##SESSIONIDPLACEHOLDER##", sessionID);
			
			String usersInvolvedText = "";
			
			if (usersInvolved.size() > 1) {
				for (int j=0; j< usersInvolved.size()-1;j++) {
					usersInvolvedText = usersInvolvedText + String.valueOf(usersInvolved.get(j).getId()) + " && ";
				}
				usersInvolvedText = usersInvolvedText + String.valueOf(usersInvolved.get(usersInvolved.size()-1).getId());
			}
			else
				usersInvolvedText = String.valueOf(usersInvolved.get(0).getId());

			updatedPath.replaceAll("##USERSINVOLVEDIDS##", usersInvolvedText);

			try {
				ComplexEventRuleType rule = ComplexEventRuleType.Factory.parse(updatedPath);
				rulesToLoad.addNewComplexEventRuleActionList().setInsertArray(i, rule);
			} catch (XmlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rulesToLoad;
	}
}
