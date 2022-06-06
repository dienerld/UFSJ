import java.text.ParseException;
import java.util.Date;

import Util.*;

public class Registry {

  public static Laboratory[] registerLaboratory(Laboratory[] laboratories, int numLaboratories) {
    // Cadastra um novo Laboratório
    System.out.print("Nome do Laboratório: ");
    String nameLab = Console.readLine();

    Laboratory laboratory = new Laboratory(nameLab, numLaboratories);
    laboratories[numLaboratories] = laboratory;

    System.out.println("Inserido com Sucesso!!");
    return laboratories;
  }

  // -----------//
  public static Laboratory[] registerCollaborator(Laboratory[] laboratories, int numLaboratories) {
    // Casdastra novo colaborador no laboratorio desejado
    while (true) {

      for (int i = 0; i < numLaboratories; i++) {
        Auxiliary.printLabs(laboratories, numLaboratories);
        System.out.print("Digite o ID do Laboratório: ");
        int IdLabs = Integer.parseInt(Console.readLine());
        if (laboratories[i].getIDLaboratory() == IdLabs) {

          System.out.print("Nome do colaborador: ");
          String nameCollab = Console.readLine();

          System.out.print("Email do colaborador: ");
          String emailCollab = Console.readLine();

          System.out.println("Digite o número do cargo: ");
          int function = -1;
          while (function == -1) {
            System.out.print("1- Professor   2- Pesquisador\n3- Aluno Graduação   4- Aluno de Mestrado\n-> ");
            function = Integer.parseInt(Console.readLine());

            if (function < 1 && function > 5) {
              function = -1;
              System.out.print("Opção inválida!!\nDigite novamente: ");

            }
          }

          Collaborator collaborator = new Collaborator(nameCollab, emailCollab, function);
          laboratories[i].registerCollaborator(collaborator);
          System.out.println("Colaborador inserido com sucesso!");
          return laboratories;
        }
      }
    }
  }

  // -----------//
  public static Laboratory[] registerProject(Laboratory[] laboratories, int numLaboratories) throws ParseException {
    // PROJETOS
    // título, data de início, data de término, agência financiadora, valor
    // financiado, objetivo, descrição, coordenador e participantes

    Auxiliary.printLabs(laboratories, numLaboratories);
    System.out.print("Escolha o ID do Laboratório: ");
    int IdLabs = Integer.parseInt(Console.readLine());
    for (int i = 0; i < numLaboratories; i++) {
      if (laboratories[i].getIDLaboratory() == IdLabs) {
        System.out.print("Digite Título do Projeto: ");
        String titleProject = Console.readLine();
        System.out.print("Digite Objetivo: ");
        String goalProject = Console.readLine();
        System.out.print("Digite Descrição: ");
        String descriptionProject = Console.readLine();

        System.out.print("Digite Data de Início (dd/mm/yy): ");
        Date startDateProject = AuxiliaryDate.formatDate(Console.readLine());
        System.out.print("Digite Data de Término (dd/mm/yy): ");
        Date endDateProject = AuxiliaryDate.formatDate(Console.readLine());

        System.out.print("Digite Agência Financiadora: ");
        String agencyFundingProject = Console.readLine();
        System.out.print("Digite Valor Financiado: ");
        int valueFundingProject = Integer.parseInt(Console.readLine());

        Project project = new Project(titleProject, startDateProject, endDateProject, descriptionProject, goalProject,
            agencyFundingProject, valueFundingProject);
        laboratories[i].createProject(project);
        System.out.println("Projeto inserido com sucesso!");
        i = numLaboratories;// forçar saida do loop
      }
    }
    return laboratories;

  }

  // -----------//
  public static Laboratory[] allocateCollaborator(Laboratory[] laboratories, int numLaboratories) {
    // aloca colaborador no projeto
    Laboratory tmpLaboratory = null;
    int countLoop = 0;
    do {
      tmpLaboratory = Auxiliary.searchLaboratory(laboratories, numLaboratories);
      if (countLoop > 5)
        return laboratories;
      countLoop++;
    } while (tmpLaboratory == null);

    if (tmpLaboratory.getTotalProjects() <= 0) {
      System.out.println("Sem Projetos Cadastrados!!");
      return laboratories;
    }
    tmpLaboratory.printProjects();

    Project tmpProject = tmpLaboratory.searchProject();
    if (tmpProject == null) {
      System.out.println("Projeto Inválido");
      return laboratories;
    }

    boolean isProfessor = false;
    do {
      if (tmpProject.getState() == "Em Elaboração") {
        System.out.print("Inserir Professor? 1- Sim  0- Não\n-> ");
        if (Integer.parseInt(Console.readLine()) == 1) {
          isProfessor = true;
          tmpLaboratory.printCollaborators(isProfessor);
        } else {
          isProfessor = false;
          tmpLaboratory.printCollaborators(isProfessor);
        }
        System.out.print("Escolha Colaborador a ser alocado: ");
        int IDCollaborator = Integer.parseInt(Console.readLine());
        Collaborator tmpCollaborator = Auxiliary.searchCollaborator(tmpLaboratory, IDCollaborator, isProfessor);
        tmpProject.allocateCollaboratorInternal(tmpCollaborator, isProfessor);
        tmpCollaborator.insertProjectHistorical(tmpProject);
      } else {
        System.out.println("Não é possível inserção de Colaboradores!!");
        return laboratories;
      }
      System.out.print("Inserir novo Colaborador no mesmo Laboratório: 1- Sim   0- Não\n-> ");
    } while (Integer.parseInt(Console.readLine()) == 1);

    return laboratories;

  }

  // -----------//
  public static Laboratory[] insertPost(Laboratory[] laboratories, int numLaboratories) {
    Laboratory tmpLaboratory = Auxiliary.searchLaboratory(laboratories, numLaboratories);
    if (tmpLaboratory.getTotalProjects() <= 0) {
      System.out.println("Sem Projetos Cadastrados!!");
      return laboratories;
    }

    tmpLaboratory.printProjects();
    Project tmpProject = tmpLaboratory.searchProject();

    if (tmpProject.getState() != "Em andamento") {
      System.out.println("Impossibilitado de fazer publicações!!");
      return laboratories;
    }

    System.out.print("Título da publicação: ");
    String TitlePost = Console.readLine();
    System.out.print("Nome da Conferência: ");
    String NameConference = Console.readLine();
    System.out.print("Ano da Conferência (yyyy): ");
    int DateConference = Integer.parseInt(Console.readLine());

    Post post = new Post(TitlePost, NameConference, DateConference);
    boolean stopLoop = false;
    while (!stopLoop) {
      System.out.println("Professores ");
      tmpLaboratory.printCollaborators(true);
      System.out.println("Alunos e Pesquisadores ");
      tmpLaboratory.printCollaborators(false);
      System.out.print("Digite o ID do Colaborador: ");
      int IDCollaborator = Integer.parseInt(Console.readLine());
      Collaborator tmpCollaborator = Auxiliary.searchCollaborator(tmpLaboratory, IDCollaborator, false);
      post.setNewAutor(tmpCollaborator);
      System.out.print("Inserir novo Autor? 1- Sim   0- Não\n-> ");
      if (Integer.parseInt(Console.readLine()) == 0)
        stopLoop = true;
      tmpCollaborator.insertPostHistorical(post);
    }
    tmpProject.publishPost(post);

    return laboratories;
  }

  // -----------//
  public static Laboratory[] insertOrientation(Laboratory[] laboratories, int numLaboratories) throws ParseException {
    boolean stopLoop = false;
    System.out.print("Título da Orientação: ");
    String TitleOrientation = Console.readLine();
    System.out.print("Data de início da Orientação (dd/mm/yy): ");
    Date startDate = AuxiliaryDate.formatDate(Console.readLine());
    System.out.print("Data de fim da Orientação (dd/mm/yy): ");
    Date endDate = AuxiliaryDate.formatDate(Console.readLine());
    while (!stopLoop) {
      stopLoop = true;
      Laboratory tmpLaboratory = Auxiliary.searchLaboratory(laboratories, numLaboratories);
      int totalCollaborators = tmpLaboratory.getTotalCollaborators();
      boolean isProfessor = true;
      tmpLaboratory.printCollaborators(isProfessor);
      System.out.print("ID do Professor Orientador: ");
      int IDProfessor = Integer.parseInt(Console.readLine());
      if (IDProfessor > totalCollaborators) {
        System.out.println("ID Inválido!!");
        stopLoop = false;
        continue;
      }
      Collaborator tmpProfessor = Auxiliary.searchCollaborator(tmpLaboratory, IDProfessor, isProfessor);

      isProfessor = false;
      tmpLaboratory.printCollaborators(isProfessor);
      System.out.print("ID do Aluno Orientado: ");
      int IDStudent = Integer.parseInt(Console.readLine());

      Collaborator tmpStudent = Auxiliary.searchCollaborator(tmpLaboratory, IDStudent, isProfessor);

      Orientation orientation = new Orientation(TitleOrientation, startDate, endDate, tmpStudent, tmpProfessor);
      tmpLaboratory.allocateOrientation(orientation);

    }
    return laboratories;
  }

  // -----------//

}
