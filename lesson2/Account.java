public class Account {

    private MoneySaver owner;
    private long amount;
	
	public Account(long amount, MoneySaver owner){
		this.amount = amount;
		this.owner = owner;	
	}
	
	public void deposit(long depositAmount){
		amount += depositAmount;
	}
	
	public long getAmount(){
		return amount;
	}
	
	public void setAmount(long amount){
		this.amount = amount;
	}
	
    public String toString() {
        return "Account owner: " + owner.getName() + ", Account Balance: $" + amount;
    }

    public static Account largerAccount(Account acc1, Account acc2) {
		return acc1;
    }
    
    public static void main(String[] args){
		MoneySaver jim = new MoneySaver("Jim", 100);
		Account a = new Account(100, jim);
		System.out.println(a);
		a.setAmount(20);
		a.deposit(10);
		System.out.println("New amount: " + a.getAmount());
	}

}
