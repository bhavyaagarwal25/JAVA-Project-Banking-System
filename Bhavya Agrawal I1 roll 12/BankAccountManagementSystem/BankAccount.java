
import java.util.ArrayList;
import java.util.Scanner;

class InvalidAmountException extends Exception {

    @Override
    public String getMessage() {
        return "InvalidAmountException";
    }
}

class InputMismatchException extends Exception {

    @Override
    public String getMessage() {
        return "InputMismatchException";
    }
}

class AccountDoesNotExist extends Exception {

    @Override
    public String getMessage() {
        return "AccountDoesNotExist";
    }
}

class BankDetails {

    static int usersCount = 1;
    String name;
    int accNo;
    double balance = 0;

    public BankDetails(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.accNo = usersCount;
        usersCount++;
    }

    public int getID() {
        return this.accNo;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double amount) {
        this.balance += amount;
    }

    public void withdrawAmount(double withdrawVal) {

        this.balance -= withdrawVal;
    }

}

public class BankAccount {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ArrayList<BankDetails> accounts = new ArrayList<>();

        int choice;
        System.out.println("__MENU__");
        do {
            System.out.println();
            System.out.println("1.Create an account");
            System.out.println("2.Deposit money");
            System.out.println("3.Withdraw money");
            System.out.println("4.View balance");
            System.out.println("5.Exit!");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    try {
                        String n;
                        System.out.println("Enter the Account detail");
                        System.out.print("Name:");
                        n = sc.nextLine().trim().toLowerCase();
                        if (n.matches(".*\\d.*")) {

                            throw new InputMismatchException();
                        }
                        BankDetails acc1 = new BankDetails(n, 0);
                        accounts.add(acc1);
                        System.out.println("Account Created For " + acc1.name + " With Acc no :" + acc1.getID());

                    } catch (InputMismatchException e) {
                        System.out.println("Integer in Name Error");
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    try {
                        int depositID;
                        System.out.println("Enter your account number");
                        depositID = sc.nextInt();
                        sc.nextLine();
                        BankDetails accFound = null;

                        for (BankDetails acc : accounts) {
                            if (acc.getID() == depositID) {
                                accFound = acc;
                                break;
                            }
                        }

                        if (accFound != null) {
                            System.out.print("Enter amount to deposit: ");
                            double amount = sc.nextDouble();
                            sc.nextLine();
                            if (amount < 0) {
                                throw new InvalidAmountException();
                            }
                            accFound.setBalance(amount);
                            System.out.println("Amount deposited successfully.");
                        } else {
                            throw new AccountDoesNotExist();
                        }

                    } catch (InvalidAmountException e) {
                        System.out.println("negative Deposit amount");

                        System.out.println(e.getMessage());
                    } catch (AccountDoesNotExist e) {
                        System.out.println("Account Does Not exist !");
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }
                case 3 -> {
                    try {
                        System.out.println("Withdraw");
                        System.out.println("Enter the account number");
                        int withdrawAcc;
                        withdrawAcc = sc.nextInt();
                        sc.nextLine();
                        BankDetails accFound = null;

                        for (BankDetails acc : accounts) {
                            if (acc.getID() == withdrawAcc) {
                                accFound = acc;
                                break;
                            }
                        }

                        if (accFound != null) {
                            double withdrawVal;
                            System.out.println("Enter the amount to Withdraw");
                            withdrawVal = sc.nextDouble();
                            sc.nextLine();
                            if (withdrawVal > accFound.getBalance()) {
                                throw new InvalidAmountException();
                            }
                            if (withdrawVal < 0) {
                                throw new InvalidAmountException();
                            }
                            accFound.withdrawAmount(withdrawVal);
                            System.out.println("Withdrawn " + withdrawVal);

                        } else {
                            throw new AccountDoesNotExist();
                        }

                    } catch (AccountDoesNotExist e) {
                        System.out.println(e.getMessage());
                        System.out.println("Account Does Not exist !");

                    } catch (InvalidAmountException e) {
                        System.out.println("negative Withdraw amount");

                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }
                case 4 -> {
                    try {

                        System.out.println("View Balance");
                        System.out.println("Enter the account number");
                        int id = sc.nextInt();
                        sc.nextLine();
                        BankDetails accFound = null;
                        for (BankDetails acc : accounts) {
                            if (acc.getID() == id) {
                                accFound = acc;
                                break;
                            }
                        }

                        if (accFound != null) {
                            System.out.println("Balance: " + accFound.getBalance());
                        } else {
                            throw new AccountDoesNotExist();
                        }

                    } catch (AccountDoesNotExist e) {
                        System.out.println(e.getMessage());
                        System.out.println("Account Does Not exist !");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());

                    }
                }
                case 5 -> {
                    System.out.println("Exited the program!");
                    sc.close();
                    System.exit(0);
                }
                default ->
                    throw new AssertionError();
            }
        } while (choice != 5);

        sc.close();
    }
}
