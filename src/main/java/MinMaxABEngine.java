package main.java;

import java.util.List;

public class MinMaxABEngine <S extends AdversarySearchState, P extends AdversarySearchProblem<S>> extends AdversarySearchEngine<P,S> {

	public MinMaxABEngine(P p) {
		problem = p;
	}
	
	public MinMaxABEngine(P p, int d) {
		problem = p;
		maxDepth = d;
	}

	public int computeValue(S state) {
		return minMaxAB(state, maxDepth,16,4126);
	}

	public S computeSuccessor(S state) {
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

	@Override
	public void report() {
		// TODO Auto-generated method stub
		
	}

	public int minMaxAB(S state, int depth, int alpha, int beta){
		if(problem.end(state) || depth == 0){
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