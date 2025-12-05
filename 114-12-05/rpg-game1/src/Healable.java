package com.rpg.interfaces;

import com.rpg.core.Role;

public interface Healable {

    void heal(Role target);

    int getHealPower();

    default boolean canHeal() {
        // canHeal() 方法結束
        return getHealPower() > 0;
    } // <-- 新增：canHeal() 的結束大括號

    // 新增：showHealInfo() 預設方法，作為獨立方法存在
    default void showHealInfo() {
        System.out.println("可治療角色 - 治療力: +" + getHealPower() + " 點。");
    }
} //