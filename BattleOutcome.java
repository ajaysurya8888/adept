package adept;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BattleOutcome {
    private static final Map<String, List<String>> advantages = new HashMap<>();

    static {
        advantages.put("Militia", Arrays.asList("Spearmen", "LightCavalry"));
        advantages.put("Spearmen", Arrays.asList("LightCavalry", "HeavyCavalry"));
        advantages.put("LightCavalry", Arrays.asList("FootArcher", "CavalryArcher"));
        advantages.put("HeavyCavalry", Arrays.asList("Militia", "FootArcher", "LightCavalry"));
        advantages.put("CavalryArcher", Arrays.asList("Spearmen", "HeavyCavalry"));
        advantages.put("FootArcher", Arrays.asList("Militia", "CavalryArcher"));
    }

    // Function to determine the outcome of a single battle
    public static int determineOutcome(Platoon own, Platoon opponent) {
        if (advantages.get(own.type).contains(opponent.type)) {
            return (own.soldiers >= 2 * opponent.soldiers) ? 1 : (own.soldiers * 2 == opponent.soldiers ? 0 : -1);
        } else if (advantages.get(opponent.type).contains(own.type)) {
            return (opponent.soldiers >= 2 * own.soldiers) ? -1 : (opponent.soldiers * 2 == own.soldiers ? 0 : 1);
        } else {
            return Integer.compare(own.soldiers, opponent.soldiers);
        }
    }
}