import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Util.*;

/*
 * SisUFSJ
 */

public class SisUFSJ {
  private static List<Laboratory> Laboratories = new ArrayList<Laboratory>();

  public static void main(String[] args) throws ParseException {
    int option = 0;

    do {
      System.out.println("1 - Cadastrar Laboratório");
      System.out.println("2 - Cadastrar Colaboradores");
      System.out.println("3 - Cadastrar Projetos");
      System.out.println("4 - Alocar Colaboradores");
      System.out.println("5 - Listar Laboratórios");
      System.out.println("6 - Fazer Publicação");
      System.out.println("7 - Adicionar Orientação");
      System.out.println("8 - Relatório Sobre Laboratório");
      System.out.println("9 - Consultar Colaborador");
      System.out.println("10 - Consultar Projeto");
      System.out.println("11 - Editar Projeto");

      System.out.print("Escolha uma opção: ");
      option = Integer.parseInt(Console.readLine());

      switch (option) {
        case 1: // Cadastrar Laboratório
          Laboratories = Registry.registerLaboratory(Laboratories);
          break;
        case 2:// Cadastrar Colaboradores
          if (Laboratories.size() > 0) {
            Laboratories = Registry.registerCollaborator(Laboratories);
          } else {
            System.out.println("Nenhum laboratório cadastrado!");
          }
          break;
        case 3:// Cadastrar Projetos
          if (Laboratories.size() > 0) {
            Laboratories = Registry.registerProject(Laboratories);
          } else {
            System.out.println("Nenhum laboratório cadastrado!");
          }
          break;
        case 4:// Alocar Colaboradores
          if (Laboratories.size() > 0) {
            Laboratories = Registry.allocateCollaborator(Laboratories);
          } else {
            System.out.println("Nenhum laboratório cadastrado!");
          }
          break;
        case 5:// Listar Laboratórios
          if (Laboratories.size() > 0) {
            Auxiliary.printLabs(Laboratories);
          } else {
            System.out.println("Nenhum laboratório cadastrado!");
          }
          break;
        case 6:// Fazer Publicação
          if (Laboratories.size() > 0) {
            Laboratories = Registry.insertPost(Laboratories);
          } else {
            System.out.println("Nenhum laboratório cadastrado!");
          }
          break;
        case 7:// Adicionar Orientação
          if (Laboratories.size() > 0) {
            Laboratories = Registry.insertOrientation(Laboratories);
          } else {
            System.out.println("Nenhum laboratório cadastrado!");
          }
          break;
        case 8: // Relatório Sobre Laboratório
          if (Laboratories.size() > 0) {
            Laboratory tmpLaboratory = Auxiliary.searchLaboratory(Laboratories);
            System.out.println("Total de Colaboradores: " + tmpLaboratory.getTotalCollaborators()
                + "\nProjetos em Elaboração: " + tmpLaboratory.getProjectInElaboration() + "\nProjetos em Andamento: "
                + tmpLaboratory.getProjectInGoing() + "\nProjetos Concluídos: " + tmpLaboratory.getProjectCompleted()
                + "\nTotal de Projetos: " + tmpLaboratory.getTotalProjects() + "\nTotal de Orientações: "
                + tmpLaboratory.getTotalOrientations() + "\nTotal de Publicações: " + tmpLaboratory.getTotalPosts());

          } else {
            System.out.println("Nenhum laboratório cadastrado!");
          }
          break;
        case 9: // Consultar Colaborador
          if (Laboratories.size() > 0) {
            Auxiliary.historyCollaborator(Laboratories);
          } else {
            System.out.println("Nenhum laboratório cadastrado!");
          }
          break;
        case 10: // Consultar Projeto
          if (Laboratories.size() > 0) {
            Project tmpProject = Auxiliary.searchProject(Laboratories);
            if (tmpProject == null) {
              System.out.println("Laboratório não encontrado");
            } else {
              tmpProject.PrintProject();
            }
          } else {
            System.out.println("Nenhum laboratório cadastrado!");
          }
          break;
        case 11: // Editar Projeto
          if (Laboratories.size() > 0) {
            Project tmpProject = Auxiliary.searchProject(Laboratories);
            if (tmpProject == null) {
              System.out.println("Projeto não encontrado no laboratório");
              break;
            }
            System.out.print("1- Remover Collaborator 2- Adicionar Colaborador 3- Alterar Estado\n-> ");
            switch (Integer.parseInt(Console.readLine())) {
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
                  for (Laboratory laboratory : Laboratories) {
                    if (laboratory.searchProjectByName(titleProject).getTitle().equals(tmpProject.getTitle())) {
                      tmpLaboratory = laboratory;
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
    } while (option != 30);
  }

}
