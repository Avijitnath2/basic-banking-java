import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int acts = -1;
        BankAccount myAccount;
        System.out.println("\nWelcome to the Basic Bank!\n");
        int choice = 0;
        do{
            System.out.println("\nPlease select below options:");
            System.out.println("1.Create a new account");
            System.out.println("2.Sign in to an existing account");
            System.out.println("3.Exit\n");

            System.out.print("Enter your choice: ");
            try {
                choice = getChoice(sc);
                sc.nextLine();
            }catch (InputMismatchException e){
                System.out.println("Invalid input! Please enter a valid choice.\n");
                sc.next();
                continue;
            }
            switch (choice){
                case 1:
                    while (true) {
                        try {
                            createAccount(sc, (++acts));
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid Input! Enter valid details.");
                            sc.next();
                        }
                    }
                    break;
                case 2:
                    while (true){
                        try {
                            myAccount = loginAccount(sc);
                            break;
                        }catch (InputMismatchException e){
                            System.out.println("Invalid Input! Enter valid details.");
                            sc.next();
                        }
                    }
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
    }

    private static void createAccount(Scanner sc,int acts){
        int accountNumber;
        String customerName;
        double balance;
        System.out.println("Please enter the necessary details to create an account: ");
        boolean checkAccounts;
        do{
            checkAccounts = false;
            System.out.print("Account Number: ");
            accountNumber = sc.nextInt();sc.nextLine();
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
        System.out.print("Starting Balance: ");
        balance = sc.nextDouble();sc.nextLine();
        BankAccount myAccount = new BankAccount(accountNumber,customerName,balance);
        System.out.println("Account is Created!");
        BankAccount.accounts[acts] = myAccount;
    }

    private static BankAccount loginAccount(Scanner sc){
        int accountNumber;
        boolean checkAccounts;
        BankAccount myAccount = null;
        do{
            checkAccounts = false;
            System.out.println("Please enter your correct details to login:");

            System.out.print("Account Number (Press '0' to go back): ");
            accountNumber = sc.nextInt();sc.nextLine();

            if(accountNumber == 0){
                return null;
            }

            for(BankAccount existAccounts: BankAccount.accounts){
                if(existAccounts == null){
                    System.out.println("No account exists with this details.\n");
                    break;
                }
                if(existAccounts.getAccountNumber() == accountNumber){
                    System.out.println("Logged in successfully!\n");
                    checkAccounts = true;
                    myAccount = existAccounts;
                    break;
                }
            }
        }while (!checkAccounts);
        return myAccount;
    }
    private static void mainMenu(BankAccount myAccount,Scanner sc){
        System.out.println("Welcome to your account " + myAccount.getCustomerName());
        int choice = 0;
        double amount;
        do{
            System.out.println();
            System.out.println("Main Menu: -  ");
            System.out.println("1. Deposit,");
            System.out.println("2. Withdraw,");
            System.out.println("3. Check your Balance,");
            System.out.println("4. Logout.\n");

            System.out.print("Enter your choice: ");
            try {
                choice = getChoice(sc);
                sc.nextLine();
            }catch (InputMismatchException e){
                System.out.println("Invalid input! Please enter a valid choice.\n");
                sc.next();
                continue;
            }

            switch (choice){
                case 1:

                    while (true){
                        try {
                            System.out.print("Enter the deposit amount: ");
                            amount = sc.nextDouble();sc.nextLine();
                            break;
                        }catch (InputMismatchException e){
                            System.out.println("Invalid Input! Enter valid details.");
                            sc.next();
                        }
                    }
                    myAccount.deposit(amount);
                    break;
                case 2:

                    while (true){
                        try {
                            System.out.print("Enter the amount to withdraw: ");
                            amount = sc.nextDouble();sc.nextLine();
                            break;
                        }catch (InputMismatchException e){
                            System.out.println("Invalid Input! Enter valid details.");
                            sc.next();
                        }
                    }
                    myAccount.withdraw(amount);
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

    private static int getChoice(Scanner sc) {
        return sc.nextInt();
    }
}
