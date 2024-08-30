import java.util.*;
public class Adept {

    public static void main(String args[]) {
        Map<String,List<String>> advantageMap = new HashMap<String,List<String>>();
        advantageMap.put("Militia", Arrays.asList("Spearmen", "LightCavalry"));
        advantageMap.put("Spearmen", Arrays.asList("LightCavalry", "HeavyCavalry"));
        advantageMap.put("LightCavalry", Arrays.asList("FootArcher", "CavalryArcher"));
        advantageMap.put("HeavyCavalry", Arrays.asList("Militia", "FootArcher", "LightCavalry"));
        advantageMap.put("CavalryArcher", Arrays.asList("Spearmen", "HeavyCavalry"));
        advantageMap.put("FootArcher", Arrays.asList("Militia", "CavalryArcher"));

        Scanner sc = new Scanner(System.in);
        System.out.println("Print your platoons");
        String ownPlatoons = sc.nextLine();
        System.out.println("print opponent platoons");
        String opponentPlatoons = sc.nextLine();

        String[] ownPlatoonsArray = ownPlatoons.split(";");
        String[] opponentPlatoonsArray = opponentPlatoons.split(";");
        LinkedHashMap<String,Integer[]> hashMap= new LinkedHashMap<>();

        for(String s : ownPlatoonsArray){
            String[] temp = s.split("#");
            hashMap.put(temp[0],new Integer[] { Integer.parseInt(temp[1]), -1, 0 });
        }
        LinkedHashMap<String,String> answer = new LinkedHashMap<String,String>();
        for(String s : opponentPlatoonsArray){
            String[] temp = s.split("#");
            if(hashMap.get(temp[0]) == null) {

                hashMap.put(temp[0],new Integer[]{-1,0,0});
                answer.put(temp[0],"");
            }
            Integer[] arr = hashMap.get(temp[0]);
            arr[1] = Integer.parseInt(temp[1]);
            answer.put(temp[0],"");
        }
        int win = 0;
        for (Map.Entry<String, Integer[]> m : hashMap.entrySet()) {
            if (m.getValue()[2] == 1) {
                continue;
            }
            if(canWeWinStraight(hashMap,answer,m.getKey(),m.getKey())) {
                win++;
            }
            else {

             int winCount =   canWeWinDouble(hashMap,answer,m.getKey(),advantageMap.get(m.getKey()));
             win+=winCount;
            }
        }
    if(win >= 3) {
        String answers = "";

        for (Map.Entry<String, String> ma : answer.entrySet()) {
            answers += ma.getValue() + "#" + hashMap.get(ma.getValue())[0] + ";";
        }
        System.out.println(answers);
    }
    else {
        System.out.println("There is no chance of winning");
    }

    }

    static int canWeWinDouble(LinkedHashMap<String,Integer[]> hashMap, LinkedHashMap<String, String> answer,String ownClass, List<String> advantageList) {
        int winCount = 0;
        for(String advantageSingle : advantageList) {
            if (hashMap.get(advantageSingle)[2] == 1) {
                continue;
            }
            else if(canWeWinStraight(hashMap,answer,advantageSingle,advantageSingle)) {
                winCount++;
            }
            else {
                if (2 * hashMap.get(ownClass)[0] > hashMap.get(advantageSingle)[1]) {
                    hashMap.get(advantageSingle)[2] = 1;
                    answer.put(advantageSingle, ownClass);
                    answer.put(ownClass,advantageSingle);
                    winCount++;
                    break;
                }
                else {
                    if(hashMap.get(ownClass)[0] != -1)
                    answer.put(ownClass,ownClass);
                    else{
                        answer.put(ownClass,advantageSingle);
                    }

                }

            }
        }
        return winCount;
    }

    static boolean canWeWinStraight(LinkedHashMap<String, Integer[]> hashMap, LinkedHashMap<String, String> answer, String ownClass, String opponentClass) {
        if(hashMap.get(ownClass)[0] == -1 || hashMap.get(ownClass)[1] == -1) {
            return false;
        }
        else if (hashMap.get(ownClass)[0] > hashMap.get(opponentClass)[1]) {
            hashMap.get(opponentClass)[2] = 1;
            answer.put(ownClass, opponentClass);
            return true;
        }
        else {
            answer.put(ownClass,opponentClass);
        }

        return false;
    }
    }

