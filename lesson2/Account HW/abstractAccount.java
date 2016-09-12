public abstract class abstractAccount {

    protected MoneySaver owner;
    protected long amount;
	
	public abstractAccount(long amount, MoneySaver owner){
		this.amount = amount;
		this.owner = owner;	
	}
	
	public void deposit(long depositAmount){
	}
	
	public abstract long getAmount();
	
	public abstract void setAmount(long amount);
	
	public abstract MoneySaver getOwner();
	
    public abstract String toString();
    
    public static void main(String[] args){
		abstractAccount aa = new abstractAccount(10, new MoneySaver("Jason", 3));
	}

}
