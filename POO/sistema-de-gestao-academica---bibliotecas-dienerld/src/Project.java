import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Util.AuxiliaryDate;
import Util.Console;

public class Project {
  /*
   * título, data de início, data de término, agência financiadora, valor
   * financiado, objetivo, descrição, coordenador e participantes
   */

  private Boolean HaveTeacher = false;
  private List<Collaborator> Collaborators;
  private int ID = -1;
  private Date StartDate;
  private Date EndDate;
  private int ValueFunding;
  private String Title;
  private String Description;
  private String Goal;
  private String AgencyFunding;
  private String State = "Em Elaboração";
  private List<Post> Posts;

  public Project(String title, Date startDate, Date endDate, String description, String goal, String agencyFunding,
      int valueFunding) {
    this.Title = title;
    this.StartDate = startDate;
    this.EndDate = endDate;
    this.Description = description;
    this.Goal = goal;
    this.AgencyFunding = agencyFunding;
    this.ValueFunding = valueFunding;
    this.Collaborators = new ArrayList<Collaborator>();
    this.Posts = new ArrayList<Post>();

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
          if (this.Posts.size() != 0) {
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
    Collaborators.add(collaborator);
    if (isProfessor) {
      setTeacher();
    }
    System.out.println("Inserido com Sucesso!!");
    return;
  }

  public void publishPost(Post post) {
    this.Posts.add(post);
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
    return this.Posts.size();
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
    for (Collaborator collaborator : this.Collaborators) {
      collaborator.printCollaborator();
      System.out.println("------------------------");
    }
    sortPost();
    for (Post post : this.Posts) {
      post.printPost();
    }
    return;
  }

  public void sortPost() {
    this.Posts.sort((d1, d2) -> sortPostByDate(d1, d2));
  }

  private int sortPostByDate(Post d1, Post d2) {
    return d2.getPostYear() - d1.getPostYear();
  }

  public void removeCollaborator(String name) {
    for (Collaborator collaborator : this.Collaborators) {
      if (collaborator.getName().equals(name)) {
        Collaborators.remove(collaborator);
      }
    }
  }
}
