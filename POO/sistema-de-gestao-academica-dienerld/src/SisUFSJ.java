import java.text.ParseException;

import Util.*;

/*
 * SisUFSJ
 */

public class SisUFSJ {
  private static Laboratory[] laboratories = new Laboratory[10];
  private static int numLaboratories = 0;

  public static void main(String[] args) throws ParseException {
    int opcao = 0;

    do {
      System.out.println("1 - Cadastrar Laboratório");
      System.out.println("2 - Cadastrar Colaboradores");
      System.out.println("3 - Cadastrar Projetos");
      System.out.println("4 - Alocar Colaboradores");
      System.out.println("5 - Listar Laboratórios");
      System.out.println("6 - Fazer Publicação");
      System.out.println("7 - Adicionar Orientação");
      System.out.println("8 - Relatório Sobre Laborátorio");
      System.out.println("9 - Consultar Colaborador");
      System.out.println("10 - Consultar Projeto");
      System.out.println("11 - Editar Projeto");

      System.out.print("Escolha uma opcao: ");
      opcao = Integer.parseInt(Console.readLine());

      switch (opcao) {
        case 1: // Cadastrar Laboratório
          laboratories = Registry.registerLaboratory(laboratories, numLaboratories);
          numLaboratories++;
          break;
        case 2:// Cadastrar Colaboradores
          if (numLaboratories > 0) {
            laboratories = Registry.registerCollaborator(laboratories, numLaboratories);
          } else {
            System.out.println("Nenhum laboratório cadastrado!");
          }
          break;
        case 3:// Cadastrar Projetos
          if (numLaboratories > 0) {
            laboratories = Registry.registerProject(laboratories, numLaboratories);
          } else {
            System.out.println("Nenhum laboratório cadastrado!");
          }
          break;
        case 4:// Alocar Colaboradores
          if (numLaboratories > 0) {
            laboratories = Registry.allocateCollaborator(laboratories, numLaboratories);
          } else {
            System.out.println("Nenhum laboratório cadastrado!");
          }
          break;
        case 5:// Listar Laboratórios
          if (numLaboratories > 0) {
            Auxiliary.printLabs(laboratories, numLaboratories);
          } else {
            System.out.println("Nenhum laboratório cadastrado!");
          }
          break;
        case 6:// Fazer Publicação
          if (numLaboratories > 0) {
            laboratories = Registry.insertPost(laboratories, numLaboratories);
          } else {
            System.out.println("Nenhum laboratório cadastrado!");
          }
          break;
        case 7:// Adicionar Orientação
          if (numLaboratories > 0) {
            laboratories = Registry.insertOrientation(laboratories, numLaboratories);
          } else {
            System.out.println("Nenhum laboratório cadastrado!");
          }
          break;
        case 8: // Relatório Sobre Laborátorio
          if (numLaboratories > 0) {
            Laboratory tmpLaboratory = Auxiliary.searchLaboratory(laboratories, numLaboratories);
            System.out.println("Total de Colaboradores: " + tmpLaboratory.getTotalCollaborators()
                + "\nProjetos em Elaboração: " + tmpLaboratory.getProjectInElaboration() + "\nProjetos em Andamento: "
                + tmpLaboratory.getProjectInGoing() + "\nProjetos Concluidos: " + tmpLaboratory.getProjectCompleted()
                + "\nTotal de Projetos: " + tmpLaboratory.getTotalProjects() + "\nTotal de Orientações: "
                + tmpLaboratory.getTotalOrientations() + "\nTotal de Publicações: " + tmpLaboratory.getTotalPosts());

          } else {
            System.out.println("Nenhum laboratório cadastrado!");
          }
          break;
        case 9: // Consultar Colaborador
          if (numLaboratories > 0) {
            Auxiliary.historyCollaborator(laboratories, numLaboratories);
          } else {
            System.out.println("Nenhum laboratório cadastrado!");
          }
          break;
        case 10: // Consultar Projeto
          if (numLaboratories > 0) {
            Project tmpProject = Auxiliary.searchProject(laboratories, numLaboratories);
            tmpProject.PrintProject();
          } else {
            System.out.println("Nenhum laboratório cadastrado!");
          }
          break;
        case 11: // Editar Projeto
          if (numLaboratories > 0) {
            Project tmpProject = Auxiliary.searchProject(laboratories, numLaboratories);
            if (tmpProject == null) {
              System.out.println("Projeto não encontrado no laboratório");
              break;
            }
            System.out.print("1- Remover Collaborator 2- Adicionar Colaborador 3- Alterar Estado\n-> ");
            int option = Integer.parseInt(Console.readLine());
            switch (option) {
              case 1: // Remover Collaborator
                System.out.print("Digite Nome do Colaborador\n->");
                String name = Console.readLine();
                tmpProject.removeCollaborator(name);
                break;
              case 2: // Adicionar Colaborador
                boolean isProfessor;
                if (tmpProject.getState() == "Em Elaboração") {
                  System.out.print("Inserir Professor? 1- Sim  0- Não\n-> ");
                  if (Integer.parseInt(Console.readLine()) == 1) {
                    isProfessor = true;
                  } else {
                    isProfessor = false;
                  }
                  System.out.print("Digite nome do Colaborador a ser alocado: ");
                  String nameCollab = Console.readLine();
                  String titleProject = tmpProject.getTitle();
                  Laboratory tmpLaboratory = null;
                  for (int i = 0; i < numLaboratories; i++) {
                    if (laboratories[i].searchProjectByName(titleProject).getTitle().equals(tmpProject.getTitle())) {
                      tmpLaboratory = laboratories[i];
                      i = numLaboratories;
                    }
                  }
                  Collaborator tmpCollaborator = tmpLaboratory.searchCollaboratorByName(nameCollab);
                  if (tmpCollaborator == null) {
                    System.out.println("Colaborador não encontrado no laboratório");
                    break;
                  }
                  tmpProject.allocateCollaboratorInternal(tmpCollaborator, isProfessor);
                  tmpCollaborator.insertProjectHistorical(tmpProject);
                } else {
                  System.out.println("Não é possível inserção de Colaboradores!!");
                }
                break;
              case 3: // Alterar Estado
                tmpProject.setState();
                break;

            }
          } else {
            System.out.println("Nenhum laboratório cadastrado!");
          }
          break;
      }
    } while (opcao != 30);
  }

}
