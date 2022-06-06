public class checkingAccount extends Account {

  private Client client;
  private Card card;

  public void printName() {
    System.out.println("Cliente: " + client.getName() + "Tempo de Conta: " + client.getAge());
  }

  public void printCreditLimit() {
    System.out.println("Limite de Crédito: " + this.card.getLimit());
  }
}
