/**
 * ShieldSwordsMan - 持盾劍士類別
 * 繼承 SwordsMan，**實作 Defendable 介面**。
 */
// 變更點 1: 新增 implements Defendable
public class ShieldSwordsMan extends SwordsMan implements Defendable {
    private int defenseCapacity;

    // 建構子無變動
    public ShieldSwordsMan(String name, int health, int attackPower, int armor, int defenseCapacity) {
        super(name, health, attackPower, armor);
        this.defenseCapacity = defenseCapacity;
    }

    // 變更點 2: 標註實作介面方法
    @Override
    public int getDefenseCapacity() { return defenseCapacity; }

    @Override
    public void attack(Role opponent) {
        int reducedDamage = Math.max(1, this.getAttackPower() - 5);
        System.out.println("🛡️⚔️  " + this.getName() + " 單手揮動 " + getWeaponType() + " 攻擊 " + opponent.getName() + "！");
        opponent.takeDamage(reducedDamage);
    }

    /**
     * 變更點 3: 將 defence() 更名為 defend() 並標註實作介面
     */
    @Override
    public void defend() {
        int oldHealth = this.getHealth();
        this.setHealth(this.getHealth() + defenseCapacity);
        System.out.println("🛡️  " + this.getName() + " 舉起盾牌防禦！");
        System.out.println("💚 恢復 " + defenseCapacity + " 點生命值。(" + oldHealth + " → " + this.getHealth() + ")");
    }

    @Override
    public void showSpecialSkill() {
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║ " + this.getName() + " 的特殊技能      ║");
        System.out.println("╠═════════════════════════════╣");
        System.out.println("║ 技能名稱：盾牌猛擊          ║");
        System.out.println("║ 防禦力：+" + defenseCapacity + " 點        ║"); // 新增防禦力顯示
        System.out.println("║ 護甲值：+" + getArmor() + " 點              ║");
        System.out.println("╚═════════════════════════════╝");
    }

    // ... 其他方法保持不變 ...

    @Override
    public void onDeath() {
        System.out.println("💀 " + this.getName() + " 力竭倒下...");
        System.out.println("🛡️  厚重的盾牌砸在地上。");
        System.out.println("⚔️  " + getWeaponType() + " 也隨之掉落。");
        System.out.println("---");
    }

    @Override
    public String getWeaponType() {
        return "單手劍+盾牌";
    }

    @Override
    protected void onMeleePrepare() {
        System.out.println("🛡️  檢查盾牌的牢固程度，準備防禦姿態...");
    }

    @Override
    public void afterBattle() {
        System.out.println("🛡️  " + this.getName() + " 檢視盾牌上的新傷痕，並進行簡單修補。");
    }
}