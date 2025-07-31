// PetStats.java
public class PetStats {
    // 0..100 (0 = starving, 100 = stuffed)
    private double fullness = 40;
    // 0..100 (drives speed/energy)
    private double energy = 40;

    public synchronized void tickDecay() {
        // Natural decay over time
        fullness = Math.max(0, fullness - 1.2);
        // If hungry, energy drains faster; if full, drain slowly
        double drain = fullness < 25 ? 1.5 : 0.5;
        energy = Math.max(0, energy - drain);
    }

    /** Feed by N "commit units". */
    public synchronized void feedCommits(int commits) {
        if (commits <= 0) return;
        // Tunable: how much each commit feeds/energizes
        fullness = Math.min(100, fullness + commits * 8);
        energy   = Math.min(100, energy   + commits * 6);
    }

    /** 0.8..1.6 scale mapping from fullness. */
    public synchronized double getScale() {
        return 0.8 + (fullness / 100.0) * 0.8;
    }

    /** px/tick speed multiplier ~ 1..5 */
    public synchronized double getSpeedFactor() {
        return 1.0 + (energy / 100.0) * 4.0;
    }

    /** Jump probability per second goes up with energy */
    public synchronized double getJumpChance() {
        return 0.02 + (energy / 100.0) * 0.10; // 2%..12% per second
    }

    public synchronized double getFullness() { return fullness; }
    public synchronized double getEnergy() { return energy; }
}
