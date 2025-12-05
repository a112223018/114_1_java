/**
 * Defendable - 可防禦介面
 * 定義角色具備防禦能力的契約。
 */
public interface Defendable {

    /**
     * 執行防禦動作 (抽象方法)
     */
    void defend();

    /**
     * 取得防禦力 (抽象方法)
     * @return 防禦力數值
     */
    int getDefenseCapacity();

    /**
     * 檢查是否可以防禦 (預設方法)
     * 預設邏輯：防禦力大於 0 即可防禦。
     */
    default boolean canDefend() {
        return getDefenseCapacity() > 0;
    }
}