/**
 * Magician - 魔法師類別
 * 改為繼承 RangedRole，使用能量系統。
 */
public class Magician extends RangedRole {
    private int healPower;

    // 建構子新增 range, maxEnergy
    public Magician(String name, int health, int attackPower, int healPower, int range, int maxEnergy) {
        super(name, health, attackPower, range, maxEnergy);
        this.healPower = healPower;
    }

    public int getHealPower() { return healPower; }

    @Override
    public void attack(Role opponent) {
        // 攻擊需消耗 15 點能量
        if (!consumeEnergy(15)) return;

        System.out.println("✨ " + getName() + " 施放 " + getRangedAttackType() + " 攻擊 " + opponent.getName() + "！");
        opponent.takeDamage(this.getAttackPower());
    }

    public void heal(Role ally) {
        // 治療需消耗 10 點能量
        if (!consumeEnergy(10)) return;

        int oldHealth = ally.getHealth();
        ally.setHealth(ally.getHealth() + this.healPower);
        System.out.println("💚 " + this.getName() + " 治療 " + ally.getName() +
                " 回復 " + healPower + " 點生命值 (" + oldHealth + " → " + ally.getHealth() + ")");
    }

    @Override
    public void showSpecialSkill() {
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║ " + this.getName() + " 的特殊技能        ║");
        System.out.println("╠═════════════════════════════╣");
        System.out.println("║ 技能名稱：元素爆發          ║");
        System.out.println("║ 射程：" + getRange() + " 米                ║");
        System.out.println("╚═════════════════════════════╝");
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + this.getName() + " 化為魔法粒子消散...");
        System.out.println("🌟 魔法書掉落在地上。");
        System.out.println("---");
    }

    // 實作 RangedRole 方法
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