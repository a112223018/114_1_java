/**
 * RPG - 第三階段主程式
 * 展示多層繼承結構、新角色(Archer)以及特性展示。
 */
public class RPG {
    public static void main(String[] args) {
        System.out.println("════════════════════════════════════════");
        System.out.println("        🎮 RPG 遊戲 - 第三階段");
        System.out.println("      展示：多層繼承結構設計");
        System.out.println("════════════════════════════════════════\n");

        // ========== 建立角色 ==========
        // 近戰：name, health, attack, armor
        SwordsMan swordsMan = new SwordsMan("光明劍士", 100, 20, 5);
        ShieldSwordsMan shieldSwordsMan = new ShieldSwordsMan("鋼鐵守衛", 120, 18, 10, 20); // 最後是 defenseCapacity

        // 遠程：name, health, attack, (heal/special), range, maxEnergy
        Magician magician = new Magician("黑暗法師", 80, 20, 10, 8, 100);
        Archer archer = new Archer("精靈射手", 90, 18, 10, 80, 30); // range=10, energy=80, arrows=30

        Role[] roles = {swordsMan, shieldSwordsMan, magician, archer};

        // ========== 戰鬥準備 ==========
        System.out.println("【戰鬥準備】");
        for (Role r : roles) {
            r.prepareBattle();
        }
        System.out.println();

        // ========== 特性展示 ==========
        System.out.println("【特性展示：護甲與能量】");
        // 測試劍士受傷 (有護甲)
        System.out.println(">> 測試近戰護甲：");
        swordsMan.takeDamage(10); // 應扣 10-5=5

        // 測試法師攻擊 (消耗能量)
        System.out.println("\n>> 測試遠程能量：");
        magician.attack(swordsMan);
        magician.heal(swordsMan);

        // 測試弓箭手攻擊 (消耗箭矢+能量)
        System.out.println("\n>> 測試弓箭手資源：");
        archer.attack(magician);

        // ========== 戰鬥結束與恢復 ==========
        System.out.println("\n【戰鬥結束】");
        for (Role r : roles) {
            if (r.isAlive()) {
                r.afterBattle();
            }
        }
    }
}