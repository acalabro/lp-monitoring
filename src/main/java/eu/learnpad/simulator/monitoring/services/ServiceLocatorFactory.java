package eu.learnpad.simulator.monitoring.services;

import eu.learnpad.simulator.monitoring.cep.ComplexEventProcessor;
import eu.learnpad.simulator.monitoring.impl.RuleTemplateManager;
import eu.learnpad.simulator.monitoring.impl.ServiceLocatorImpl;
import eu.learnpad.simulator.monitoring.impl.ServiceLocatorParseViolationReceivedFromBSM;

public class ServiceLocatorFactory {
	
	public static ServiceLocator getServiceLocatorImpl(
			ComplexEventProcessor engine,
			String soapRequestFilePath,
			RuleTemplateManager ruleTemplateManager,
			String bsmWsdlUriFilePath,
			String regexPatternFilePath)
	{
		ServiceLocator locator = new ServiceLocatorImpl(
				engine,
				soapRequestFilePath,
				ruleTemplateManager,
				bsmWsdlUriFilePath,
				regexPatternFilePath);
		
		return locator;
	}
	
	public static ServiceLocator getServiceLocatorParseViolationReceivedFromBSM(ComplexEventProcessor engine,
			RuleTemplateManager ruleTemplateManager,
			String regexPatternFilePath)
	{
		ServiceLocator locator = new ServiceLocatorParseViolationReceivedFromBSM(engine,
				ruleTemplateManager,
				regexPatternFilePath);
		return locator;
	}
}
