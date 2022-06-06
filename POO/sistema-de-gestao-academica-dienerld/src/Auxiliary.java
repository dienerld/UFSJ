import Util.Console;

public class Auxiliary {

  public static void printLabs(Laboratory[] laboratories, int numLaboratories) {
    for (int i = 0; i < numLaboratories; i++) {
      System.out.println("------------------------");
      laboratories[i].printNameLab();
      System.out.println("------------------------");
    }
  }

  public static Collaborator searchCollaborator(Laboratory laboratory, int id, boolean isProfessor) {
    Collaborator collaborator = null;
    collaborator = laboratory.searchCollaborator(id, isProfessor);
    return collaborator;

  }

  public static Laboratory searchLaboratory(Laboratory[] laboratories, int numLaboratories) {
    printLabs(laboratories, numLaboratories);
    System.out.print("ID do Laboratório: ");
    int IDLaboratory = Integer.parseInt(Console.readLine());

    for (int i = 0; i < numLaboratories; i++) {
      if (laboratories[i].getIDLaboratory() == IDLaboratory) {
        return laboratories[i];
      }
    }
    System.out.println("Laboratório Não Encontrado");
    return null;
  }

  public static void historyCollaborator(Laboratory[] laboratories, int numLaboratories) {
    System.out.println("Digite o Nome do Colaborador");
    String NameCollaborator = Console.readLine();
    for (int i = 0; i < numLaboratories; i++) {
      laboratories[i].searchCollaboratorByName(NameCollaborator).printCollaboratorWithHistorical();

    }
    return;
  }

  public static Project searchProject(Laboratory[] laboratories, int numLaboratories) {
    System.out.print("Digite o Título do Projeto: ");
    String NameTítulo = Console.readLine();
    Project tmpProject = null;
    for (int i = 0; i < numLaboratories; i++) {
      tmpProject = laboratories[i].searchProjectByName(NameTítulo);
    }
    return tmpProject;
  }
}
