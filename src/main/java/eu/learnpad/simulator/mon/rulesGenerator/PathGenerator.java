package eu.learnpad.simulator.mon.rulesGenerator;

import java.util.Vector;

import eu.learnpad.simulator.mon.coverage.Activity;
import eu.learnpad.simulator.mon.coverage.Learner;
import eu.learnpad.simulator.mon.coverage.Path;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleType;

public interface PathGenerator {
	ComplexEventRuleActionListDocument generateAllPathsRules(Vector<Activity[]> theUnfoldedBusinessProcess);
	ComplexEventRuleType generateRuleForSinglePath(Activity[] anActivitiesSet, String rulesName); 
	Vector<Path> generatePaths(ComplexEventRuleActionListDocument generatedRules, Vector<Activity[]> theUnfoldedBPMN, String theBPMNidentifier);
	ComplexEventRuleActionListDocument instantiateRulesSetForUsersInvolved(Vector<Path> thePathsToInstantiate, Vector<Learner> usersInvolved, String sessionID);
}
