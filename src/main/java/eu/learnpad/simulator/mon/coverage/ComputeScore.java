package eu.learnpad.simulator.mon.coverage;

import java.util.Vector;

public class ComputeScore {

	/*SINGLE SCORES*/
	public static float global(Vector<Activity> theActivitiesExecutedByUser) {
		float global = 0;
		for (int i = 0; i<theActivitiesExecutedByUser.size(); i++){
			global += theActivitiesExecutedByUser.get(i).getWeight();
		}
		return global;
	}
	
	
	/*RELATIVE SCORES*/
	public static float relativeBP(Vector<Path> executedByUser) {
		float relativeBP = 0;
		for (int i = 0; i<executedByUser.size(); i++) {
			relativeBP += executedByUser.get(i).getAbsoluteSessionScore();
		}
		return relativeBP;
	}
	
	public static float relativeGlobal(Vector<Float> relativeBPScoreExecutedByUser) {
		float relativeGlobal = 0;
		for (int i = 0; i<relativeBPScoreExecutedByUser.size(); i++) {
			relativeGlobal += relativeBPScoreExecutedByUser.get(i);
		}
		return relativeGlobal;
	}

//	public static float global(Vector<Bpmn> executedByUser) {
//		float relativeBP = 0;
//		for (int i = 0; i<executedByUser.size(); i++) {
//			relativeBP += executedByUser.get(i).getAbsoluteSessionScore();
//		}
//		return relativeBP;
	
	
	/*ABSOLUTE SCORES*/
	public static float absoluteSession(Activity[] activities) {

			float absoluteSession = 0;
			for (int i=0; i< activities.length; i++) {
				absoluteSession += activities[i].getWeight();			
			}
			return absoluteSession;
	}

	public static float absoluteBP(Vector<Path> thePathOfTheBPMN) {
		float absoluteBP = 0;
		for(int i = 0; i<thePathOfTheBPMN.size(); i++) {
			absoluteBP += thePathOfTheBPMN.get(i).getAbsoluteSessionScore();
		}
		return absoluteBP;
	}
	
	public static float absoluteGlobal(Vector<Bpmn> theBPMNexecutedByTheUser) {
		float absoluteGlobal = 0;
		for(int i = 0; i<theBPMNexecutedByTheUser.size(); i++) {
			absoluteGlobal += theBPMNexecutedByTheUser.get(i).getAbsoluteBpScore();
		}
		return absoluteGlobal;
	}

	
}
