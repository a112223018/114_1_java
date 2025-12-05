package com.rpg.roles.melee;

import com.rpg.core.Role;
import com.rpg.interfaces.Defendable;
import com.rpg.interfaces.Healable;

public class Paladin extends MeleeRole implements Defendable, Healable {
    private int defenseCapacity; // Defendable
    private int healPower;       // Healable
    private int holyPower;       // Paladin 特有資源：聖能值
    private int maxHolyPower;

    public Paladin(String name, int health, int attackPower, int armor,
                   int defenseCapacity, int healPower, int maxHolyPower) {
        super(name, health, attackPower, armor);
        this.defenseCapacity = defenseCapacity;
        this.healPower = healPower;
        this.maxHolyPower = maxHolyPower;
        this.holyPower = maxHolyPower;
    }

    public int getHolyPower() { return holyPower; }

    private boolean consumeHolyPower(int amount) {
        if (holyPower >= amount) {
            holyPower -= amount;
            System.out.println("✨ 消耗 " + amount + " 點聖能，剩餘：" + holyPower + "/" + maxHolyPower);
            return true;
        } else {
            System.out.println("❌ 聖能不足！需要 " + amount + "，目前只有 " + holyPower);
            return false;
        }
    }

    private void restoreHolyPower(int amount) {
        int oldPower = holyPower;
        holyPower = Math.min(holyPower + amount, maxHolyPower);
        System.out.println("🌟 恢復 " + (holyPower - oldPower) + " 點聖能 (" + oldPower + " → " + holyPower + ")");
    }

    // ========== 實作 Role/MeleeRole 抽象方法 ==========

    @Override
    public void attack(Role opponent) {
        System.out.println("⚔️✨ " + this.getName() + " 揮動 " + getWeaponType() + " 進行神聖攻擊 " + opponent.getName() + "！");
        opponent.takeDamage(this.getAttackPower());
        restoreHolyPower(5); // 攻擊時恢復少量聖能
    }

    @Override
    public void showSpecialSkill() {
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║ " + this.getName() + " 的特殊技能      ║");
        System.out.println("║ 技能名稱：神聖審判          ║");
        System.out.println("║ 防禦力：+" + defenseCapacity + " 點        ║");
        System.out.println("║ 治療力：+" + healPower + " 點        ║");
        System.out.println("╚═════════════════════════════╝");
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + this.getName() + " 完成了神聖的使命...");
        System.out.println("✨ 聖光環繞身體，緩緩消散。");
        System.out.println("---");
    }

    @Override
    public String getWeaponType() {
        return "聖劍+聖盾";
    }

    @Override
    protected void onMeleePrepare() {
        System.out.println("🙏 低聲祈禱，聖能聚集...");
        System.out.println("📊 聖能值：" + holyPower + "/" + maxHolyPower);
    }

    @Override
    public void afterBattle() {
        System.out.println("🙏 " + this.getName() + " 感謝聖光的庇護。");
        restoreHolyPower(10);
    }

    // ========== 實作 Defendable 介面 (防禦能力) ==========

    @Override
    public void defend() {
        if (!consumeHolyPower(10)) return; // 防禦消耗聖能

        int oldHealth = this.getHealth();
        this.setHealth(this.getHealth() + defenseCapacity);
        System.out.println("🛡️✨ " + this.getName() + " 舉起聖盾，聖光防護罩！");
        System.out.println("💚 恢復 " + defenseCapacity + " 點生命值 (" + oldHealth + " → " + this.getHealth() + ")");
    }

    @Override
    public int getDefenseCapacity() {
        return defenseCapacity;
    }

    // ========== 實作 Healable 介面 (治療能力) ==========

    @Override
    public void heal(Role target) {
        if (!consumeHolyPower(15)) return; // 治療消耗聖能

        int oldHealth = target.getHealth();
        target.setHealth(target.getHealth() + this.healPower);
        System.out.println("💚✨ " + this.getName() + " 施放聖光治療 " + target.getName());
        System.out.println("💚 恢復 " + healPower + " 點生命值 (" + oldHealth + " → " + target.getHealth() + ")");
    }

    @Override
    public int getHealPower() {
        return healPower;
    }

    /**
     * 覆寫 canHeal 預設方法，加入聖能檢查
     */
    @Override
    public boolean canHeal() {
        return getHealPower() > 0 && holyPower >= 15;
    }

    @Override
    public String toString() {
        return super.toString() + ", 防禦力: " + defenseCapacity +
                ", 治療力: " + healPower + ", 聖能: " + holyPower + "/" + maxHolyPower;
    }
}