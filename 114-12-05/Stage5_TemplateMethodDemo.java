/**
 * Stage5_Templatemethoddemo.java
 * 模板方法模式範例：模擬遊戲角色戰鬥流程
 */

// 抽象父類別：定義戰鬥流程的骨架
abstract class Character {
    private final String name;
    private int health;

    public Character(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        if (isAlive()) {
            this.health -= damage;
            if (this.health < 0) {
                this.health = 0;
            }
            System.out.println("  > " + name + " 受到 " + damage + " 點傷害，剩餘生命值：" + health);
        }
    }

    // =======================================================
    // ⚔️ 1. 模板方法 (Template Method) - 使用 final 鎖定流程
    // =======================================================
    public final void performBattle(Character opponent) {
        System.out.println("\n--- 戰鬥回合開始: " + this.name + " vs. " + opponent.getName() + " ---");

        // 步驟 1：戰鬥前檢查 (具體方法)
        if (!preBattleCheck(opponent)) {
            System.out.println("--- 戰鬥回合結束 (檢查未通過) ---");
            return;
        }

        // 步驟 2：戰鬥準備 (抽象方法) - 由子類別實作細節
        prepare();

        // 步驟 3：攻擊前行為 (Hook Method) - 可選步驟
        beforeAttack(opponent);

        // 步驟 4：執行攻擊 (抽象方法) - 由子類別實作細節
        attack(opponent);

        // 步驟 5：攻擊後行為 (Hook Method) - 可選步驟
        afterAttack(opponent);

        System.out.println("--- 戰鬥回合結束 ---");
    }

    // =======================================================
    // 🔨 2. 具體方法 (Concrete Method) - 固定邏輯
    // =======================================================
    private boolean preBattleCheck(Character opponent) {
        if (!this.isAlive() || !opponent.isAlive()) {
            System.out.println("  > 檢查失敗：其中一方已無法戰鬥。");
            return false;
        }
        System.out.println("  > 檢查通過：" + this.name + " 準備戰鬥。");
        return true;
    }

    // =======================================================
    // ✍️ 3. 抽象方法 (Abstract Method) - 必須實作
    // =======================================================
    protected abstract void prepare();
    protected abstract void attack(Character opponent);

    // =======================================================
    // 🎣 4. 鉤子方法 (Hook Method) - 可選實作
    // =======================================================
    // 預設什麼都不做，子類別可選擇性覆寫來加入特殊行為
    protected void beforeAttack(Character opponent) {
        // System.out.println(name + " 在攻擊前沒有特殊動作。"); // 保持靜默
    }

    protected void afterAttack(Character opponent) {
        // System.out.println(name + " 在攻擊後沒有特殊動作。"); // 保持靜默
    }
}

// 具體子類別：戰士
class Warrior extends Character {
    public Warrior(String name, int health) {
        super(name, health);
    }

    @Override
    protected void prepare() {
        System.out.println("  * 戰士準備: " + getName() + " 緊握劍柄，準備衝鋒。");
    }

    @Override
    protected void attack(Character opponent) {
        int damage = 20;
        System.out.println("  * 戰士攻擊: " + getName() + " 揮舞巨劍，猛烈劈向 " + opponent.getName() + "！");
        opponent.takeDamage(damage);
    }

    // 覆寫 Hook Method：戰士特有的攻擊前行為 (喊戰吼)
    @Override
    protected void beforeAttack(Character opponent) {
        System.out.println("  🔊 " + getName() + "：「受死吧！」(戰吼提高士氣)");
    }

    // 覆寫 Hook Method：戰士特有的攻擊後行為 (檢查裝備)
    @Override
    protected void afterAttack(Character opponent) {
        System.out.println("  🛡️ " + getName() + " 檢查了裝備耐久度。");
    }
}

// 具體子類別：法師
class Mage extends Character {
    public Mage(String name, int health) {
        super(name, health);
    }

    @Override
    protected void prepare() {
        System.out.println("  * 法師準備: " + getName() + " 開始吟唱複雜的咒語...");
    }

    @Override
    protected void attack(Character opponent) {
        int damage = 25;
        System.out.println("  * 法師攻擊: " + getName() + " 釋放一道火焰魔法，直擊 " + opponent.getName() + "！");
        opponent.takeDamage(damage);
    }

    // 法師不需要覆寫 Hook Method，因此沿用父類別的空實作
    // 保持靜默，不需加入不必要的程式碼
}


// 執行範例
public class Stage5_TemplateMethodDemo {
    public static void main(String[] args) {
        // 創建角色
        Warrior aragorn = new Warrior("亞拉岡", 100);
        Mage gandalf = new Mage("甘道夫", 80);
        Warrior boromir = new Warrior("波羅莫", 10); // 低血量的角色

        // 範例 1：正常戰鬥 (Warrior vs. Mage)
        aragorn.performBattle(gandalf);

        // 範例 2：另一個回合 (Mage vs. Warrior)
        gandalf.performBattle(aragorn);

        // 範例 3： Hook Method 的差異
        // 注意看：法師的回合沒有「戰吼」和「檢查裝備」的輸出，因為他沒有覆寫 Hook Method。

        // 範例 4： 具體方法的應用 (戰鬥前檢查)
        // 亞拉岡攻擊低血量的波羅莫，將其擊倒
        aragorn.performBattle(boromir);

        // 範例 5： 流程控制被鎖定 (final 的作用)
        // 甘道夫再次攻擊已陣亡的波羅莫
        gandalf.performBattle(boromir); // 戰鬥前檢查會失敗，後續步驟不會執行
    }
}