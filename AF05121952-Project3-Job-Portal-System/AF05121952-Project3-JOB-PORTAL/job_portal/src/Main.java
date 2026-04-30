import dao.CandidateDAO;
import dao.JobDAO;
import model.Candidate;
import model.Job;

import java.util.List;

/**
 * Main entry point — demonstrates Job Portal with Resume Matching.
 */
public class Main {

    public static void main(String[] args) {

        JobDAO       jobDAO       = new JobDAO();
        CandidateDAO candidateDAO = new CandidateDAO();

        System.out.println("=== JOB PORTAL WITH RESUME MATCHING ===\n");

        // ── 1. Add a new job ───────────────────────────
        System.out.println("--- Add New Job ---");
        jobDAO.addJob(new Job("Android Developer", "Java,Kotlin,SQL"));

        // ── 2. List all jobs ───────────────────────────
        System.out.println("\n--- All Jobs ---");
        List<Job> jobs = jobDAO.getAllJobs();
        jobs.forEach(System.out::println);

        // ── 3. Add a candidate ─────────────────────────
        System.out.println("\n--- Add Candidate ---");
        Candidate newCandidate = new Candidate("Sneha Patel", "Java,SQL,React");
        candidateDAO.addCandidate(newCandidate);

        // ── 4. List all candidates ─────────────────────
        System.out.println("\n--- All Candidates ---");
        candidateDAO.getAllCandidates().forEach(System.out::println);

        // ── 5. Resume Matching ─────────────────────────
        System.out.println("\n--- Resume Matching for 'Java,SQL' ---");
        List<Job> matchedJobs = jobDAO.matchJobs("Java,SQL");

        System.out.println("\n--- Resume Matching for 'Python,SQL,Pandas' ---");
        jobDAO.matchJobs("Python,SQL,Pandas");

        // ── 6. Apply for a job ─────────────────────────
        System.out.println("\n--- Applying for Job ---");
        if (!matchedJobs.isEmpty()) {
            candidateDAO.applyForJob(newCandidate.getId(), matchedJobs.get(0).getId());
        }

        System.out.println("\n=== DONE ===");
    }
}
