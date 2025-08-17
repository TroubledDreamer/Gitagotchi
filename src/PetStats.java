public class PetStats {

    private double fullness = 40;
    private double energy = 40;

    public synchronized void tickDecay() {
        fullness = Math.max(0, fullness - 1.2);
        double drain = fullness < 25 ? 1.5 : 0.5;
        energy = Math.max(0, energy - drain);
    }


    public synchronized void feedCommits(int commits) {
        if (commits <= 0) return;
        fullness = Math.min(100, fullness + commits * 8);
        energy   = Math.min(100, energy   + commits * 6);
    }


    public synchronized double getScale() {
        return 0.8 + (fullness / 100.0) * 0.8;
    }


    public synchronized double getSpeedFactor() {
        return 1.0 + (energy / 100.0) * 4.0;
    }


    public synchronized double getJumpChance() {
        return 0.02 + (energy / 100.0) * 0.10; 
    }

    public synchronized double getFullness() { return fullness; }
    public synchronized double getEnergy() { return energy; }
}
