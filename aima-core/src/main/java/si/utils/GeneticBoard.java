package si.utils;

public class GeneticBoard {
	private int id;
	private int board_size;
	private int board_layouts;

	private int population_size;
	private int iterations;
	private double mutationProbability;
	private double crossoverProbability;

	private double fitness;

	private boolean isGoal;
	private int iterationsConsumed;
	private double time;

	public GeneticBoard() {
	}


	public GeneticBoard(int id, int board_size, int board_layouts, int population_size, int iterations,
			double mutationProbability, double crossoverProbability, double fitness, boolean isGoal,
			int iterationsConsumed, double time) {
		super();
		this.id = id;
		this.board_size = board_size;
		this.board_layouts = board_layouts;
		this.population_size = population_size;
		this.iterations = iterations;
		this.mutationProbability = mutationProbability;
		this.crossoverProbability = crossoverProbability;
		this.fitness = fitness;
		this.isGoal = isGoal;
		this.iterationsConsumed = iterationsConsumed;
		this.time = time;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getBoard_size() {
		return board_size;
	}


	public void setBoard_size(int board_size) {
		this.board_size = board_size;
	}


	public int getBoard_layouts() {
		return board_layouts;
	}


	public void setBoard_layouts(int board_layouts) {
		this.board_layouts = board_layouts;
	}


	public int getPopulation_size() {
		return population_size;
	}


	public void setPopulation_size(int population_size) {
		this.population_size = population_size;
	}


	public int getIterations() {
		return iterations;
	}


	public void setIterations(int iterations) {
		this.iterations = iterations;
	}


	public double getMutationProbability() {
		return mutationProbability;
	}


	public void setMutationProbability(double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}


	public double getCrossoverProbability() {
		return crossoverProbability;
	}


	public void setCrossoverProbability(double crossoverProbability) {
		this.crossoverProbability = crossoverProbability;
	}


	public double getFitness() {
		return fitness;
	}


	public void setFitness(double fitness) {
		this.fitness = fitness;
	}


	public boolean isGoal() {
		return isGoal;
	}


	public void setGoal(boolean isGoal) {
		this.isGoal = isGoal;
	}


	public int getIterationsConsumed() {
		return iterationsConsumed;
	}


	public void setIterationsConsumed(int iterationsConsumed) {
		this.iterationsConsumed = iterationsConsumed;
	}


	public double getTime() {
		return time;
	}


	public void setTime(double time) {
		this.time = time;
	}
	
	

}
