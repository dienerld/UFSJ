import java.util.List;

import Util.Console;

public class Auxiliary {

  public static void printLabs(List<Laboratory> Laboratories) {
    for (Laboratory laboratory : Laboratories) {
      System.out.println("------------------------");
      laboratory.printNameLab();
      System.out.println("------------------------");
    }

  }

  public static Collaborator searchCollaborator(Laboratory laboratory, int id, boolean isProfessor) {
    Collaborator collaborator = null;
    collaborator = laboratory.searchCollaborator(id, isProfessor);
    return collaborator;

  }

  public static Laboratory searchLaboratory(List<Laboratory> Laboratories) {
    printLabs(Laboratories);
    System.out.print("ID do Laboratório: ");
    int IDLaboratory = Integer.parseInt(Console.readLine());

    for (Laboratory laboratory : Laboratories) {
      if (laboratory.getIDLaboratory() == IDLaboratory) {
        return laboratory;
      }
    }
    System.out.println("Laboratório Não Encontrado");
    return null;
  }

  public static void historyCollaborator(List<Laboratory> Laboratories) {
    System.out.println("Digite o Nome do Colaborador");
    String NameCollaborator = Console.readLine();
    for (Laboratory laboratory : Laboratories) {
      laboratory.searchCollaboratorByName(NameCollaborator).printCollaboratorWithHistorical();
    }
    return;
  }

  public static Project searchProject(List<Laboratory> Laboratories) {
    System.out.print("Digite o Título do Projeto: ");
    String NameTítulo = Console.readLine();
    Project tmpProject = null;
    for (Laboratory laboratory : Laboratories) {
      tmpProject = laboratory.searchProjectByName(NameTítulo);
    }
    return tmpProject;
  }
}
