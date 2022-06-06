import java.util.ArrayList;
import java.util.List;

import Util.Console;

public class Laboratory {
  private List<Project> Projects;
  private List<Collaborator> Collaborators;
  private List<Orientation> orientations;

  private int ID = -1;
  private String nameLab;

  public Laboratory(String name, int id) {
    this.nameLab = name;
    this.ID = id;
    this.Projects = new ArrayList<Project>();
    this.Collaborators = new ArrayList<Collaborator>();
    this.orientations = new ArrayList<Orientation>();
  }

  public void printProjects() {
    for (Project project : Projects) {
      project.PrintProject();
    }

  }

  public void createProject(Project project) {
    project.setID(this.Projects.size());
    this.Projects.add(project);
    System.out.println("Projeto inserido com sucesso");

  }

  public void registerCollaborator(Collaborator collaborator) {
    collaborator.setID(this.Collaborators.size());
    this.Collaborators.add(collaborator);
    System.out.println("Colaborador inserido com sucesso\n");

  }

  public void allocateOrientation(Orientation orientation) {
    this.orientations.add(orientation);
    System.out.println("Orientação inserida com sucesso");
    return;
  }

  public void printCollaborators(boolean isProfessor) {
    for (Collaborator collaborator : this.Collaborators) {
      System.out.println("------------------------");
      if (isProfessor == collaborator.getIsProfessor())
        collaborator.printCollaborator();
    }
    return;
  }

  public Collaborator searchCollaboratorByName(String name) {
    for (Collaborator collaborator : this.Collaborators) {
      if (name.equals(collaborator.getName())) {
        return collaborator;
      }
    }
    return null;
  }

  public Project searchProjectByName(String name) {
    for (Project project : this.Projects) {
      if (name.equals(project.getTitle())) {
        return project;
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
    return this.Projects.size();
  }

  public int getTotalCollaborators() {
    return this.Collaborators.size();
  }

  public int getTotalOrientations() {
    return this.orientations.size();
  }

  public int getTotalPosts() {
    int totalPosts = 0;
    for (Project project : this.Projects) {
      totalPosts += project.getTotalPosts();
    }
    return totalPosts;
  }

  public Collaborator searchCollaborator(int id, boolean isProfessor) {
    for (Collaborator collaborator : this.Collaborators) {
      if (collaborator.searchCollaborator(id, isProfessor)) {
        return collaborator;
      }
    }
    return null;
  }

  public Project searchProject() {
    System.out.print("Digite o ID do Projeto: ");
    int id = Integer.parseInt(Console.readLine());
    if (id <= this.Projects.size()) {
      for (Project project : this.Projects) {
        if (project.getIDProject() == id) {
          return project;
        }
      }
    }
    System.out.println("ID Inválido!!");
    return null;
  }

  public int getProjectInElaboration() {
    int total = 0;
    for (Project projects : this.Projects) {
      if (projects.getState().equals("Em Elaboração")) {
        total++;
      }
    }
    return total;
  }

  public int getProjectInGoing() {
    int total = 0;
    for (Project project : this.Projects) {
      if (project.getState().equals("Em Andamento")) {
        total++;
      }
    }
    return total;
  }

  public int getProjectCompleted() {
    int total = 0;
    for (Project project : this.Projects) {
      if (project.getState().equals("Concluido")) {
        total++;
      }
    }
    return total;
  }
}
