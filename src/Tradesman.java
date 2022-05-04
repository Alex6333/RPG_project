public class Tradesman implements Seller {

    @Override
    public String sell(Products products) {
        String unit = "";
        if (products == Products.POTION) {
            unit = "potion";
        }
        return unit;
    }

    public enum Products {
        POTION((int)(Math.random()*25),(int)(Math.random()*35));

        private final int cost;
        private final int healing;

        Products(int cost, int healing) {
            this.cost = cost;
            this.healing = healing;
        }

        public int getCost() {
            return cost;
        }

        public int getHealing() {
            return healing;
        }
    }
}
