package eu.learnpad.simulator.mon.coverage;

import java.util.Vector;

public class ComputeScore {

	
	/*RELATIVE SCORE*/
	
	public static float relativeBP(Vector<Path> executedByUser) {
		float relativeBP = 0;
		for (int i = 0; i<executedByUser.size(); i++) {
			relativeBP = relativeBP + executedByUser.get(i).getAbsoluteSessionScore();
		}
		return relativeBP;
	}

//	public static float global(Vector<Bpmn> executedByUser) {
//		float relativeBP = 0;
//		for (int i = 0; i<executedByUser.size(); i++) {
//			relativeBP = relativeBP + executedByUser.get(i).getAbsoluteSessionScore();
//		}
//		return relativeBP;
	
	
	/*ABSOLUTE SCORE*/
	public static float absoluteSession(Activity[] activities) {

			float absoluteSession = 0;
			for (int i=0; i< activities.length; i++) {
				absoluteSession = absoluteSession + activities[i].getWeight();			
			}
			return absoluteSession;
	}

	public static float absoluteBP(Vector<Path> thePathOfTheBPMN) {
		float absoluteBP = 0;
		for(int i = 0; i<thePathOfTheBPMN.size(); i++) {
			absoluteBP = absoluteBP + thePathOfTheBPMN.get(i).getAbsoluteSessionScore();
		}
		return absoluteBP;
	}
	
	public static float absoluteGlobal(Vector<Bpmn> theBPMNexecutedByTheUser) {
		float absoluteGlobal = 0;
		for(int i = 0; i<theBPMNexecutedByTheUser.size(); i++) {
			absoluteGlobal = absoluteGlobal + theBPMNexecutedByTheUser.get(i).getAbsoluteBpScore();
		}
		return absoluteGlobal;
	}

	
}
