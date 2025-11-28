public class Magician extends Role {

    // // 治疗力 (Healing Power)
    private int healPower;

    // // 构造子：初始化魔法师的名称、生命值和攻击力 (Constructor: Initialize Magician's name, health, and attack power)
    public Magician(String name, int health, int attackPower, int healPower) {
        super(name, health, attackPower);
        this.healPower = healPower;
    }

    // // 取得治疗力 (Get Healing Power)
    public int getHealPower() {
        return healPower;
    }

    // // 攻击对手 (Attack Opponent)
    public void attack(Magician opponent) {
        opponent.setHealth(opponent.getHealth() - this.getAttackPower());
        System.out.println(this.getName() + " 攻击 " + opponent.getName() + " 造成 " +
                this.getAttackPower() + " 点伤害 " + opponent);
    }

    // // 治疗盟友 (Heal Ally)
    public void heal(Swordsman ally) {
        ally.setHealth(ally.getHealth() + this.healPower);
        System.out.println(this.getName() + " 治疗 " + ally.getName() + " 回复 " + healPower + " 点生命值 " + ally);
    }

    @Override
    public String toString() {
        return super.toString() + ", 治疗力: " + healPower;
    }
}