/**
 * Healable - 可治療介面
 * 定義角色具備治療能力的契約。
 */
public interface Healable {

    /**
     * 治療目標角色 (抽象方法)
     * @param target 被治療的角色
     */
    void heal(Role target);

    /**
     * 取得治療力 (抽象方法)
     * @return 治療力數值
     */
    int getHealPower();

    /**
     * 檢查是否可以治療 (預設方法)
     * 預設邏輯：治療力大於 0 即可治療。
     */
    default boolean canHeal() {
        return getHealPower() > 0;
    }

    /**
     * 顯示治療資訊 (預設方法)
     */
    default void showHealInfo() {
        System.out.println("💚 治療力：" + getHealPower() + " 點");
    }
}