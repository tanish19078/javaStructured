public class Skill {
    private int skillId;
    String name;
    String category;
    public Skill(int skillId, String name, String category) {
        this.skillId = skillId;
        this.name = name;
        this.category = category;
    }

String getName() {
        return name;
    }

    void setdetail(String name, String category) {
        this.name = name;
        this.category = category;
    }

}