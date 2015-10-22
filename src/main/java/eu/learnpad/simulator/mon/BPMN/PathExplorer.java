package eu.learnpad.simulator.mon.BPMN;

import java.util.List;
import java.util.Vector;

import org.w3c.dom.Document;

public interface PathExplorer {
	
	List<String[]> getUnfoldedBPMN(Document theBusinessProcessToUnfold);
	void setUnfoldedBPMN(Vector<String[]> theUnfoldedBusinessProcess);
	
}
