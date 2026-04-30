package dao;

import model.Candidate;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for Candidate CRUD operations.
 */
public class CandidateDAO {

    public boolean addCandidate(Candidate c) {
        String sql = "INSERT INTO candidates (name, skills) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getName());
            ps.setString(2, c.getSkills());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) c.setId(keys.getInt(1));
                System.out.println("✅ Candidate added: " + c);
                return true;
            }

        } catch (SQLException e) {
            System.err.println("❌ addCandidate failed: " + e.getMessage());
        }
        return false;
    }

    public List<Candidate> getAllCandidates() {
        List<Candidate> list = new ArrayList<>();
        String sql = "SELECT * FROM candidates";

        try (Connection con = DBConnection.getConnection();
             Statement st   = con.createStatement();
             ResultSet rs   = st.executeQuery(sql)) {

            while (rs.next()) {
                Candidate c = new Candidate();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setSkills(rs.getString("skills"));
                list.add(c);
            }

        } catch (SQLException e) {
            System.err.println("❌ getAllCandidates failed: " + e.getMessage());
        }
        return list;
    }

    public Candidate getCandidateById(int id) {
        String sql = "SELECT * FROM candidates WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Candidate c = new Candidate();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setSkills(rs.getString("skills"));
                return c;
            }

        } catch (SQLException e) {
            System.err.println("❌ getCandidateById failed: " + e.getMessage());
        }
        return null;
    }

    public boolean applyForJob(int candidateId, int jobId) {
        String sql = "INSERT INTO applications (candidate_id, job_id) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, candidateId);
            ps.setInt(2, jobId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Application submitted: Candidate " + candidateId + " → Job " + jobId);
                return true;
            }

        } catch (SQLException e) {
            System.err.println("❌ applyForJob failed: " + e.getMessage());
        }
        return false;
    }
}
