package com.rpg.roles.melee;

import com.rpg.core.Role;

public abstract class MeleeRole extends Role {
    // 近戰角色特有屬性：護甲值
    private int armor;

    public MeleeRole(String name, int health, int attackPower, int armor) {
        super(name, health, attackPower);
        this.armor = armor;
    }

    public int getArmor() { return armor; }
    public void setArmor(int armor) { this.armor = armor; }

    /**
     * 計算防禦減免後的傷害
     */
    public int calculateDefense(int incomingDamage) {
        int actualDamage = Math.max(0, incomingDamage - armor);
        if (armor > 0 && incomingDamage > 0) {
            System.out.println("🛡️  護甲減免 " + Math.min(armor, incomingDamage) + " 點傷害！");
        }
        return actualDamage;
    }

    /**
     * 覆寫 Role 的 takeDamage，加入防禦計算邏輯
     */
    @Override
    public void takeDamage(int damage) {
        int actualDamage = calculateDefense(damage);
        super.takeDamage(actualDamage);
    }

    // 抽象方法：取得武器類型
    public abstract String getWeaponType();

    // 覆寫 prepareBattle：提供近戰共通準備流程
    @Override
    public void prepareBattle() {
        System.out.println("⚔️  " + getName() + " 檢查 " + getWeaponType() + " 的狀態...");
        System.out.println("🛡️  目前護甲值：" + armor);
        onMeleePrepare(); // Hook method
    }

    // 抽象方法：近戰特殊準備
    protected abstract void onMeleePrepare();

    @Override
    public String toString() {
        return super.toString() + ", 護甲值: " + armor;
    }
}