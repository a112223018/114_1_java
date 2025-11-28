public class Role {
    private String name;
    private int health;
    private int attackPower;

    // The constructor is partially obscured in the full image,
    // but based on the usage in the zoomed image (line 11) and context:
    public Role(String name, int health, int attackPower) {
        this.name = name; // Assuming 'name' is initialized here
        this.health = health;
        this.attackPower = attackPower;
    }

    // // 取得角色名称 (Get Role Name)
    public String getName() {
        return name;
    }

    // // 取得生命值 (Get Health)
    public int getHealth() {
        return health;
    }

    // // 取得攻击力 (Get Attack Power)
    public int getAttackPower() {
        return attackPower;
    }

    // // 设定生命值 (Set Health)
    public void setHealth(int health) {
        this.health = health;
    }

    // // 检查角色是否存活 (Check if role is alive)
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public String toString() {
        return "角色名称: " + name + ", 生命值: " + health;
    }
}