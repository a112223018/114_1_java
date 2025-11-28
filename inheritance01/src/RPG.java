public class RPG {
    public static void main(String[] args) {

        // 建立剑士和魔法师角色
        Swordsman swordsman_light = new Swordsman("光明剑士", 100, 20);
        Swordsman swordsman_dark = new Swordsman("黑暗剑士", 100, 25);

        Magician magician_light = new Magician("光明法师", 80, 15, 10);
        Magician magician_dark = new Magician("黑暗法师", 80, 20, 5);

        // 战斗过程
        System.out.println("战斗开始！");
        swordsman_light.attack(swordsman_dark);
        magician_dark.attack(magician_light);
        magician_dark.heal(swordsman_dark);
    }
}
