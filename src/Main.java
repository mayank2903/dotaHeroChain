import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

  // TODO: convert these to camelCase so its easier to read final output.
  private static final String[] DOTA_HEROES = {"ABADDON", "ALCHEMIST", "ANCIENT APPARITION",
      "ANTI-MAGE", "ARC WARDEN", "AXE", "BANE", "BATRIDER", "BEASTMASTER", "BLOODSEEKER",
      "BOUNTY HUNTER", "BREWMASTER", "BRISTLEBACK", "BROODMOTHER", "CENTAUR WARRUNNER",
      "CHAOS KNIGHT", "CHEN", "CLINKZ", "CLOCKWERK", "CRYSTAL MAIDEN", "DARK SEER", "DARK WILLOW",
      "DAWNBREAKER", "DAZZLE", "DEATH PROPHET", "DISRUPTOR", "DOOM", "DRAGON KNIGHT", "DROW RANGER",
      "EARTH SPIRIT", "EARTHSHAKER", "ELDER TITAN", "EMBER SPIRIT", "ENCHANTRESS", "ENIGMA",
      "FACELESS VOID", "GRIMSTROKE", "GRYOCOPTER", "HOODWINK", "HUSKAR", "INVOKER", "IO", "JAKIRO",
      "JUGGERNAUT", "KEEPER OF THE LIGHT", "KUNKKA", "LEGION COMMANDER", "LESHRAC", "LICH",
      "LIFESTEALER", "LINA", "LION", "LONE DRUID", "LUNA", "LYCAN", "MAGNUS", "MARCI", "MARS",
      "MEDUSA", "MEEPO", "MIRANA", "MONKEY KING", "MORPHLING", "MUERTA", "NAGA SIREN",
      "NATURE'S PROPHET", "NECROPHOS", "NIGHT STALKER", "NYX ASSASSIN", "OGRE MAGI", "OMNIKNIGHT",
      "ORACLE", "OUTWORLD DESTROYER", "PANGOLIER", "PHANTOM ASSASSIN", "PHANTOM LANCER", "PHOENIX",
      "PRIMAL BEAST", "PUCK", "PUDGE", "PUGNA", "QUEEN OF PAIN", "RAZOR", "RIKI", "RUBICK",
      "SAND KING", "SHADOW DEMON", "SHADOW FIEND", "SHADOW SHAMAN", "SILENCER", "SKYWRATH MAGE",
      "SLARDAR", "SLARK", "SNAPFIRE", "SNIPER", "SPECTRE", "SPIRIT BREAKER", "STORM SPIRIT", "SVEN",
      "TECHIES", "TEMPLAR ASSASSIN", "TERRORBLADE", "TIDEHUNTER", "TIMBERSAW", "TINKER", "TINY",
      "TREANT PROTECTOR", "TROLL WARLORD", "TUSK", "UNDERLORD", "UNDYING", "URSA",
      "VENGEFUL SPIRIT", "VENOMANCER", "VIPER", "VISAGE", "VOID SPIRIT", "WARLOCK", "WEAVER",
      "WINDRANGER", "WINTER WYVERN", "WITCH DOCTOR", "WRAITH KING", "ZEUS"};

  public static void main(String[] args) {
    // 1. Pre-computation.
    Map<Character, ArrayList<Integer>> heroesIndexedByFirstLetter = new HashMap<>();
    Map<Character, ArrayList<Integer>> heroesIndexedByLastLetter = new HashMap<>();
    for (int i = 0; i < DOTA_HEROES.length; i++) {
      String hero = DOTA_HEROES[i];
      char startingChar = hero.charAt(0);
      char endingChar = hero.charAt(hero.length() - 1);

      heroesIndexedByFirstLetter.computeIfAbsent(startingChar, v -> new ArrayList<>()).add(i);
      heroesIndexedByLastLetter.computeIfAbsent(endingChar, v -> new ArrayList<>()).add(i);
    }

    Node[] graphs = new Node[DOTA_HEROES.length];

    for (int i = 0; i < DOTA_HEROES.length; i++) {
      System.out.println("Forming graph starting:" + DOTA_HEROES[i]);
      graphs[i] = traverse(i, new boolean[DOTA_HEROES.length], new Node[DOTA_HEROES.length],
          heroesIndexedByFirstLetter);
    }

    int maxDepth = 0;
    ArrayList<String> longestSequence = new ArrayList<>();
    for (int i = 0; i < DOTA_HEROES.length; i++) {
      ArrayList<String> currentBestSequence = findLongestSequence(graphs[i]);
      int currentDepth = currentBestSequence.size();
      if (currentDepth > maxDepth) {
        maxDepth = currentDepth;
        longestSequence = currentBestSequence;
      }
    }

    System.out.println(longestSequence.size());
    System.out.println(longestSequence);
  }

  private static Node traverse(int index, boolean[] visited, Node[] subgraphs,
      Map<Character, ArrayList<Integer>> heroesIndexedByFirstChar) {
    if (visited[index]) {
      return subgraphs[index];
    }

    visited[index] = true;
    String currentHero = DOTA_HEROES[index];
    char lastChar = currentHero.charAt(currentHero.length() - 1);

    ArrayList<Integer> heroesWithFirstCharAsCurrentLastChar = heroesIndexedByFirstChar.getOrDefault(
        lastChar, new ArrayList<>());
    ArrayList<Node> downstreamGraphs = new ArrayList<>();
    for (int downstreamHeroIndex : heroesWithFirstCharAsCurrentLastChar) {
      Node node = traverse(downstreamHeroIndex, visited, subgraphs, heroesIndexedByFirstChar);
      downstreamGraphs.add(node);
    }

    return subgraphs[index] = new Node(index, downstreamGraphs.isEmpty() ? null : downstreamGraphs);
  }

  private static ArrayList<String> findLongestSequence(Node graph) {
    ArrayList<String> ans = new ArrayList<>();
    if (graph.getDownstreamGraphs() == null) {
      ans.add(DOTA_HEROES[graph.getHeroIndex()]);
      return ans;
    }

    int maxLen = 0;
    ArrayList<String> tempBestSequence = new ArrayList<>();
    for (Node subGraph : graph.getDownstreamGraphs()) {
      if (subGraph == null) {
        continue;
      }

      ArrayList<String> bestSubGraphSequence = findLongestSequence(subGraph);
      if (bestSubGraphSequence.size() > maxLen) {
        maxLen = bestSubGraphSequence.size();
        tempBestSequence = bestSubGraphSequence;
      }
    }

    ans.add(DOTA_HEROES[graph.getHeroIndex()]);
    ans.addAll(tempBestSequence);
    return ans;
  }
}
