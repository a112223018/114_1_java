/**
 * ShieldSwordsMan - 持盾劍士類別
 * 繼承 SwordsMan，使用單手劍+盾牌。
 */
public class ShieldSwordsMan extends SwordsMan {
    private int defenseCapacity;

    // 建構子新增 armor 參數，並傳遞給父類別
    public ShieldSwordsMan(String name, int health, int attackPower, int armor, int defenseCapacity) {
        super(name, health, attackPower, armor);
        this.defenseCapacity = defenseCapacity;
    }

    public int getDefenseCapacity() { return defenseCapacity; }

    @Override
    public void attack(Role opponent) {
        int reducedDamage = Math.max(1, this.getAttackPower() - 5); // 單手劍傷害較低
        System.out.println("🛡️⚔️  " + this.getName() + " 單手揮動 " + getWeaponType() + " 攻擊 " + opponent.getName() + "！");
        opponent.takeDamage(reducedDamage);
    }

    public void defence() {
        int oldHealth = this.getHealth();
        this.setHealth(this.getHealth() + defenseCapacity);
        System.out.println("🛡️  " + this.getName() + " 舉起盾牌防禦！恢復 " + defenseCapacity + " 點生命值。");
    }

    @Override
    public void showSpecialSkill() {
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║ " + this.getName() + " 的特殊技能      ║");
        System.out.println("╠═════════════════════════════╣");
        System.out.println("║ 技能名稱：盾牌猛擊          ║");
        System.out.println("║ 護甲值：+" + getArmor() + " 點              ║");
        System.out.println("╚═════════════════════════════╝");
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + this.getName() + " 力竭倒下...");
        System.out.println("🛡️  厚重的盾牌砸在地上。");
        System.out.println("⚔️  " + getWeaponType() + " 也隨之掉落。");
        System.out.println("---");
    }

    // 覆寫 MeleeRole 方法
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