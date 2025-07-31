import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class GitFeeder {
    private final String repoPath;
    private Instant lastCheck;       
    private final String authorFilter;  

    public GitFeeder(String repoPath, String authorFilter) {
        this.repoPath = Objects.requireNonNull(repoPath);
        this.authorFilter = authorFilter;
        this.lastCheck = Instant.now().minusSeconds(3600); 
    }

    public int pollNewCommits() {
        try {
            String sinceIso = DateTimeFormatter.ISO_INSTANT.format(lastCheck);
            ProcessBuilder pb = new ProcessBuilder(
                    "git", "-C", repoPath,
                    "rev-list", "--count",
                    "--since=" + sinceIso,
                    authorFilter != null ? "--author=" + authorFilter : "",
                    "HEAD"
            );
            pb.command().removeIf(String::isEmpty);
            pb.redirectErrorStream(true);

            Process p = pb.start();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String out = br.readLine();
                p.waitFor();
                int count = (out == null || out.isEmpty()) ? 0 : Integer.parseInt(out.trim());
                lastCheck = Instant.now();
                return Math.max(0, count);
            }
        } catch (Exception e) {
            System.err.println("[GitFeeder] " + e.getMessage());
            return 0;
        }
    }
}
