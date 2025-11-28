/**
 * RangedRole - 遠程角色抽象類別
 * 負責處理遠程角色的共通邏輯：射程與能量管理。
 */
public abstract class RangedRole extends Role {
    // 遠程角色特有屬性
    private int range;
    private int energy;
    private int maxEnergy;

    public RangedRole(String name, int health, int attackPower, int range, int maxEnergy) {
        super(name, health, attackPower);
        this.range = range;
        this.maxEnergy = maxEnergy;
        this.energy = maxEnergy; // 初始滿能量
    }

    public int getRange() { return range; }
    public int getEnergy() { return energy; }
    public int getMaxEnergy() { return maxEnergy; }

    public void setEnergy(int energy) {
        this.energy = Math.min(energy, maxEnergy);
    }

    /**
     * 消耗能量，若不足則回傳 false
     */
    public boolean consumeEnergy(int amount) {
        if (energy >= amount) {
            energy -= amount;
            System.out.println("💫 消耗 " + amount + " 點能量，剩餘：" + energy + "/" + maxEnergy);
            return true;
        } else {
            System.out.println("❌ " + getName() + " 能量不足！需要 " + amount + "，目前只有 " + energy);
            return false;
        }
    }

    /**
     * 恢復能量
     */
    public void restoreEnergy(int amount) {
        int oldEnergy = energy;
        energy = Math.min(energy + amount, maxEnergy);
        System.out.println("✨ 恢復 " + (energy - oldEnergy) + " 點能量 (" + oldEnergy + " → " + energy + ")");
    }

    // 抽象方法：取得遠程攻擊類型
    public abstract String getRangedAttackType();

    // 覆寫 prepareBattle：提供遠程共通準備流程
    @Override
    public void prepareBattle() {
        System.out.println("🎯 " + getName() + " 準備 " + getRangedAttackType() + " 攻擊...");
        System.out.println("📊 能量值：" + energy + "/" + maxEnergy + "，射程：" + range);
        onRangedPrepare();
    }

    // 覆寫 afterBattle：戰後自動回充能量
    @Override
    public void afterBattle() {
        restoreEnergy(10);
        onRangedRecover();
    }

    protected abstract void onRangedPrepare();
    protected abstract void onRangedRecover();

    @Override
    public String toString() {
        return super.toString() + ", 能量: " + energy + "/" + maxEnergy + ", 射程: " + range;
    }
}