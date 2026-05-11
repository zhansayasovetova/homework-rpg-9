package com.narxoz.rpg.artifact.visitor;

import com.narxoz.rpg.artifact.*;

public class RarityVisitor implements ArtifactVisitor {

    private int commonCount;
    private int rareCount;
    private int legendaryCount;

    public int getCommonCount() {
        return commonCount;
    }

    public int getRareCount() {
        return rareCount;
    }

    public int getLegendaryCount() {
        return legendaryCount;
    }

    public int getRarityScore() {
        return commonCount + rareCount * 2 + legendaryCount * 3;
    }

    @Override
    public void visit(Weapon weapon) {
        classify(weapon.getValue());
    }

    @Override
    public void visit(Potion potion) {
        classify(potion.getValue());
    }

    @Override
    public void visit(Scroll scroll) {
        classify(scroll.getValue());
    }

    @Override
    public void visit(Ring ring) {
        classify(ring.getValue());
    }

    @Override
    public void visit(Armor armor) {
        classify(armor.getValue());
    }

    private void classify(int value) {
        if (value >= 100) {
            legendaryCount++;
        } else if (value >= 50) {
            rareCount++;
        } else {
            commonCount++;
        }
    }
}