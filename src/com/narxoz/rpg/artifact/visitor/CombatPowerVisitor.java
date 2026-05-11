package com.narxoz.rpg.artifact.visitor;

import com.narxoz.rpg.artifact.*;

public class CombatPowerVisitor implements ArtifactVisitor {

    private int combatPower;

    public int getCombatPower() {
        return combatPower;
    }

    @Override
    public void visit(Weapon weapon) {
        combatPower += weapon.getAttackBonus();
    }

    @Override
    public void visit(Potion potion) {
        combatPower += potion.getHealing() / 2;
    }

    @Override
    public void visit(Scroll scroll) {
        combatPower += 10;
    }

    @Override
    public void visit(Ring ring) {
        combatPower += ring.getMagicBonus();
    }

    @Override
    public void visit(Armor armor) {
        combatPower += armor.getDefenseBonus();
    }
}