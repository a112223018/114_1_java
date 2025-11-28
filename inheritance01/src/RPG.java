public class RPG {
    public static void main(String[] args) {

        // // 建立剑士和魔法师角色 (Create Swordsman and Magician roles)
        Swordsman swordsman_light = new Swordsman(name:"光明剑士", health: 100, attackPower: 20);
        Swordsman swordsman_dark = new Swordsman(name: "黑暗剑士", health: 100, attackPower: 25);

        Magician magician_light = new Magician(name: "光明法师", health: 80, attackPower: 15, healPower: 10);
        Magician magician_dark = new Magician(name: "黑暗法师", health: 80, attackPower: 20, healPower: 5);

        // 战斗过程 (Battle Process)
        System.out.println("战斗开始！");
        swordsman_light.attack(swordsman_dark);
        magician_dark.attack(magician_light);
        magician_dark.heal(swordsman_dark);
    }
}