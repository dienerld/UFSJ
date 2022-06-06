import java.util.Date;

import Util.AuxiliaryDate;

public class Orientation {
  String Title;
  Collaborator Professor;
  Collaborator Guiding;
  Date StartDate;
  Date EndDate;

  public Orientation(String Title, Date StartDate, Date EndDate, Collaborator Student, Collaborator professor) {
    this.Title = Title;
    this.StartDate = StartDate;
    this.EndDate = EndDate;
    this.Guiding = Student;
    this.Professor = professor;
  }

  public void printOrientation() {
    System.out.println("Título: " + this.Title + " || Data de Inicio " + AuxiliaryDate.formatDateString(this.StartDate)
        + " || Data de Fim " + AuxiliaryDate.formatDateString(this.EndDate) + "\nOrientado: " + this.Guiding
        + " || Orientador: " + this.Professor);
    return;
  }

  public Date getEndDate() {
    return this.EndDate;
  }
}
