package Week6;

public class PopMachine {

    private int numberOfPop;
    private double amountOfMoney;
    private double priceOfPop;
    private double currentBalance = 0.0;

    public PopMachine(int numberOfPop, double amountOfMoney, double priceOfPop) {
        this.numberOfPop = numberOfPop;
        this.amountOfMoney = amountOfMoney;
        this.priceOfPop = priceOfPop;
    }

    public PopMachine(int numberOfPop) {
        this(numberOfPop, 90.0, 1.75);
    }

    public void coinSlot(double money) {
        currentBalance += money;
    }

    public void popButton() {
        if (numberOfPop >= 1 && currentBalance >= priceOfPop) {
            numberOfPop--;
            currentBalance -= priceOfPop;
            amountOfMoney -= priceOfPop;
        } else {
            System.out.println("Sold out or not enough money!");
        }
    }

    public double coinReturn() {
        if (currentBalance > priceOfPop) {
            currentBalance -= priceOfPop;
            return priceOfPop;
        }
        System.out.println(amountOfMoney);

        return 0.0;
    }
}