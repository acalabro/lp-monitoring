package eu.learnpad.monitoring.glimpse.services;

import eu.learnpad.monitoring.glimpse.cep.ComplexEventProcessor;
import eu.learnpad.monitoring.glimpse.impl.RuleTemplateManager;
import eu.learnpad.monitoring.glimpse.impl.ServiceLocatorImpl;
import eu.learnpad.monitoring.glimpse.impl.ServiceLocatorParseViolationReceivedFromBSM;

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
