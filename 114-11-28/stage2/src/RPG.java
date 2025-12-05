public class RPG {
    public static void main(String[] args) {
        System.out.println("════════════════════════════════════════");
        System.out.println("🎮 RPG 遊戲 - 抽象類別與模板方法模式測試");
        System.out.println("════════════════════════════════════════\n");

        Role swordsman_light = new Swordsman("光明劍士", 100, 20);
        Role swordsman_dark = new Swordsman("黑暗劍士", 100, 25);
        Magician magician_light = new Magician("光明法師", 80, 15, 10);
        Magician magician_dark = new Magician("黑暗法師", 80, 20, 5);
        Role shield_guard = new ShieldSwordsman("鋼鐵守衛", 120, 18);

        System.out.println("--- 戰鬥準備 ---");
        swordsman_light.prepareBattle();
        magician_dark.prepareBattle();
        shield_guard.prepareBattle();
        System.out.println("----------------\n");

        System.out.println("--- 戰鬥開始 ---");
        ((Swordsman) swordsman_light).attack(swordsman_dark);
        magician_dark.attack(magician_light);
        magician_dark.heal(swordsman_dark);

        System.out.println("\n🔥 測試死亡效果...");
        magician_light.takeDamage(100);

        System.out.println("\n💣 測試鋼鐵守衛受傷...");
        shield_guard.takeDamage(70);
        System.out.println("----------------\n");

        System.out.println("--- 戰鬥結束 ---");
        if (swordsman_light.isAlive()) swordsman_light.afterBattle();
        if (magician_dark.isAlive()) magician_dark.afterBattle();
        if (shield_guard.isAlive()) shield_guard.afterBattle();
    }
}