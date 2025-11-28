public class Swordsman extends Role {
    public Swordsman(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    public void attack(Role opponent) {
        int damage = this.getAttackPower();
        System.out.println("⚔️  " + this.getName() + " 揮劍攻擊 " + opponent.getName() + "，造成 " + damage + " 點基礎傷害。");
        opponent.takeDamage(damage);
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + this.getName() + " 倒下了...");
        System.out.println("⚔️  " + this.getName() + " 的劍掉落在地上，發出清脆的聲響。");
        System.out.println("---");
    }

    @Override
    public void prepareBattle() {
        System.out.println("🗡️  " + this.getName() + " 擦拭劍刃，劍身反射出凜冽的寒光...");
    }

    @Override
    public void afterBattle() {
        System.out.println("🗡️  " + this.getName() + " 將劍收入劍鞘。");
    }
}