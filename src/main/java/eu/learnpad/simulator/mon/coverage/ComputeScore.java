package eu.learnpad.simulator.mon.coverage;

import java.util.Vector;

public class ComputeScore {


	public static float absoluteSession(Activity[] activities) {

		float absoluteSession = 0;
		for (int i=0; i< activities.length; i++) {
			absoluteSession += activities[i].getWeight();			
		}
		return absoluteSession;
	}

	public static float learnerBP(int learnerID, String idBPMN) {
		// TODO Auto-generated method stub
		return 0f;
	}
	
	public static float learnerRelativeBP(Vector<Path> executedByUser) {
		float relativeBP = 0;
		for (int i = 0; i<executedByUser.size(); i++) {
			relativeBP += executedByUser.get(i).getAbsoluteSessionScore();
		}
		return relativeBP;
	}
	
	public static float absoluteBP(Vector<Path> thePathOfTheBPMN) {
		float absoluteBP = 0;
		for(int i = 0; i<thePathOfTheBPMN.size(); i++) {
			absoluteBP += thePathOfTheBPMN.get(i).getAbsoluteSessionScore();
		}
		return absoluteBP;
	}
	
	public static float learnerGlobal(Vector<Float> theScoresOfTheBPMNExecutedByUser) {
		float global = 0;
		for (int i = 0; i<theScoresOfTheBPMNExecutedByUser.size(); i++){
			global += theScoresOfTheBPMNExecutedByUser.get(i);
		}
		return global;
	}
	
	public static float relativeGlobal(Vector<Float> relativeBPScoreExecutedByUser) {
		float relativeGlobal = 0;
		for (int i = 0; i<relativeBPScoreExecutedByUser.size(); i++) {
			relativeGlobal += relativeBPScoreExecutedByUser.get(i);
		}
		return relativeGlobal;
	}
	
	public static float learnerAbsoluteGlobal(Vector<Bpmn> theBPMNexecutedByTheUser) {
		float absoluteGlobal = 0;
		for(int i = 0; i<theBPMNexecutedByTheUser.size(); i++) {
			absoluteGlobal += theBPMNexecutedByTheUser.get(i).getAbsoluteBpScore();
		}
		return absoluteGlobal;
	}

	public static float learnerRelativeGlobal(Vector<Float> learnerRelativeBPScores) {
		float relativeGlobal = 0;
		for(int i = 0; i<learnerRelativeBPScores.size(); i++) {
			relativeGlobal += learnerRelativeBPScores.get(i);
		}
		return relativeGlobal;
	}	
}
