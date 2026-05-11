package com.narxoz.rpg.artifact.visitor;


import com.narxoz.rpg.artifact.*;

public class TotalValueVisitor implements ArtifactVisitor {

    private int totalValue;

    public int getTotalValue() {
        return totalValue;
    }

    @Override
    public void visit(Weapon weapon) {
        totalValue += weapon.getValue();
    }

    @Override
    public void visit(Potion potion) {
        totalValue += potion.getValue();
    }

    @Override
    public void visit(Scroll scroll) {
        totalValue += scroll.getValue();
    }

    @Override
    public void visit(Ring ring) {
        totalValue += ring.getValue();
    }

    @Override
    public void visit(Armor armor) {
        totalValue += armor.getValue();
    }
}