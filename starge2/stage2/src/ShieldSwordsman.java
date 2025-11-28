public class ShieldSwordsman extends Role {
    public ShieldSwordsman(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    public void attack(Role opponent) {
        int damage = this.getAttackPower();
        System.out.println("🛡️⚔️ " + this.getName() + " 揮舞劍盾攻擊 " + opponent.getName() + "，造成 " + damage + " 點基礎傷害。");
        opponent.takeDamage(damage);
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + this.getName() + " 力竭倒下...");
        System.out.println("🛡️  厚重的盾牌砸在地上，揚起一陣塵土。");
        System.out.println("⚔️  " + this.getName() + " 的劍也隨之掉落。");
        System.out.println("---");
    }

    @Override
    public void prepareBattle() {
        System.out.println("🛡️  " + this.getName() + " 檢查盾牌的牢固程度...");
        System.out.println("⚔️  同時確認劍刃的鋒利度，準備應戰。");
    }

    @Override
    public void afterBattle() {
        System.out.println("🛡️  " + this.getName() + " 檢視盾牌上的新傷痕，並進行簡單修補。");
    }
}