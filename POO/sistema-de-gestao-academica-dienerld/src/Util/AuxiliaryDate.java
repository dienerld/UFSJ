package Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuxiliaryDate {
  public static Date formatDate(String date) throws ParseException {
    SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yy");
    Date DateFormated = newFormat.parse(date);
    return DateFormated;
  }

  public static String formatDateString(Date date) {
    SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
    String stringDate = DateFor.format(date);

    return stringDate;

  }

}
