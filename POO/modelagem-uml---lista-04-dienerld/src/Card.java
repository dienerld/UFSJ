import java.util.Date;

public class Card {
  private String Key;
  private float Limit;
  private Date Expiry;

  public Card(String Key, float Limit, Date Expiry) {
    this.Key = Key;
    this.Limit = Limit;
    this.Expiry = Expiry;
    return;
  }

  public boolean ValidateKey(String Key) {
    if (Key.equals(this.Key)) {
      return true;
    } else {
      return false;
    }
  }

  public float getLimit() {
    return this.Limit;
  }

  public Date getValidity() {
    return this.Expiry;
  }
}
