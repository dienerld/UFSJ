import java.text.ParseException;
import java.util.Date;
import java.util.List;

import Util.*;

public class Registry {

  public static List<Laboratory> registerLaboratory(List<Laboratory> Laboratories) {
    // Cadastra um novo Laboratório
    System.out.print("Nome do Laboratório: ");
    String nameLab = Console.readLine();

    Laboratory laboratory = new Laboratory(nameLab, Laboratories.size());
    Laboratories.add(laboratory);

    System.out.println("Inserido com Sucesso!!\n");
    return Laboratories;
  }

  // -----------//
  public static List<Laboratory> registerCollaborator(List<Laboratory> Laboratories) {
    // Cadastra novo colaborador no laboratório desejado
    while (true) {

      for (Laboratory laboratory : Laboratories) {
        Auxiliary.printLabs(Laboratories);
        System.out.print("Digite o ID do Laboratório: ");
        int IdLabs = Integer.parseInt(Console.readLine());
        if (laboratory.getIDLaboratory() == IdLabs) {

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
          laboratory.registerCollaborator(collaborator);
          return Laboratories;
        }
      }
    }
  }

  // -----------//
  public static List<Laboratory> registerProject(List<Laboratory> Laboratories) throws ParseException {
    // PROJETOS
    // título, data de início, data de término, agência financiadora, valor
    // financiado, objetivo, descrição, coordenador e participantes

    Auxiliary.printLabs(Laboratories);
    System.out.print("Escolha o ID do Laboratório: ");
    int IdLabs = Integer.parseInt(Console.readLine());
    for (Laboratory laboratory : Laboratories) {
      if (laboratory.getIDLaboratory() == IdLabs) {
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
        laboratory.createProject(project);
        System.out.println("Projeto inserido com sucesso!");
        return Laboratories;
      }
    }
    return Laboratories;

  }

  // -----------//
  public static List<Laboratory> allocateCollaborator(List<Laboratory> Laboratories) {
    // aloca colaborador no projeto
    Laboratory tmpLaboratory = null;
    int countLoop = 0;
    do {
      tmpLaboratory = Auxiliary.searchLaboratory(Laboratories);
      if (countLoop > 5)
        return Laboratories;
      countLoop++;
    } while (tmpLaboratory == null);

    if (tmpLaboratory.getTotalProjects() <= 0) {
      System.out.println("Sem Projetos Cadastrados!!");
      return Laboratories;
    }
    tmpLaboratory.printProjects();

    Project tmpProject = tmpLaboratory.searchProject();
    if (tmpProject == null) {
      System.out.println("Projeto Inválido");
      return Laboratories;
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
        return Laboratories;
      }
      System.out.print("Inserir novo Colaborador no mesmo Laboratório: 1- Sim   0- Não\n-> ");
    } while (Integer.parseInt(Console.readLine()) == 1);

    return Laboratories;

  }

  // -----------//
  public static List<Laboratory> insertPost(List<Laboratory> Laboratories) {
    Laboratory tmpLaboratory = Auxiliary.searchLaboratory(Laboratories);
    if (tmpLaboratory.getTotalProjects() <= 0) {
      System.out.println("Sem Projetos Cadastrados!!");
      return Laboratories;
    }

    tmpLaboratory.printProjects();
    Project tmpProject = tmpLaboratory.searchProject();

    if (tmpProject.getState() != "Em andamento") {
      System.out.println("Impossibilitado de fazer publicações!!");
      return Laboratories;
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

    return Laboratories;
  }

  // -----------//
  public static List<Laboratory> insertOrientation(List<Laboratory> Laboratories) throws ParseException {
    boolean stopLoop = false;
    try {
      System.out.print("Título da Orientação: ");
      String TitleOrientation = Console.readLine();
      System.out.print("Data de início da Orientação (dd/mm/yy): ");
      Date startDate = AuxiliaryDate.formatDate(Console.readLine());
      System.out.print("Data de fim da Orientação (dd/mm/yy): ");
      Date endDate = AuxiliaryDate.formatDate(Console.readLine());

      while (!stopLoop) {
        stopLoop = true;
        Laboratory tmpLaboratory = Auxiliary.searchLaboratory(Laboratories);
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
    } catch (Exception e) {
      System.out.println(e);
      return Laboratories;
    }
    return Laboratories;
  }

  // -----------//

}
