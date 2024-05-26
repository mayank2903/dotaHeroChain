import java.util.ArrayList;

public class Node {


  private int heroIndex;
  private ArrayList<Node> downstreamGraphs;

  public Node(int heroIndex, ArrayList<Node> downstreamGraphs) {
    this.heroIndex = heroIndex;
    this.downstreamGraphs = downstreamGraphs;
  }

  public int getHeroIndex() {
    return heroIndex;
  }

  public void setHeroIndex(int heroIndex) {
    this.heroIndex = heroIndex;
  }

  public ArrayList<Node> getDownstreamGraphs() {
    return downstreamGraphs;
  }

  public void setDownstreamGraphs(ArrayList<Node> downstreamGraphs) {
    this.downstreamGraphs = downstreamGraphs;
  }
}
