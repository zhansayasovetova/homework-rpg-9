package com.narxoz.rpg.artifact.visitor;

import com.narxoz.rpg.artifact.*;

public class WeightVisitor implements ArtifactVisitor {

    private int totalWeight;

    public int getTotalWeight() {
        return totalWeight;
    }

    @Override
    public void visit(Weapon weapon) {
        totalWeight += weapon.getWeight();
    }

    @Override
    public void visit(Potion potion) {
        totalWeight += potion.getWeight();
    }

    @Override
    public void visit(Scroll scroll) {
        totalWeight += scroll.getWeight();
    }

    @Override
    public void visit(Ring ring) {
        totalWeight += ring.getWeight();
    }

    @Override
    public void visit(Armor armor) {
        totalWeight += armor.getWeight();
    }
}