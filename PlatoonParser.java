package adept;

import java.util.ArrayList;
import java.util.List;

class PlatoonParser {
    public static List<Platoon> parse(String input) {
        List<Platoon> platoons = new ArrayList<>();
        String[] parts = input.split(";");
        for (String part : parts) {
            String[] details = part.split("#");
            platoons.add(new Platoon(details[0], Integer.parseInt(details[1])));
        }
        return platoons;
    }
}