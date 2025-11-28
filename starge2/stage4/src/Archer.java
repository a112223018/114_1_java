/**
 * Archer - 弓箭手類別 (新)
 * 繼承 RangedRole，使用箭矢與體力(能量)。
 */
public class Archer extends RangedRole {
    private int arrowCount;
    private int maxArrows;

    public Archer(String name, int health, int attackPower, int range, int maxEnergy, int maxArrows) {
        super(name, health, attackPower, range, maxEnergy);
        this.maxArrows = maxArrows;
        this.arrowCount = maxArrows;
    }

    public int getArrowCount() { return arrowCount; }

    public void reloadArrows(int amount) {
        int oldCount = arrowCount;
        arrowCount = Math.min(arrowCount + amount, maxArrows);
        System.out.println("🏹 補充箭矢 " + (arrowCount - oldCount) + " 支 (" + oldCount + " → " + arrowCount + ")");
    }

    @Override
    public void attack(Role opponent) {
        // 雙重檢查：箭矢 與 能量
        if (arrowCount <= 0) {
            System.out.println("❌ " + getName() + " 箭矢用盡，無法攻擊！");
            return;
        }
        if (!consumeEnergy(10)) { // 拉弓消耗體力
            return;
        }

        arrowCount--;
        System.out.println("🏹 " + getName() + " 射出 " + getRangedAttackType() + " 攻擊 " + opponent.getName() + "！");
        System.out.println("📊 剩餘箭矢：" + arrowCount + "/" + maxArrows);
        opponent.takeDamage(this.getAttackPower());
    }

    @Override
    public void showSpecialSkill() {
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║ " + this.getName() + " 的特殊技能        ║");
        System.out.println("║ 技能名稱：多重箭矢          ║");
        System.out.println("║ 箭矢：" + arrowCount + "/" + maxArrows + "               ║");
        System.out.println("╚═════════════════════════════╝");
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + this.getName() + " 倒下了...");
        System.out.println("🏹 弓弦斷裂，箭矢散落一地。");
        System.out.println("---");
    }

    @Override
    public String getRangedAttackType() {
        return "精準箭矢";
    }

    @Override
    protected void onRangedPrepare() {
        System.out.println("🏹 檢查弓弦的張力和箭矢的狀態...");
    }

    @Override
    protected void onRangedRecover() {
        System.out.println("💪 " + this.getName() + " 放鬆手臂肌肉。");
        if (arrowCount < maxArrows) {
            reloadArrows(5); // 戰後補充箭矢
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", 箭矢: " + arrowCount + "/" + maxArrows;
    }
}