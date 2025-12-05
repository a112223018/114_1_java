/**
 * Magician - 魔法師類別
 * 繼承 RangedRole，**實作 Healable 介面**。
 */
// 變更點 1: 新增 implements Healable
public class Magician extends RangedRole implements Healable {
    private int healPower;

    // 建構子無變動
    public Magician(String name, int health, int attackPower, int healPower, int range, int maxEnergy) {
        super(name, health, attackPower, range, maxEnergy);
        this.healPower = healPower;
    }

    // 變更點 2: 標註實作介面方法
    @Override
    public int getHealPower() { return healPower; }

    @Override
    public void attack(Role opponent) {
        // 攻擊需消耗 15 點能量
        if (!consumeEnergy(15)) return;

        System.out.println("✨ " + getName() + " 施放 " + getRangedAttackType() + " 攻擊 " + opponent.getName() + "！");
        opponent.takeDamage(this.getAttackPower());
    }

    /**
     * 變更點 3: 標註實作介面方法
     */
    @Override
    public void heal(Role target) {
        // 治療需消耗 10 點能量
        if (!consumeEnergy(10)) return;

        int oldHealth = target.getHealth();
        target.setHealth(target.getHealth() + this.healPower);
        System.out.println("💚 " + this.getName() + " 治療 " + target.getName() +
                " 回復 " + healPower + " 點生命值 (" + oldHealth + " → " + target.getHealth() + ")");
    }

    @Override
    public void showSpecialSkill() {
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║ " + this.getName() + " 的特殊技能        ║");
        System.out.println("╠═════════════════════════════╣");
        System.out.println("║ 技能名稱：元素爆發          ║");
        System.out.println("║ 射程：" + getRange() + " 米                ║");
        System.out.println("║ 治療力：+" + healPower + " 點        ║"); // 新增治療力顯示
        System.out.println("╚═════════════════════════════╝");
    }

    // ... 其他方法保持不變 ...

    @Override
    public void onDeath() {
        System.out.println("💀 " + this.getName() + " 化為魔法粒子消散...");
        System.out.println("🌟 魔法書掉落在地上。");
        System.out.println("---");
    }

    @Override
    public String getRangedAttackType() {
        return "魔法彈";
    }

    @Override
    protected void onRangedPrepare() {
        System.out.println("📖 翻開魔法書，開始吟唱古老的咒語...");
    }

    @Override
    protected void onRangedRecover() {
        System.out.println("🧘 " + this.getName() + " 閉目冥想，深度恢復魔力。");
    }

    @Override
    public String toString() {
        return super.toString() + ", 治癒力: " + healPower;
    }
}