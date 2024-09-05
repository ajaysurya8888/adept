package adept;

import java.util.List;
import java.util.Scanner;

public class MedievalBattleSimulator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ownPlatoonsInput = sc.nextLine();
        String opponentPlatoonsInput = sc.nextLine();

        List<Platoon> ownPlatoons = PlatoonParser.parse(ownPlatoonsInput);
        List<Platoon> opponentPlatoons = PlatoonParser.parse(opponentPlatoonsInput);

        BattleSimulator simulator = new BattleSimulator();
        simulator.findWinningArrangement(ownPlatoons, opponentPlatoons);
    }
}