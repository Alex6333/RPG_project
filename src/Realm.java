import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Realm {

    private static BufferedReader br;
    private static Character player = null;
    private static Battle battle = null;

    public static void main(String[] args) {

        br = new BufferedReader(new InputStreamReader(System.in));
        battle = new Battle();
        System.out.println("Введите имя вашего персонажа:");

        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {

        if (player == null) {
            player = new Player(string, 0, 0, 100, 20, 20, 1);
            System.out.printf("А вот и наш герой! Имя ему %s! Да прибудет с тобой сила, %s!%n", player.getName(), player.getName());
            printNavigation();
            command(br.readLine());
        }
        switch (string) {
            case "1" -> {
                buying(player, new Tradesman());
                printNavigation();
                command(br.readLine());
            }
            case "2" -> commitFight();
            case "3" -> System.exit(1);
            default -> {
                System.out.println("Некорректная команда.");
                command(br.readLine());
            }
        }
    }

    private static void variant(String string) throws IOException {

        switch (string) {
            case "да" -> command("2");
            case "нет" -> {
                printNavigation();
                command(br.readLine());
            }
            default -> {
                System.out.println("""
                        Некорректная команда.
                        да - продолжить поход
                        нет - вернуться в город""");
                variant(br.readLine());
            }
        }
    }

    private static void printNavigation() {
        System.out.printf("%s, куда вы желаете отправиться?%n", player.getName());
        System.out.println("1.К торговцу");
        System.out.println("2.В темный лес");
        System.out.println("3.На выход");
    }

    private static void commitFight() {
        battle.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.printf("""
                                ============
                                %s одержал победу! Теперь ваш опыт %d, запас вашего золота %d, %d единиц здоровья.
                                ============%n""",
                        player.getName(), player.getXp(), player.getGold(), player.getHealth());
                if (player.getXp() >= 500 * Math.pow(player.getLevel(), 2)) {
                    player.levelUp();
                }
                System.out.println("Желаете продолжить свой поход или вернуться в город?(да/нет)");

                try {
                    variant(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {
                System.exit(1);
            }
        });
    }

    private static Character createMonster() {
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0) return new Goblin("Гоблин", 100 * player.getLevel(), 20 * player.getLevel(),
                65 * player.getLevel(), 10 * player.getLevel(), 10 * player.getLevel(),  player.getLevel());
        else return new Skeleton("Скелет", 100 * player.getLevel(), 20 * player.getLevel(),
                65 * player.getLevel(), 10 * player.getLevel(), 10 * player.getLevel(),  player.getLevel());
    }

    interface FightCallback {
        void fightWin();

        void fightLost();
    }

    public static void buying(Character player, Tradesman potionTrader) {

        System.out.println("Добро пожаловать в нашу скромную лавку торговли зельями.");
        if (player.getGold() >= Tradesman.Products.POTION.getCost()) {
            potionTrader.sell(Tradesman.Products.POTION);
            player.setGold(player.getGold() - Tradesman.Products.POTION.getCost());
            player.setHealth(player.getHealth() + Tradesman.Products.POTION.getHealing());
            System.out.printf("Вот вы и подлечились. Теперь уровень вашего здоровья %d," +
                    " а запас золота стал равен %d.%n", player.getHealth(), player.getGold());
        } else {
            System.out.println("Вы безусловно герой, но, боюсь, ваших средств не хватает. Заходите снова чуть позже.");
        }
    }
}

