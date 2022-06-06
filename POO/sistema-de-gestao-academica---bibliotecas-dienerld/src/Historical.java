import java.util.ArrayList;
import java.util.List;

public class Historical {
  private List<Project> Projects;
  private List<Post> Posts;
  private List<Orientation> Orientations;

  public Historical() {
    this.Projects = new ArrayList<Project>();
    this.Orientations = new ArrayList<Orientation>();
    this.Posts = new ArrayList<Post>();
    return;
  }

  public void setNewProject(Project project) {
    this.Projects.add(project);
    return;
  }

  public void setNewPost(Post post) {
    this.Posts.add(post);
    return;
  }

  public void setNewOrientation(Orientation orientation) {
    this.Orientations.add(orientation);
    return;
  }

  public void printHistorical() {
    for (Project project : Projects) {
      project.PrintProject();
    }

    for (Orientation orientation : Orientations) {
      orientation.printOrientation();
    }

    for (Post post : Posts) {
      post.printPost();
    }
    return;
  }

  public int sortOrientationsByDate(Orientation a, Orientation b) {
    return a.getEndDate().compareTo(b.getEndDate());
  }

  public int sortProjectsByDate(Project a, Project b) {
    return a.getEndDate().compareTo(b.getEndDate());
  }

  public int sortPostByDate(Post a, Post b) {
    return b.getPostYear() - a.getPostYear();
  }

  public void sortHistorical() {
    this.Orientations.sort((d1, d2) -> sortOrientationsByDate(d1, d2));
    this.Projects.sort((d1, d2) -> sortProjectsByDate(d1, d2));
    this.Posts.sort((d1, d2) -> sortPostByDate(d1, d2));
    return;
  }

}
