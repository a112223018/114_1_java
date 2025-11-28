public class Magician extends Role {
    private int healPower;

    public Magician(String name, int health, int attackPower, int healPower) {
        super(name, health, attackPower);
        this.healPower = healPower;
    }

    public int getHealPower() { return healPower; }

    public void attack(Role opponent) {
        int damage = this.getAttackPower();
        System.out.println("✨ " + this.getName() + " 施放魔法攻擊 " + opponent.getName() + "，造成 " + damage + " 點基礎傷害。");
        opponent.takeDamage(damage);
    }

    public void heal(Role ally) {
        ally.setHealth(ally.getHealth() + this.healPower);
        System.out.println("🩹 " + this.getName() + " 治療 " + ally.getName() + " 回復 " + healPower + " 點生命值。 " + ally);
    }

    @Override
    public String toString() {
        return super.toString() + ", 治療力: " + healPower;
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + this.getName() + " 的生命之火熄滅了...");
        System.out.println("✨ " + this.getName() + " 的身體化為無數魔法粒子，消散在空氣中。");
        System.out.println("🌟 魔法書掉落在地上，微微發光。");
        System.out.println("---");
    }

    @Override
    public void prepareBattle() {
        System.out.println("📖 " + this.getName() + " 翻開魔法書，開始吟唱古老的咒語...");
        System.out.println("✨ 魔法能量在周圍凝聚，空氣中閃爍著神秘的光芒。");
    }

    @Override
    public void afterBattle() {
        System.out.println("🧘 " + this.getName() + " 閉目冥想，恢復消耗的魔力。");
    }
}