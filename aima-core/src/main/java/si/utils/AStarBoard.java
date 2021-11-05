package si.utils;

public class AStarBoard {
	private int id;
	private int board_size;
	
	private String nodesExpandedReinsertedInFrontier;
	private String nodesExpanded;
	private String pathCost;
	private String timeTaken;
	
	private boolean oom;
	
	public AStarBoard(){
		this.oom = false;
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

	public String getNodesExpandedReinsertedInFrontier() {
		return nodesExpandedReinsertedInFrontier;
	}

	public void setNodesExpandedReinsertedInFrontier(String nodesReinsertedInFrontier) {
		this.nodesExpandedReinsertedInFrontier = nodesReinsertedInFrontier;
	}

	public String getNodesExpanded() {
		return nodesExpanded;
	}

	public void setNodesExpanded(String nodesExpanded) {
		this.nodesExpanded = nodesExpanded;
	}

	public String getPathCost() {
		return pathCost;
	}

	public void setPathCost(String pathCost) {
		this.pathCost = pathCost;
	}

	public String getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}

	public boolean isOom() {
		return oom;
	}

	public void setOom(boolean oom) {
		this.oom = oom;
	};
	
	
	
	

}
