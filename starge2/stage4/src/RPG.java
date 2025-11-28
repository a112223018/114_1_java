/**
 * RPG - 第四階段主程式
 * 展示多層繼承、新角色(Paladin)以及介面能力展示。
 */
public class RPG {
    public static void main(String[] args) {
        System.out.println("════════════════════════════════════════");
        System.out.println("        🎮 RPG 遊戲 - 第四階段");
        System.out.println("      展示：介面與多重能力設計");
        System.out.println("════════════════════════════════════════\n");

        // ========== 建立角色 ==========
        // 近戰：name, health, attack, armor
        SwordsMan swordsMan = new SwordsMan("光明劍士", 100, 20, 5);
        ShieldSwordsMan shieldSwordsMan = new ShieldSwordsMan("鋼鐵守衛", 120, 18, 10, 20); // defenseCapacity=20

        // 遠程：name, health, attack, (heal/special), range, maxEnergy
        Magician magician = new Magician("黑暗法師", 80, 20, 10, 8, 100);
        Archer archer = new Archer("精靈射手", 90, 18, 10, 80, 30);

        // 變更點 1: 新增 Paladin (多重能力角色)
        // Paladin: name, health, attack, armor, defenseCapacity, healPower, maxHolyPower
        Paladin paladin = new Paladin("聖騎士", 110, 17, 6, 15, 12, 100);

        Role[] roles = {swordsMan, shieldSwordsMan, magician, archer, paladin}; // 新增 paladin

        // ========== 戰鬥準備 ==========
        System.out.println("【戰鬥準備】");
        for (Role r : roles) {
            r.prepareBattle();
        }
        System.out.println();

        // ========== 變更點 2: 介面能力展示 (核心) ==========
        System.out.println("════════════════════════════════════════");
        System.out.println("          🔍 介面能力展示：");
        System.out.println("════════════════════════════════════════");

        System.out.println("【可防禦角色 (Defendable)】");
        for (Role role : roles) {
            if (role instanceof Defendable) {
                Defendable defender = (Defendable) role; // 轉型為 Defendable
                System.out.println("✅ " + role.getName() +
                        " - 防禦力：" + defender.getDefenseCapacity() +
                        " (可防禦：" + defender.canDefend() + ")");
                // 執行防禦
                if (defender.canDefend()) {
                    defender.defend();
                }
            }
        }
        System.out.println();

        System.out.println("【可治療角色 (Healable)】");
        for (Role role : roles) {
            if (role instanceof Healable) {
                Healable healer = (Healable) role; // 轉型為 Healable
                System.out.print("✅ " + role.getName() + " - ");
                healer.showHealInfo(); // 呼叫預設方法

                // 執行治療 (以光明劍士為目標)
                if (healer.canHeal() && swordsMan.isAlive()) {
                    healer.heal(swordsMan);
                }
            }
        }

        System.out.println("\n════════════════════════════════════════");

        // ========== 戰鬥結束與恢復 ==========
        System.out.println("\n【戰鬥結束】");
        for (Role r : roles) {
            if (r.isAlive()) {
                r.afterBattle();
            }
        }
    }
}