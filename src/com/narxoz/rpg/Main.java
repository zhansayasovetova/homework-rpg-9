package com.narxoz.rpg;

import com.narxoz.rpg.artifact.*;
import com.narxoz.rpg.artifact.visitor.*;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 9 Demo: Visitor + Memento ===");

        // ---------- INVENTORY 1 ----------
        Inventory warriorInventory = new Inventory();
        warriorInventory.addArtifact(new Weapon("Sun Blade", 120, 8, 25));
        warriorInventory.addArtifact(new Armor("Dragon Plate", 90, 15, 18));
        warriorInventory.addArtifact(new Potion("Healing Potion", 35, 1, 30));

        // ---------- INVENTORY 2 ----------
        Inventory mageInventory = new Inventory();
        mageInventory.addArtifact(new Scroll("Scroll of Time", 80, 1, "Time Freeze"));
        mageInventory.addArtifact(new Ring("Moon Ring", 110, 1, 20));
        mageInventory.addArtifact(new Potion("Mana Potion", 40, 1, 15));

        // ---------- HEROES ----------
        Hero warrior = new Hero("Arden", 100, 30, 20, 12, 50, warriorInventory);
        Hero mage = new Hero("Lyra", 75, 40, 12, 5, 80, mageInventory);

        System.out.println("\n--- Party before vault ---");
        System.out.println(warrior);
        System.out.println(mage);

        // ---------- VISITORS ----------
        TotalValueVisitor valueVisitor = new TotalValueVisitor();
        WeightVisitor weightVisitor = new WeightVisitor();
        CombatPowerVisitor combatVisitor = new CombatPowerVisitor();
        RarityVisitor rarityVisitor = new RarityVisitor();

        // apply visitors
        warrior.getInventory().accept(valueVisitor);
        warrior.getInventory().accept(weightVisitor);
        warrior.getInventory().accept(combatVisitor);
        warrior.getInventory().accept(rarityVisitor);

        mage.getInventory().accept(valueVisitor);
        mage.getInventory().accept(weightVisitor);
        mage.getInventory().accept(combatVisitor);
        mage.getInventory().accept(rarityVisitor);

        // ---------- PRINT VISITOR RESULTS ----------
        System.out.println("\n--- Visitor Results ---");
        System.out.println("Total Value: " + valueVisitor.getTotalValue());
        System.out.println("Total Weight: " + weightVisitor.getTotalWeight());
        System.out.println("Combat Power: " + combatVisitor.getCombatPower());
        System.out.println("Rarity Score: " + rarityVisitor.getRarityScore());

        // ---------- ENGINE ----------
        ChronomancerEngine engine = new ChronomancerEngine();
        VaultRunResult result = engine.runVault(List.of(warrior, mage));

        System.out.println("\n--- Party after rewind ---");
        System.out.println(warrior);
        System.out.println(mage);

        System.out.println("\n--- Final Result ---");
        System.out.println(result);
    }
}