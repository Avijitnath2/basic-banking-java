public class BankAccount {
    private final int accountNumber;
    private final String customerName;
    private double balance;
    static BankAccount[] accounts = new BankAccount[10];

    BankAccount(int accountNumber, String customerName, double balance){
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance;
    }
    public int getAccountNumber(){
        return this.accountNumber;
    }
    public String getCustomerName(){
        return this.customerName;
    }
    public double getBalance(){
        return this.balance;
    }

    public void deposit(double amount){
        if(amount <= 0){
            System.out.println("Negative amount not allowed during deposit!");
        }
        else {
            this.balance += amount;
            System.out.println("Deposited Amount: " + amount);
        }
    }
    public void withdraw(double amount){
        if(amount <= 0){
            System.out.println("Invalid withdrawal amount");
        }
        else if(this.balance < amount){
            System.out.println("Insufficient Balance to withdraw!");
        }
        else{
            this.balance -= amount;
            System.out.println("Withdrawn Amount: " + amount);
        }
    }
}
