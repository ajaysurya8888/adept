package adept;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Class responsible for simulating battles and finding the optimal arrangement
class BattleSimulator {

    public void findWinningArrangement(List<Platoon> own, List<Platoon> opponent) {
        List<List<Platoon>> permutations = new ArrayList<>();
        generatePermutations(own, 0, permutations);

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

    private boolean isWinningPermutation(List<Platoon> own, List<Platoon> opponent) {
        int wins = 0;
        for (int i = 0; i < own.size(); i++) {
            int outcome = BattleOutcome.determineOutcome(own.get(i), opponent.get(i));
            if (outcome == 1) {
                wins++;
            }
            if (wins >= 3) {
                return true;
            }
        }
        return wins >= 3;
    }

    private void generatePermutations(List<Platoon> list, int start, List<List<Platoon>> result) {
        if (start == list.size() - 1) {
            result.add(new ArrayList<>(list));
        }
        for (int i = start; i < list.size(); i++) {
            Collections.swap(list, start, i);
            generatePermutations(list, start + 1, result);
            Collections.swap(list, start, i);
        }
    }
}