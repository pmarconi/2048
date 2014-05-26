package main.java;

import java.util.List;

public class MinMaxABEngine <S extends AdversarySearchState, P extends AdversarySearchProblem<S>> extends AdversarySearchEngine<P,S> {
	
	private int visitedStates;
	private int finalStates;
	private int countPruning;
	
	public MinMaxABEngine(P p) {
		visitedStates = 0;
		finalStates = 0;
		countPruning = 0;
		problem = p;
	}
	
	public MinMaxABEngine(P p, int d) {
		visitedStates = 0;
		finalStates = 0;
		countPruning = 0;
		problem = p;
		maxDepth = d;
	}

	public int computeValue(S state) {
		if(state == null) throw new IllegalArgumentException("State null");
		return minMaxAB(state, maxDepth,problem.minValue(),problem.maxValue());
	}

	public S computeSuccessor(S state) {
		if(state == null) throw new IllegalArgumentException("State null");
		List<S> successors = problem.getSuccessors(state);
		System.out.println("successors: "+successors.size());
		S result = null;
		int resValue = Integer.MIN_VALUE;
		for(S succ : successors){
			int value = computeValue(succ);
			System.out.println("value "+succ.ruleApplied()+": "+value);
			if ( value > resValue){
				resValue = value;
				result = succ;
			}
		}
		return result;
	}

	public void report() {
		System.out.println("Depth = "+ maxDepth);
		System.out.println("Visited states = "+ visitedStates);
		System.out.println("Final states analyzed = "+ finalStates);
		System.out.println("Count pruning = "+ countPruning );
	}

	public int minMaxAB(S state, int depth, int alpha, int beta){
		if(state == null) throw new IllegalArgumentException("State null");
		if(depth<0) throw new IllegalArgumentException("invalid depth");
		if(alpha<0) throw new IllegalArgumentException("invalid alpha");
		if(beta<0) throw new IllegalArgumentException("invalid beta");
		visitedStates++;
		if(problem.end(state) || depth == 0){
			finalStates++;
			return problem.value(state);
		}else{
			List<S> suc = problem.getSuccessors(state);
			for (S child : suc) {
				if (state.isMax()){
					alpha = max(alpha, minMaxAB(child, depth-1, alpha, beta));
				}else{
					beta = min(beta, minMaxAB(child, depth-1, alpha, beta));
				}
				if(alpha >= beta){
					countPruning++;
					break;
				}
			}
			if (state.isMax()){
				return alpha;
			}else{
				return beta;
			}
		}
	}
	
	private int min(int num1, int num2) {
		return (num1 < num2) ? num1 : num2;
	}

	private int max(int num1, int num2) {
		return (num1 >= num2) ? num1 : num2;
	}

}