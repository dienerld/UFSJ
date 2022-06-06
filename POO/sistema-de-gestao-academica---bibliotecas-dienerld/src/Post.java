import java.util.ArrayList;
import java.util.List;

public class Post {
  private String Title;
  private String Conference;
  private int PostYear;
  List<Collaborator> Authors;

  public Post(String Title, String Conference, int PostYear) {
    this.Title = Title;
    this.Conference = Conference;
    this.PostYear = PostYear;
    this.Authors = new ArrayList<Collaborator>();
  }

  public void setNewAutor(Collaborator collaborator) {
    this.Authors.add(collaborator);
    return;
  }

  public void printTitlePost() {
    System.out.println("Título: " + this.Title);
    return;
  }

  public void printPost() {
    System.out.println(
        "Título: " + this.Title + "  || Conferência: " + this.Conference + "  || Ano de Publicação: " + this.PostYear);
    for (Collaborator authors : Authors) {
      authors.printCollaborator();
    }
    return;
  }

  public int getPostYear() {
    return this.PostYear;
  }
}
