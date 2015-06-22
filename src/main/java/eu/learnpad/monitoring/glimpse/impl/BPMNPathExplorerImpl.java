package eu.learnpad.monitoring.glimpse.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.w3c.dom.Document;

import eu.learnpad.monitoring.glimpse.BPMNExplorer.BPMNPathExplorer;

public class BPMNPathExplorerImpl implements BPMNPathExplorer {

	public List<String[]> lastExploredBPMN;
	
	@Override
	public List<String[]> getUnfoldedBPMN(Document theBusinessProcessToUnfold) {
		
		//call the software provided by third parties
		lastExploredBPMN = new ArrayList<String[]>();
		lastExploredBPMN.add(new String[]
						{"Check Application","Validate elegibility", "Send rejection letter"});
		lastExploredBPMN.add(new String[]
						{"Check Application","Validate elegibility","Invite for interview",
									"Make interview", "Decide application", "Send rejection letter"});
		lastExploredBPMN.add(new String[]{"Check Application","Validate elegibility",
									"Invite for interview", "Make interview", "Decide application", "Decide fee", "Send acceptance letter"});
		return lastExploredBPMN;
	}

	@Override
	public void setUnfoldedBPMN(Vector<String[]> theUnfoldedBusinessProcess) {
		lastExploredBPMN = theUnfoldedBusinessProcess;
	}

}
