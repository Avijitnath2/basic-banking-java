import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int acts = 0;
        BankAccount myAccount;
        System.out.println("\nWelcome to the ABC Bank!\n");
        int choice;
        do{
            System.out.println("\nPlease select below options:");
            System.out.println("1.Create a new account");
            System.out.println("2.Sign in to an existing account");
            System.out.println("3.Exit\n");

            choice = getChoice(sc);
            boolean isGoBack;
            switch (choice){
                case 1:
                    isGoBack = createAccount(sc, acts);
                    if(isGoBack){
                        continue;
                    }
                    acts++;
                    break;
                case 2:
                    myAccount = loginAccount(sc);
                    if(myAccount == null){
                        continue;
                    }
                    mainMenu(myAccount, sc);

                    break;
                case 3:
                    System.out.println("Exiting Bank!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }while (choice != 3);
        sc.close();
    }

    private static boolean createAccount(Scanner sc,int acts){
        int accountNumber;
        String customerName;
        double balance;
        boolean checkAccounts;
        if(acts == BankAccount.accounts.length){
            System.out.println("Maximum account limit reached!");
            return true;
        }
        System.out.println("Please enter the necessary details to create an account: ");
        do{
            checkAccounts = false;
            accountNumber = readAcc(sc);
            if (accountNumber == 0){
                return true;
            }
            if(acts == 0){
                break;
            }
            for(BankAccount existAccounts: BankAccount.accounts){
                if(existAccounts == null){
                    break;
                }
                if(existAccounts.getAccountNumber() == accountNumber){
                    System.out.println("Account already exists!");
                    checkAccounts = true;
                    break;
                }
            }
        }while (checkAccounts);
        System.out.print("Customer Name: ");
        customerName = sc.nextLine();
        System.out.println("Starting Balance: ");
        balance = readAmount(sc);
        BankAccount myAccount = new BankAccount(accountNumber,customerName,balance);
        BankAccount.accounts[acts] = myAccount;
        return false;
    }

    private static BankAccount loginAccount(Scanner sc){
        int accountNumber;
        boolean foundAccount = false;
        BankAccount myAccount = null;

        System.out.println("Please enter your correct details to login:");

        accountNumber = readAcc(sc);
        if(accountNumber == 0){
            return null;
        }
        for(BankAccount existAccounts: BankAccount.accounts){
            if(existAccounts != null && existAccounts.getAccountNumber() == accountNumber){
                foundAccount = true;
                myAccount = existAccounts;
                break;
            }
        }
        if(!foundAccount){
            System.out.println("No account exists with these details.\n");
            return null;
        }
        return myAccount;
    }
    private static void mainMenu(BankAccount myAccount,Scanner sc){
        System.out.println("Welcome to your account " + myAccount.getCustomerName());
        int choice;
        double amount;
        do{
            System.out.println();
            System.out.println("Main Menu: -  ");
            System.out.println("1. Deposit,");
            System.out.println("2. Withdraw,");
            System.out.println("3. Check your Balance,");
            System.out.println("4. Logout.\n");

            choice = getChoice(sc);
            boolean checkTransactions;
            switch (choice){
                case 1:
                    checkTransactions = false;
                    while (!checkTransactions){
                        System.out.println("Deposit: ");
                        amount = readAmount(sc);
                        checkTransactions = myAccount.deposit(amount);
                    }
                    break;
                case 2:
                    checkTransactions = false;
                    while (!checkTransactions){
                        System.out.println("Withdraw: ");
                        amount = readAmount(sc);
                        checkTransactions = myAccount.withdraw(amount);
                    }
                    break;
                case 3:
                    System.out.println("Current Balance: " + myAccount.getBalance());
                    break;
                case 4:
                    System.out.println("Logged out Successfully!\n");
                    break;
                default:
                    System.out.println("Invalid Choice!\n");
            }
        }while (choice != 4);
    }
    private static double readAmount(Scanner sc){
        double amt;
        while (true){
            try {
                System.out.print("Enter your amount: ");
                amt = sc.nextDouble();sc.nextLine();
                if(amt <= 0){
                    System.out.println("Amount must be positive!");
                    continue;
                }
                return amt;
            }catch (InputMismatchException e){
                System.out.println("Invalid Input! Enter valid details.");
                sc.nextLine();
            }
        }
    }
    private static int readAcc(Scanner sc){
        int acc;
        while(true){
            try {
                System.out.print("Account Number (Press '0' to go back): ");
                acc = sc.nextInt();sc.nextLine();
                if(acc < 0){
                    System.out.println("Account Number must be positive!");
                    continue;
                }
                return acc;
            }catch (InputMismatchException e){
                System.out.println("Invalid Input! Enter valid details.");
                sc.nextLine();
            }
        }
    }

    private static int getChoice(Scanner sc) {
        int choice;
        while(true){
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();sc.nextLine();
                return choice;
            }catch (InputMismatchException e){
                System.out.println("Invalid Input! Enter valid details.");
                sc.nextLine();
            }
        }
    }
}
