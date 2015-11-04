package eu.learnpad.simulator.mon.rulesGenerator;

import java.util.Vector;

import eu.learnpad.simulator.mon.coverage.Activity;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;

public interface PathCrossingRulesGenerator {
	ComplexEventRuleActionListDocument generateAllPathsRules(Vector<Activity[]> theUnfoldedBusinessProcess);
	ComplexEventRuleActionListDocument generateSinglePathRule(Activity[] thePathEventsIdentifierString);
}
