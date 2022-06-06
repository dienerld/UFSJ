
public class Post {
  private String Title;
  private String Conference;
  private int PostYear;
  Collaborator[] Autors;
  private int numAutors = 0;

  public Post(String Title, String Conference, int PostYear) {
    this.Title = Title;
    this.Conference = Conference;
    this.PostYear = PostYear;
    this.Autors = new Collaborator[5];
  }

  public void setNewAutor(Collaborator collaborator) {
    this.Autors[this.numAutors++] = collaborator;
    return;
  }

  public void printTitlePost() {
    System.out.println("Título: " + this.Title);
    return;
  }

  public void printPost() {
    System.out.println(
        "Título: " + this.Title + "  || Conferência: " + this.Conference + "  || Ano de Publicação: " + this.PostYear);
    for (int i = 0; i < this.numAutors; i++) {
      Autors[i].printCollaborator();
    }
    return;
  }

  public int getPostYear() {
    return this.PostYear;
  }
}
