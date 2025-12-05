package com.rpg.core;

public abstract class Role {
    private String name;
    private int health;
    private int attackPower;

    public Role(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    // getter/setter 方法
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getAttackPower() { return attackPower; }
    public void setHealth(int health) { this.health = health; }
    public boolean isAlive() { return health > 0; }

    // 具體方法：受傷處理
    public void takeDamage(int damage) {
        this.health -= damage;
        // 確保生命值不為負
        if (this.health < 0) this.health = 0;

        System.out.println("💥 " + name + " 受到 " + damage + " 點傷害！目前生命值：" + health);

        if (!isAlive()) {
            onDeath();
        }
    }

    // =======================================================
    // ⚔️ 模板方法 (Template Method) - 鎖定流程
    // =======================================================

    // 🎣 Hook Method 1：回合開始前可選行為 (預設空實作)
    protected void beforeTurn(Role opponent) {}

    // 🎣 Hook Method 2：回合結束後可選行為 (預設空實作)
    protected void afterTurn(Role opponent) {}

    /**
     * Template Method：執行單一戰鬥回合，流程固定且不可變更。
     * @param opponent 攻擊目標
     */
    public final void performTurn(Role opponent) {
        // 檢查雙方狀態
        if (!this.isAlive() || !opponent.isAlive()) {
            System.out.println("❌ " + this.getName() + " 或 " + opponent.getName() + " 已無法戰鬥。");
            return;
        }

        System.out.println("\n--- 戰鬥回合開始: " + this.getName() + " ---");

        // 步驟 1: 戰前準備 (由子類別實作)
        prepareBattle();

        // 步驟 2: 回合前 Hook (子類可選擇性覆寫)
        beforeTurn(opponent);

        // 步驟 3: 執行攻擊 (由子類別實作)
        attack(opponent);

        // 步驟 4: 戰後恢復 (由子類別實作)
        afterBattle();

        // 步驟 5: 回合後 Hook (子類可選擇性覆寫)
        afterTurn(opponent);

        System.out.println("--- 戰鬥回合結束: " + this.getName() + " ---");
    }


    // 抽象方法：必須由子類別實作
    public abstract void attack(Role opponent);
    public abstract void showSpecialSkill();
    public abstract void onDeath();
    public abstract void prepareBattle();
    public abstract void afterBattle();

    @Override
    public String toString() {
        return "角色名稱: " + name + ", 生命值: " + health;
    }
}