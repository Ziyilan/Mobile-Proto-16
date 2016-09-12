public class MoneySaver {

    private long myMoney;
    private Account myAccount;
    private String name;


    public MoneySaver(String name, long money) {
        this.name = name;
        myMoney = money;
    }

    public String getName() {
        return name;
    }

    public Account getMyAccount() {
        return myAccount;
    }

    public String toString() {
        return name + ", " + "My balance is: "+ myMoney;
    }

    public void deposit(int amount) {
		myAccount.deposit(amount);
		myMoney = myMoney - amount;
    }

    public void withdraw(int amount) {
		CheckingAccount mychecking = (CheckingAccount) myAccount;
		mychecking.withdraw(amount);
		myMoney += amount;
    }

    public void signUpForChecking(int amount) {
		myAccount = new CheckingAccount(amount, this);
		myMoney = myMoney - amount;
    }

    public static void main(String[] args) {
		MoneySaver jim = new MoneySaver("Jim", 100);
		jim.signUpForChecking(30);
		CheckingAccount acc = (CheckingAccount) jim.getMyAccount();
		System.out.println(jim);
		System.out.println(acc);
		jim.deposit(40);
		System.out.println(jim);
		System.out.println(acc);
		jim.withdraw(60);
		System.out.println(jim);
		System.out.println(acc);
    }
}
