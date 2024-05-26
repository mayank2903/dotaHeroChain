import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class Main {

  // TODO: convert these to camelCase so its easier to read final output.
  private static final String[] DOTA_HEROES = {
      "ABADDON",
      "ALCHEMIST",
      "ANCIENT APPARITION",
      "ANTI-MAGE",
      "ARC WARDEN",
      "AXE",
      "BANE",
      "BATRIDER",
      "BEASTMASTER",
      "BLOODSEEKER",
      "BOUNTY HUNTER",
      "BREWMASTER",
      "BRISTLEBACK",
      "BROODMOTHER",
      "CENTAUR WARRUNNER",
      "CHAOS KNIGHT",
      "CHEN",
      "CLINKZ",
      "CLOCKWERK",
      "CRYSTAL MAIDEN",
      "DARK SEER",
      "DARK WILLOW",
      "DAWNBREAKER",
      "DAZZLE",
      "DEATH PROPHET",
      "DISRUPTOR",
      "DOOM",
      "DRAGON KNIGHT",
      "DROW RANGER",
      "EARTH SPIRIT",
      "EARTHSHAKER",
      "ELDER TITAN",
      "EMBER SPIRIT",
      "ENCHANTRESS",
      "ENIGMA",
      "FACELESS VOID",
      "GRIMSTROKE",
      "GRYOCOPTER",
      "HOODWINK",
      "HUSKAR",
      "INVOKER",
      "IO",
      "JAKIRO",
      "JUGGERNAUT",
      "KEEPER OF THE LIGHT",
      "KUNKKA",
      "LEGION COMMANDER",
      "LESHRAC",
      "LICH",
      "LIFESTEALER",
      "LINA",
      "LION",
      "LONE DRUID",
      "LUNA",
      "LYCAN",
      "MAGNUS",
      "MARCI",
      "MARS",
      "MEDUSA",
      "MEEPO",
      "MIRANA",
      "MONKEY KING",
      "MORPHLING",
      "MUERTA",
      "NAGA SIREN",
      "NATURE'S PROPHET",
      "NECROPHOS",
      "NIGHT STALKER",
      "NYX ASSASSIN",
      "OGRE MAGI",
      "OMNIKNIGHT",
      "ORACLE",
      "OUTWORLD DESTROYER",
      "PANGOLIER",
      "PHANTOM ASSASSIN",
      "PHANTOM LANCER",
      "PHOENIX",
      "PRIMAL BEAST",
      "PUCK",
      "PUDGE",
      "PUGNA",
      "QUEEN OF PAIN",
      "RAZOR",
      "RIKI",
      "RUBICK",
      "SAND KING",
      "SHADOW DEMON",
      "SHADOW FIEND",
      "SHADOW SHAMAN",
      "SILENCER",
      "SKYWRATH MAGE",
      "SLARDAR",
      "SLARK",
      "SNAPFIRE",
      "SNIPER",
      "SPECTRE",
      "SPIRIT BREAKER",
      "STORM SPIRIT",
      "SVEN",
      "TECHIES",
      "TEMPLAR ASSASSIN",
      "TERRORBLADE",
      "TIDEHUNTER",
      "TIMBERSAW",
      "TINKER",
      "TINY",
      "TREANT PROTECTOR",
      "TROLL WARLORD",
      "TUSK",
      "UNDERLORD",
      "UNDYING",
      "URSA",
      "VENGEFUL SPIRIT",
      "VENOMANCER",
      "VIPER",
      "VISAGE",
      "VOID SPIRIT",
      "WARLOCK",
      "WEAVER",
      "WINDRANGER",
      "WINTER WYVERN",
      "WITCH DOCTOR",
      "WRAITH KING",
      "ZEUS"
  };

  public static void main(String[] args) {
    // 1. Pre-computation.
    Map<Character, ArrayList<String>> heroesIndexedByFirstLetter = new HashMap<>();
    Map<Character, ArrayList<String>> heroesIndexedByLastLetter = new HashMap<>();
    Map<Character, Integer> startingCharCount = new HashMap<>();
    Map<Character, Integer> endingCharCount = new HashMap<>();
    for (String hero : DOTA_HEROES) {
      char startingChar = hero.charAt(0);
      char endingChar = hero.charAt(hero.length() - 1);

      heroesIndexedByFirstLetter.computeIfAbsent(startingChar, v -> new ArrayList<>()).add(hero);
      heroesIndexedByLastLetter.computeIfAbsent(endingChar, v -> new ArrayList<>()).add(hero);
      startingCharCount.put(startingChar, startingCharCount.getOrDefault(startingChar, 0) + 1);
      endingCharCount.put(endingChar, endingCharCount.getOrDefault(endingChar, 0) + 1);
    }

    // 2. Check if even possible, and call out why if not.
    for (Entry<Character, Integer> e : endingCharCount.entrySet()) {
      char c = e.getKey();
      if (startingCharCount.getOrDefault(c, 0) < e.getValue() - 1) {
        System.out.printf("Not possible to form chain. Freq(%s): (%s, %s)\n", c,
            startingCharCount.getOrDefault(c, 0), e.getValue());
        System.out.println(heroesIndexedByLastLetter.getOrDefault(c, new ArrayList<>()));
        System.out.println(heroesIndexedByFirstLetter.getOrDefault(c, new ArrayList<>()));
        break;
      }
    }
    System.out.println("Possible to form chain. Going further...");

    // 3. For each starting char, see if possible to concatenate all heroes.
    Optional<ArrayList<Integer>> optionalSequence = Optional.empty();
    String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (Character c : ALPHABET.toCharArray()) {
      optionalSequence = checkPossibility(c, heroesIndexedByFirstLetter);
      if (optionalSequence.isPresent()) {
        printConcatenation(optionalSequence.get());
        break;
      }
    }

    if (optionalSequence.isEmpty()) {
      System.out.println("No such concatenation found.");
    }
  }

  private static Optional<ArrayList<Integer>> checkPossibility(char ignoredC,
      Map<Character, ArrayList<String>> ignoredStartEndCharsOfHeroes) {
    return Optional.empty();
  }

  private static void printConcatenation(ArrayList<Integer> indices) {
    int len = indices.size();
    System.out.print(DOTA_HEROES[0]);
    for (int i = 1; i < len; i++) {
      System.out.print(DOTA_HEROES[i].substring(1));
    }
  }
}
