package eu.learnpad.monitoring.glimpse.rulesGenerator;

import java.util.List;

import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;

public interface PathCrossingRulesGenerator {
	ComplexEventRuleActionListDocument generateRules(List<String[]> theUnfoldedBusinessProcess);
}
