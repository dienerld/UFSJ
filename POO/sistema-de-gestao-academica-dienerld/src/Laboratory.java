import Util.Console;

public class Laboratory {
  private Project[] Projects;
  private Collaborator[] Collaborators;
  private int numCollaborators = 0;
  private int numProjects = 0;
  private int ID = -1;
  private String nameLab;

  private Orientation[] orientations = new Orientation[10];
  private int numOrientations = 0;

  public Laboratory(String name, int id) {
    this.nameLab = name;
    this.ID = id;
    this.Projects = new Project[5];
    this.Collaborators = new Collaborator[20];
    this.orientations = new Orientation[10];
  }

  public void printProjects() {
    for (int i = 0; i < this.numProjects; i++) {
      this.Projects[i].PrintProject();
    }
  }

  public void createProject(Project project) {
    project.setID(this.numProjects);
    this.Projects[this.numProjects++] = project;
  }

  public void registerCollaborator(Collaborator collaborator) {
    collaborator.setID(this.numCollaborators);
    this.Collaborators[this.numCollaborators++] = collaborator;
  }

  public void allocateOrientation(Orientation orientation) {
    this.orientations[this.numOrientations++] = orientation;
    System.out.println("Orientação inserida com sucesso");
    return;
  }

  public void printCollaborators(boolean isProfessor) {
    for (int i = 0; i < this.numCollaborators; i++) {
      System.out.println("------------------------");
      if (isProfessor == this.Collaborators[i].getIsProfessor())
        this.Collaborators[i].printCollaborator();
    }
  }

  public Collaborator searchCollaboratorByName(String name) {
    for (int i = 0; i < this.numCollaborators; i++) {
      if (name.equals(this.Collaborators[i].getName())) {
        return this.Collaborators[i];
      }
    }
    return null;
  }

  public Project searchProjectByName(String name) {
    for (int i = 0; i < this.numProjects; i++) {
      if (name.equals(this.Projects[i].getTitle())) {
        return this.Projects[i];
      }
    }
    return null;
  }

  public void printNameLab() {
    System.out.println("ID: " + this.ID + " || Nome: " + this.nameLab);
  }

  public int getIDLaboratory() {
    return this.ID;
  }

  public int getTotalProjects() {
    return this.numProjects;
  }

  public int getTotalCollaborators() {
    return this.numCollaborators;
  }

  public int getTotalOrientations() {
    return this.numOrientations;
  }

  public int getTotalPosts() {
    int totalPosts = 0;
    for (int i = 0; i < this.numProjects; i++) {
      totalPosts += this.Projects[i].getTotalPosts();
    }
    return totalPosts;
  }

  public Collaborator searchCollaborator(int id, boolean isProfessor) {
    Collaborator returnCollaborator = null;
    for (int i = 0; i < this.numCollaborators; i++) {
      if (this.Collaborators[i].searchCollaborator(id, isProfessor))
        returnCollaborator = this.Collaborators[i];
    }
    return returnCollaborator;
  }

  public Project searchProject() {
    System.out.print("Digite o ID do Projeto: ");
    int id = Integer.parseInt(Console.readLine());
    if (id <= this.numProjects) {
      for (int i = 0; i < this.numProjects; i++) {
        if (this.Projects[i].getIDProject() == id) {
          return this.Projects[i];
        }
      }
    }
    System.out.println("ID Inválido!!");
    return null;
  }

  public int getProjectInElaboration() {
    int total = 0;
    for (int i = 0; i < this.numProjects; i++) {
      if (this.Projects[i].getState().equals("Em Elaboração")) {
        total++;
      }
    }
    return total;
  }

  public int getProjectInGoing() {
    int total = 0;
    for (int i = 0; i < this.numProjects; i++) {
      if (this.Projects[i].getState().equals("Em Andamento")) {
        total++;
      }
    }
    return total;
  }

  public int getProjectCompleted() {
    int total = 0;
    for (int i = 0; i < this.numProjects; i++) {
      if (this.Projects[i].getState().equals("Concluido")) {
        total++;
      }
    }
    return total;
  }

}
