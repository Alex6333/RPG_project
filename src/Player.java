public class Player extends Character {

    private int level = 1;

    public Player(String name, int xp, int gold, int health, int power, int agility, int level) {
        super(name, xp, gold, health, power, agility, level);
    }

    @Override
    public void levelUp() {
        setLevel(getLevel() + 1);
        setGold((int) (getGold() + 100*getLevel()));
        setHealth(getLevel() + 100 * getLevel());
        setPower(getPower() * getLevel());
        setAgility(getAgility() + 10);
        System.out.printf("Поздравляем! Вы показали себя и вышли на новый уровень %d." +
                        " Теперь у Вас %d здоровья, %d золота, сила вашего удара стала %d, а ловкость выросла до %d%n",
                getLevel(), getHealth(), getGold(), getPower(), getAgility());
    }
}
