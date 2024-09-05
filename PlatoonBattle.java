package adept;

import java.util.*;

class PlatoonBattle {

    // Define class advantages in a map
    private static final Map<String, List<String>> advantages = new HashMap<>();
    static {
        advantages.put("Militia", Arrays.asList("Spearmen", "LightCavalry"));
        advantages.put("Spearmen", Arrays.asList("LightCavalry", "HeavyCavalry"));
        advantages.put("LightCavalry", Arrays.asList("FootArcher", "CavalryArcher"));
        advantages.put("HeavyCavalry", Arrays.asList("Militia", "FootArcher", "LightCavalry"));
        advantages.put("CavalryArcher", Arrays.asList("Spearmen", "HeavyCavalry"));
        advantages.put("FootArcher", Arrays.asList("Militia", "CavalryArcher"));
    }

    // adept.Platoon class to store class type and number of soldiers
    static class Platoon {
        String type;
        int soldiers;

        Platoon(String type, int soldiers) {
            this.type = type;
            this.soldiers = soldiers;
        }

        @Override
        public String toString() {
            return type + "#" + soldiers;
        }
    }

    // Function to parse the input string into a list of adept.Platoon objects
    private static List<Platoon> parsePlatoons(String input) {
        List<Platoon> platoons = new ArrayList<>();
        String[] parts = input.split(";");
        for (String part : parts) {
            String[] details = part.split("#");
            platoons.add(new Platoon(details[0], Integer.parseInt(details[1])));
        }
        return platoons;
    }

    // Function to determine the outcome of a single battle
    private static int battleOutcome(Platoon own, Platoon opponent) {
        if (advantages.get(own.type).contains(opponent.type)) {
            return (own.soldiers >= 2 * opponent.soldiers) ? 1 : (own.soldiers * 2 == opponent.soldiers ? 0 : -1);
        } else if (advantages.get(opponent.type).contains(own.type)) {
            return (opponent.soldiers >= 2 * own.soldiers) ? -1 : (opponent.soldiers * 2 == own.soldiers ? 0 : 1);
        } else {
            return Integer.compare(own.soldiers, opponent.soldiers);
        }
    }

    // Function to check if the current permutation leads to at least 3 wins
    private static boolean isWinningPermutation(List<Platoon> own, List<Platoon> opponent) {
        int wins = 0;
        for (int i = 0; i < own.size(); i++) {
            int outcome = battleOutcome(own.get(i), opponent.get(i));
            if (outcome == 1) {
                wins++;
            }
            if (wins >= 3) {
                return true;
            }
        }
        return wins >= 3;
    }

    // Function to generate all permutations of own platoons and check for a winning permutation
    private static void findWinningArrangement(List<Platoon> own, List<Platoon> opponent) {
        List<List<Platoon>> permutations = new ArrayList<>();
        permute(own, 0, permutations);

        for (List<Platoon> perm : permutations) {
            if (isWinningPermutation(perm, opponent)) {
                for (Platoon p : perm) {
                    System.out.print(p + ";");
                }
                return;
            }
        }
        System.out.println("There is no chance of winning");
    }

    // Helper function to generate permutations of the own platoons
    private static void permute(List<Platoon> list, int start, List<List<Platoon>> result) {
        if (start == list.size() - 1) {
            result.add(new ArrayList<>(list));
        }
        for (int i = start; i < list.size(); i++) {
            Collections.swap(list, start, i);
            permute(list, start + 1, result);
            Collections.swap(list, start, i);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ownPlatoonsInput = sc.nextLine();
        String opponentPlatoonsInput = sc.nextLine();

        List<Platoon> ownPlatoons = parsePlatoons(ownPlatoonsInput);
        List<Platoon> opponentPlatoons = parsePlatoons(opponentPlatoonsInput);

        findWinningArrangement(ownPlatoons, opponentPlatoons);
    }
}
