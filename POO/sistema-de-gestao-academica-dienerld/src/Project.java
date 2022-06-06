import java.util.Date;

import Util.AuxiliaryDate;
import Util.Console;

public class Project {
  /*
   * título, data de início, data de término, agência financiadora, valor
   * financiado, objetivo, descrição, coordenador e participantes
   */

  private Boolean HaveTeacher = false;
  private Collaborator[] Collaborators;
  private int numCollaborators = 0;
  private int ID = -1;
  private Date StartDate;
  private Date EndDate;
  private int ValueFunding;
  private String Title;
  private String Description;
  private String Goal;
  private String AgencyFunding;
  private String State = "Em Elaboração";
  private Post[] Posts;
  private int numPosts = 0;

  public Project(String title, Date startDate, Date endDate, String description, String goal, String agencyFunding,
      int valueFunding) {
    this.Title = title;
    this.StartDate = startDate;
    this.EndDate = endDate;
    this.Description = description;
    this.Goal = goal;
    this.AgencyFunding = agencyFunding;
    this.ValueFunding = valueFunding;
    this.Collaborators = new Collaborator[10];
    this.Posts = new Post[5];

  }

  public void setID(int id) {
    this.ID = id;
    return;
  }

  public String PrintTitle() {
    return this.Title;
  }

  public void setTeacher() {
    this.HaveTeacher = true;
    return;
  }

  public void setState() {
    if (this.HaveTeacher) {
      System.out.print("Escolha o Estado do Projeto: \n1 - Em andamento   2 - Concluído \n-> ");
      int state = Integer.parseInt(Console.readLine());
      switch (state) {
        case 1:
          this.State = "Em andamento";
          break;
        case 2:
          if (this.numPosts != 0) {
            this.State = "Concluído";
          } else {
            System.out.println("Inválido!! Sem Publicações Listadas");
          }
          break;
      }
    } else {
      System.out.println("Inválido!! Coordenador inexistente");
    }
    return;
  }

  public void allocateCollaboratorInternal(Collaborator collaborator, boolean isProfessor) {
    Collaborators[this.numCollaborators++] = collaborator;
    if (isProfessor) {
      setTeacher();
    }
    System.out.println("Inserido com Sucesso!!");
    return;
  }

  public void publishPost(Post post) {
    this.Posts[this.numPosts++] = post;
    System.out.println("Publicado com Sucesso!!");
    return;
  }

  public Date getEndDate() {
    return this.EndDate;
  }

  public int getIDProject() {
    return this.ID;
  }

  public int getTotalPosts() {
    return this.numPosts;
  }

  public String getTitle() {
    return this.Title;
  }

  public String getState() {
    return this.State;
  }

  public void PrintProject() {
    System.out.println("ID: " + this.ID + " || Título: " + this.Title + " || Descrição: " + this.Description
        + " \nEstado: " + this.State + " || Início: " + AuxiliaryDate.formatDateString(this.StartDate) + " ||  Fim: "
        + AuxiliaryDate.formatDateString(this.EndDate) + " \nObjetivo: " + this.Goal + " || Agência Financiadora: "
        + this.AgencyFunding + " || Valor Financiado: " + this.ValueFunding);
    System.out.println("------------------------");
    for (int i = 0; i < this.numCollaborators; i++) {
      Collaborators[i].printCollaborator();
      System.out.println("------------------------");
    }
    sortPost();
    for (int i = 0; i < this.numPosts; i++) {
      Posts[i].printPost();
    }
    return;
  }

  public void sortPost() {
    for (int i = 0; i < this.numPosts - 1; i++) {
      int index = i;
      for (int j = i + 1; j < this.numPosts; j++) {
        if (this.Posts[j].getPostYear() == this.Posts[index].getPostYear()) {
          index = j;// searching for lowest index
        }
      }
      Post smallerDate = this.Posts[index];
      this.Posts[index] = this.Posts[i];
      this.Posts[i] = smallerDate;
    }
    return;
  }

  public void removeCollaborator(String name) {

    for (int i = 0; i < this.numCollaborators; i++) {
      if (Collaborators[i].getName().equals(name)) {
        Collaborators[i] = Collaborators[this.numCollaborators - 1];
        Collaborators[this.numCollaborators - 1] = null;
        this.numCollaborators--;
      }
    }

  }
}
