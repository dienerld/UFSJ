
public class Collaborator {
  private int ID = -1;
  private Historical historical;
  private int numProjectsActive = 0;
  private String Name;
  private String Email;
  private String Function;

  public Collaborator(String Name, String Email, int Function) {
    this.Name = Name;
    this.Email = Email;
    this.historical = new Historical();
    switch (Function) {
      case 1:
        this.Function = "Professor";
        break;
      case 2:
        this.Function = "Pesquisador";
        break;
      case 3:
        this.Function = "Aluno de Graduação";
        break;
      case 4:
        this.Function = "Aluno de Mestrado";
        break;
    }

  }

  public void setID(int ID) {
    this.ID = ID;
    return;
  }

  public void insertProjectHistorical(Project project) {
    this.historical.setNewProject(project);
    return;
  }

  public void insertPostHistorical(Post post) {
    this.historical.setNewPost(post);
    return;
  }

  public void insertOrientationHistorical(Orientation orientation) {
    this.historical.setNewOrientation(orientation);
    return;
  }

  public void printCollaborator() {
    System.out.println(
        "ID: " + this.ID + " || Nome: " + this.Name + " || Email: " + this.Email + " Função: " + this.Function);
    return;
  }

  public void printCollaboratorWithHistorical() {
    this.historical.sortHistorical();
    printCollaborator();
    System.out.println("Projetos Participados");
    this.historical.printHistorical();
    return;
  }

  public boolean searchCollaborator(int id, boolean isProfessor) {
    if (id == this.ID) {
      if (this.numProjectsActive >= 2 && this.Function.equals("Aluno de Graduação")) {
        System.out.println("Colaborador sem Disponibilidade!!");
      } else if (isProfessor) {
        if (this.Function.equals("Professor"))
          isProfessor = true;
      } else {
        isProfessor = true;
      }
    } else {
      isProfessor = false;
    }
    return isProfessor;
  }

  public String getName() {
    return this.Name;
  }

  public boolean getIsProfessor() {
    boolean isProfessor = false;
    if (this.Function.equals("Professor"))
      isProfessor = true;
    return isProfessor;
  }
}
