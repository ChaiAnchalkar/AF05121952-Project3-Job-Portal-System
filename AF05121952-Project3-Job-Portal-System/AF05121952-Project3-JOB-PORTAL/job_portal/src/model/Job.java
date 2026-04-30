package model;

/**
 * Represents a job listing.
 */
public class Job {

    private int    id;
    private String title;
    private String skills;   // comma-separated

    public Job() {}

    public Job(String title, String skills) {
        this.title  = title;
        this.skills = skills;
    }

    public int    getId()     { return id; }
    public String getTitle()  { return title; }
    public String getSkills() { return skills; }

    public void setId(int id)          { this.id     = id; }
    public void setTitle(String t)     { this.title  = t; }
    public void setSkills(String s)    { this.skills = s; }

    @Override
    public String toString() {
        return "Job{id=" + id + ", title='" + title + "', skills='" + skills + "'}";
    }
}
