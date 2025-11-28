public class Swordsman extends Role {
    // // 构造子：初始化剑士的名称、生命值和攻击力 (Constructor: Initialize Swordsman's name, health, and attack power)
    public Swordsman(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    // // 攻击对手 (Attack Opponent)
    public void attack(Swordsman opponent) {
        opponent.setHealth(opponent.getHealth() - this.getAttackPower());
        System.out.println(this.getName() + " 攻击 " + opponent.getName() + " 造成 " +
                this.getAttackPower() + " 点伤害 " + opponent);
    }
}
