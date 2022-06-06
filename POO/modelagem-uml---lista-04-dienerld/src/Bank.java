import java.util.ArrayList;
import java.util.List;

public class Bank {
  private String nameBank;
  private List<savingsAccount> savingsAccounts;
  private List<checkingAccount> checkingAccounts;

  public Bank(String NameBank) {
    this.nameBank = NameBank;
    this.checkingAccounts = new ArrayList<checkingAccount>();
    this.savingsAccounts = new ArrayList<savingsAccount>();
    return;
  }

  public void createCheckingAccount() {
    checkingAccounts.add(new checkingAccount());
    return;
  }

  public void createSavingsAccount() {
    savingsAccounts.add(new savingsAccount());
    return;
  }

  public void printNameBank() {
    System.out.println("Nome do Banco: " + this.nameBank);
    return;
  }

}
