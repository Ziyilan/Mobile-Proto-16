public class CheckingAccount extends Account{

	public CheckingAccount(long amount, MoneySaver owner){
		super(amount, owner);
	}
	
	public CheckingAccount(Account oldAccount){
		super(oldAccount.getAmount(), oldAccount.getOwner());
	}
	
	public CheckingAccount(long amount){
		super(amount, null);
	}
	
	public void withdraw(int withdrawAmount){
		amount = amount - withdrawAmount;
	}
	
	public String toString(){
		return "Checking Account Balance: $" + amount;
	}
	
}
