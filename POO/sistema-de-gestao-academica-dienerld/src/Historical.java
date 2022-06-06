public class Historical {
  private Project[] Projects;
  private int numProjects = 0;
  private Post[] Posts;
  private int numPosts = 0;
  private Orientation[] Orientations;
  private int numOrientations = 0;

  public Historical() {
    this.Projects = new Project[10];
    this.Orientations = new Orientation[10];
    this.Posts = new Post[10];
    return;
  }

  public void setNewProject(Project project) {
    this.Projects[this.numProjects++] = project;
    return;
  }

  public void setNewPost(Post post) {
    this.Posts[this.numPosts++] = post;
    return;
  }

  public void setNewOrientation(Orientation orientation) {
    this.Orientations[this.numOrientations++] = orientation;
    return;
  }

  public void sortHistorical() {
    sortProjects();
    sortOrientation();
    sortPost();
    return;
  }

  public void printHistorical() {
    for (int i = 0; i < this.numProjects; i++) {
      this.Projects[i].PrintProject();
    }
    for (int i = 0; i < this.numOrientations; i++) {
      this.Orientations[i].printOrientation();
    }
    for (int i = 0; i < numPosts; i++) {
      this.Posts[i].printPost();
    }
    return;
  }

  public void sortProjects() {
    for (int i = 0; i < this.numProjects - 1; i++) {
      int index = i;
      for (int j = i + 1; j < this.numProjects; j++) {
        if (this.Projects[j].getEndDate().compareTo(this.Projects[index].getEndDate()) > 0) {
          index = j;// searching for lowest index
        }
      }
      Project smallerDate = this.Projects[index];
      this.Projects[index] = this.Projects[i];
      this.Projects[i] = smallerDate;
    }
    return;
  }

  public void sortOrientation() {
    for (int i = 0; i < this.numOrientations - 1; i++) {
      int index = i;
      for (int j = i + 1; j < this.numOrientations; j++) {
        if (this.Orientations[j].getEndDate().compareTo(this.Orientations[index].getEndDate()) > 0) {
          index = j;// searching for lowest index
        }
      }
      Orientation smallerDate = this.Orientations[index];
      this.Orientations[index] = this.Orientations[i];
      this.Orientations[i] = smallerDate;
    }
    return;
  }

  public void sortPost() {
    for (int i = 0; i < this.numPosts - 1; i++) {
      int index = i;
      for (int j = i + 1; j < this.numPosts; j++) {
        if (this.Posts[j].getPostYear() > this.Posts[index].getPostYear()) {
          index = j;// searching for lowest index
        }
      }
      Post smallerDate = this.Posts[index];
      this.Posts[index] = this.Posts[i];
      this.Posts[i] = smallerDate;
    }
    return;
  }

}
