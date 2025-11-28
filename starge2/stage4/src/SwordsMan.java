/**
 * SwordsMan - 劍士類別
 * 改為繼承 MeleeRole，自動獲得護甲功能。
 */
public class SwordsMan extends MeleeRole {

    // 建構子新增 armor 參數
    public SwordsMan(String name, int health, int attackPower, int armor) {
        super(name, health, attackPower, armor);
    }

    @Override
    public void attack(Role opponent) {
        System.out.println("⚔️  " + this.getName() + " 揮動 " + getWeaponType() + " 攻擊 " + opponent.getName() + "！");
        opponent.takeDamage(this.getAttackPower());
    }

    @Override
    public void showSpecialSkill() {
        System.out.println("┌─────────────────────────────┐");
        System.out.println("│ " + this.getName() + " 的特殊技能        │");
        System.out.println("├─────────────────────────────┤");
        System.out.println("│ 技能名稱：連續斬擊          │");
        System.out.println("│ 護甲加成：+" + getArmor() + " 點防禦         │");
        System.out.println("└─────────────────────────────┘");
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + this.getName() + " 倒下了...");
        System.out.println("⚔️  " + getWeaponType() + " 掉落在地上。");
        System.out.println("🛡️  護甲碎裂散落一地。");
        System.out.println("---");
    }

    @Override
    public void afterBattle() {
        System.out.println("🗡️  " + this.getName() + " 將 " + getWeaponType() + " 收入劍鞘。");
    }

    // 實作 MeleeRole 抽象方法
    @Override
    public String getWeaponType() {
        return "雙手劍";
    }

    @Override
    protected void onMeleePrepare() {
        System.out.println("✨ 擦拭劍刃，劍身反射出凜冽的寒光...");
    }
}