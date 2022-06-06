public class savingsAccount extends Account {

  private float indicatorYeld;
  private Client client;

  public void updateBalance() {
    System.out.println("Saldo: " + super.GetBalance());
    return;
  }

  public void printName() {
    System.out.println("Cliente: " + client.getName() + "Taxa de Crescimento: " + this.indicatorYeld);
  }

}
