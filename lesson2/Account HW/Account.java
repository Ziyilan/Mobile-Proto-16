public class Account {

    protected MoneySaver owner;
    protected long amount;
	
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
	
	public MoneySaver getOwner(){
		return owner;
	}
	
    public String toString() {
        return "Account owner: " + owner.getName() + ", Account Balance: $" + amount;
    }

    public static Account largerAccount(Account acc1, Account acc2) {
		return acc1.getAmount() > acc2.getAmount() ? acc1:acc2;
    }
    
    public static void main(String[] args){
		MoneySaver jim = new MoneySaver("Jim", 100);
		MoneySaver bob = new MoneySaver("Bob", 200);
		Account small = new Account(20, jim);
		Account big = new Account(30, bob);
		System.out.println(Account.largerAccount(small, big));
	}

}
