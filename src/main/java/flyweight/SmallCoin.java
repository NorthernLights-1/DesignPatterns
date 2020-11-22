package flyweight;

public class SmallCoin implements Coin {
    @Override
    public void pay() {
        System.out.println("You spent a small coin.");
    }
}
