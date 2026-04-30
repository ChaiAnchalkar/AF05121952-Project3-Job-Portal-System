package dao;

import model.Job;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for Job operations including resume matching.
 */
public class JobDAO {

    // ──────────────────────────────────────────
    // CREATE
    // ──────────────────────────────────────────

    public boolean addJob(Job job) {
        String sql = "INSERT INTO jobs (title, skills) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, job.getTitle());
            ps.setString(2, job.getSkills());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) job.setId(keys.getInt(1));
                System.out.println("✅ Job added: " + job);
                return true;
            }

        } catch (SQLException e) {
            System.err.println("❌ addJob failed: " + e.getMessage());
        }
        return false;
    }

    // ──────────────────────────────────────────
    // READ ALL
    // ──────────────────────────────────────────

    public List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM jobs";

        try (Connection con = DBConnection.getConnection();
             Statement st   = con.createStatement();
             ResultSet rs   = st.executeQuery(sql)) {

            while (rs.next()) {
                Job j = new Job();
                j.setId(rs.getInt("id"));
                j.setTitle(rs.getString("title"));
                j.setSkills(rs.getString("skills"));
                jobs.add(j);
            }

        } catch (SQLException e) {
            System.err.println("❌ getAllJobs failed: " + e.getMessage());
        }
        return jobs;
    }

    // ──────────────────────────────────────────
    // RESUME MATCHING — core feature
    // Logic: count how many required job skills
    //        the candidate's skills contain.
    //        Threshold: at least 50% match.
    // ──────────────────────────────────────────

    public List<Job> matchJobs(String candidateSkills) {
        List<Job> matched = new ArrayList<>();

        // Split candidate skills into a set for easy lookup
        String[] candidateSkillArr = candidateSkills.toLowerCase().split(",");

        List<Job> allJobs = getAllJobs();

        for (Job job : allJobs) {
            String[] jobSkillArr = job.getSkills().toLowerCase().split(",");

            int matchCount = 0;
            for (String jobSkill : jobSkillArr) {
                for (String candidateSkill : candidateSkillArr) {
                    if (jobSkill.trim().equals(candidateSkill.trim())) {
                        matchCount++;
                        break;
                    }
                }
            }

            // Calculate match percentage
            double matchPercent = (double) matchCount / jobSkillArr.length * 100;

            if (matchPercent >= 50) {
                System.out.printf("✅ Matched: %-30s  (%.0f%% skill match)%n",
                        job.getTitle(), matchPercent);
                matched.add(job);
            }
        }

        if (matched.isEmpty()) {
            System.out.println("❌ No matching jobs found for skills: " + candidateSkills);
        }

        return matched;
    }

    // ──────────────────────────────────────────
    // DELETE
    // ──────────────────────────────────────────

    public boolean deleteJob(int id) {
        String sql = "DELETE FROM jobs WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Job deleted with id: " + id);
                return true;
            }

        } catch (SQLException e) {
            System.err.println("❌ deleteJob failed: " + e.getMessage());
        }
        return false;
    }
}
