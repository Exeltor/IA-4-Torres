package aima.gui.demo.search;

import java.util.List;
import java.util.Optional;

import aima.core.environment.fourtowers.FourTowersBoard;
import aima.core.environment.fourtowers.FourTowersFunctions;
import aima.core.environment.fourtowers.TowerAction;
import aima.core.environment.fourtowers.FourTowersBoard.Config;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.framework.qsearch.TreeSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.uninformed.DepthFirstSearch;

public class FourTowersDemo {

	private static final int boardSize = 8;

	public static void main(String[] args) {
		startFourTowersDemo();
	}

	private static void startFourTowersDemo() {
		solveFourTowersWithDepthFirstSearch();
		solveFourTowersWithAStarSearch();
	}

	private static void solveFourTowersWithDepthFirstSearch() {
		System.out.println("\n--- FourTowersDemo DFS ---");

		Problem<FourTowersBoard, TowerAction> problem = FourTowersFunctions.createIncrementalFormulationProblem(boardSize);
		SearchForActions<FourTowersBoard, TowerAction> search = new DepthFirstSearch<>(new TreeSearch<>());
		Optional<List<TowerAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));
		System.out.println(search.getMetrics());
	}


	private static void solveFourTowersWithAStarSearch() {
		System.out.println("\n--- FourTowersDemo A* (complete state formulation, graph search 3e) ---");

		Problem<FourTowersBoard, TowerAction> problem = FourTowersFunctions.createCompleteStateFormulationProblem
				(boardSize, Config.TOWERS_IN_FIRST_ROW);
		SearchForActions<FourTowersBoard, TowerAction> search = new AStarSearch<>
				(new GraphSearch<>(), FourTowersFunctions::getNumberOfAttackingPairs);
		Optional<List<TowerAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));
		System.out.println(search.getMetrics());
	}

	
	
	
}
