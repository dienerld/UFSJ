public class Account {

  private float balance;
  private String type;

  public boolean CloseAccount() {
    return false;
  }

  protected float GetBalance() {
    return this.balance;
  }

  public void VerifyBalance() {
    System.out.println("Saldo: " + this.balance);
    return;
  }

  public void Credit(float amount) {
    this.balance += amount;
    System.out.println("Saldo Atual: " + this.balance);
    return;
  }

  public void Debit(float amount) {
    this.balance -= amount;
    System.out.println("Saldo Atual: " + this.balance);
    return;
  }
}
