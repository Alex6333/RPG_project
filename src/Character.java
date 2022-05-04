public abstract class Character implements Fighter {

    private String name;
    private int xp, health, power, agility, gold, level;

    public Character(String name, int xp, int gold, int health, int power, int agility, int level) {
        this.name = name;
        this.xp = xp;
        this.gold = gold;
        this.health = health;
        this.power = power;
        this.agility = agility;
        this.level = level;
    }

    @Override
    public int attack() {

        if (agility > getRandom()) {
            return power * 2;
        } else if (agility * 3 > getRandom()) {
            return power;
        } else return 0;
    }

    private int getRandom() {
        return (int) (Math.random() * 100);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void levelUp(){}

    @Override
    public String toString() {
        return String.format("%s здоровье:%d", name, health);
    }
}
