package model;

/**
 * Represents a job candidate.
 */
public class Candidate {

    private int    id;
    private String name;
    private String skills;   // comma-separated

    public Candidate() {}

    public Candidate(String name, String skills) {
        this.name   = name;
        this.skills = skills;
    }

    public int    getId()     { return id; }
    public String getName()   { return name; }
    public String getSkills() { return skills; }

    public void setId(int id)        { this.id     = id; }
    public void setName(String n)    { this.name   = n; }
    public void setSkills(String s)  { this.skills = s; }

    @Override
    public String toString() {
        return "Candidate{id=" + id + ", name='" + name + "', skills='" + skills + "'}";
    }
}
