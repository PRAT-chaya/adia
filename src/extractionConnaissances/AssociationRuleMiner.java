/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extractionConnaissances;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import representation.ItemSet;
import representation.Variable;

/**
 *
 * @author alexy
 */
public class AssociationRuleMiner {
    Map<Set<Variable>, Integer> frequentItemsets = new HashMap();
    List<ItemSet> itemSets = new ArrayList();
    List<ItemSet> singletons = new ArrayList();
    
    public AssociationRuleMiner(Map<Set<Variable>, Integer> frequentItemsets) {
        this.frequentItemsets = frequentItemsets;
        this.buildItemSets(frequentItemsets);
    }
    
    /**
     * Méthode permettant de construire un ArrayList d'objets ItemSet à trois dimensions 
     * Set de Variable, fréquence et confiance
     * @param frequentItemsets 
     */
    private void buildItemSets(Map<Set<Variable>, Integer> frequentItemsets) {
        for(Map.Entry<Set<Variable>, Integer> line : frequentItemsets.entrySet()) {
            if(line.getKey().size() != 1) { itemSets.add(new ItemSet(line.getKey(), line.getValue(), 0.0)); }
            else { singletons.add(new ItemSet(line.getKey(), line.getValue(), 0.0)); }
        }
    }
    
    /**
     * Méthode permettant de retourner un ArrayList d'ItemSet filtré contenant leur fréquence
     * @param freqMin
     * @param confianceMin
     * @return List<Item> Notre liste d'items filtrés
     */
    public List<ItemSet> getValidAssociationRules(int freqMin, int confianceMin) {
       Boolean filtered = false;
       
       for(ItemSet itemSet : itemSets) {
          if(itemSet.getFrequence() < freqMin) { 
              itemSets.remove(itemSet);
              continue;
          }
          
          for(ItemSet singleton : this.singletons) {
            double confiance = itemSet.getFrequence() / singleton.getFrequence();
            
            if(singleton.getFrequence() < freqMin || itemSet.getConfiance() < confianceMin) { 
                singletons.remove(itemSet);
                continue;
            }
            
            itemSet.setConfiance(confiance);
            filtered = true; // On a au moins une association valide
          }
       }
       
       List<ItemSet> allItems = new ArrayList();
       
       if(filtered) {
           allItems.addAll(this.itemSets);
           allItems.addAll(this.singletons);
       }
       
       return allItems;
    }
}
