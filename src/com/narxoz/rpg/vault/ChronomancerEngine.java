package com.narxoz.rpg.vault;

import com.narxoz.rpg.artifact.Inventory;
import com.narxoz.rpg.artifact.visitor.CombatPowerVisitor;
import com.narxoz.rpg.artifact.visitor.RarityVisitor;
import com.narxoz.rpg.artifact.visitor.TotalValueVisitor;
import com.narxoz.rpg.artifact.visitor.WeightVisitor;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.HeroMemento;
import com.narxoz.rpg.memento.Caretaker;

import java.util.List;

/**
 * Orchestrates the Chronomancer's Vault demo run.
 */
public class ChronomancerEngine {

    public VaultRunResult runVault(List<Hero> party) {
        if (party == null || party.isEmpty()) {
            return new VaultRunResult(0, 0, 0);
        }

        System.out.println("\n--- Chronomancer Engine Run ---");

        Caretaker caretaker = new Caretaker();

        int artifactsAppraised = 0;
        int mementosCreated = 0;
        int restoredCount = 0;

        for (Hero hero : party) {
            System.out.println("\nHero enters vault: " + hero.getName());
            System.out.println("Before snapshot: " + hero);

            HeroMemento snapshot = hero.createMemento();
            caretaker.save(snapshot);
            mementosCreated++;

            System.out.println("Memento saved. Caretaker size: " + caretaker.size());

            Inventory inventory = hero.getInventory();

            TotalValueVisitor valueVisitor = new TotalValueVisitor();
            WeightVisitor weightVisitor = new WeightVisitor();
            CombatPowerVisitor combatVisitor = new CombatPowerVisitor();
            RarityVisitor rarityVisitor = new RarityVisitor();

            inventory.accept(valueVisitor);
            inventory.accept(weightVisitor);
            inventory.accept(combatVisitor);
            inventory.accept(rarityVisitor);

            artifactsAppraised += inventory.size();

            System.out.println("Appraisal result:");
            System.out.println("Artifacts appraised: " + inventory.size());
            System.out.println("Total value: " + valueVisitor.getTotalValue());
            System.out.println("Total weight: " + weightVisitor.getTotalWeight());
            System.out.println("Combat power: " + combatVisitor.getCombatPower());
            System.out.println("Rarity score: " + rarityVisitor.getRarityScore());

            System.out.println("Vault trap changes hero state...");
            hero.takeDamage(20);
            hero.spendMana(10);
            hero.addGold(valueVisitor.getTotalValue());

            System.out.println("After trap: " + hero);

            HeroMemento last = caretaker.undo();
            if (last != null) {
                hero.restoreFromMemento(last);
                restoredCount++;
                System.out.println("Rewind complete: " + hero);
            }
        }

        return new VaultRunResult(artifactsAppraised, mementosCreated, restoredCount);
    }
}