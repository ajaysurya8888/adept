package adept;

class Platoon {
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