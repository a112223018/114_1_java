/**
 * Role - 角色抽象類別 (最高層)
 * 定義所有角色共有的屬性及行為。
 */
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

    // 具體方法：受傷處理 (Template Method 骨架)
    public void takeDamage(int damage) {
        this.health -= damage;
        // 確保生命值不為負
        if (this.health < 0) this.health = 0;

        System.out.println("💥 " + name + " 受到 " + damage + " 點傷害！目前生命值：" + health);

        if (!isAlive()) {
            onDeath();
        }
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