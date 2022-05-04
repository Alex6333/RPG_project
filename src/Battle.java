public class Battle {

    public void fight(Character player, Character monster, Realm.FightCallback fightCallback) {

        Runnable runnable = () -> {

            int turn = 1;
            boolean isFightEnded = false;
            System.out.println("Да начнется великая битва!");
            while (!isFightEnded) {

                System.out.println("====Ход: " + turn + "====");
                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(monster, player, fightCallback);
                } else {
                    isFightEnded = makeHit(player, monster, fightCallback);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    private Boolean makeHit(Character defender, Character attacker, Realm.FightCallback fightCallback) {

        int hit = attacker.attack();
        int defenderHealth = defender.getHealth() - hit;

        if (hit != 0) {
            System.out.printf("%s нанес удар в %d единиц!%n", attacker.getName(), hit);
            System.out.printf("У %s осталось %d единиц здоровья.%n", defender.getName(), defenderHealth);
        } else {
            System.out.printf("%s промахнулся!%n", attacker.getName());
        }
        if (defenderHealth <= 0 && defender instanceof Player) {
            System.out.println("Конец игры! Игрок пал в бою...");
            fightCallback.fightLost();
            return true;
        } else if (defenderHealth <= 0) {
            System.out.printf("Монстр побежден! Ваша награда %d золота и %d опыта%n", defender.getGold(),defender.getXp());
            attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.setGold(attacker.getGold() + defender.getGold());
            fightCallback.fightWin();
            return true;
        }else {
            defender.setHealth(defenderHealth);
            return false;
        }
    }
}
