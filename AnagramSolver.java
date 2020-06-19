// Shivangi Khanna

// AnagramSolver prints anagrams for a given word or phrase. 
// It takes a phrase or a word and a number as input from the user.
// The number signifies the maximum number of words in the anagram. 
// 0 means the maximum number of anagrams are printed.

import java.util.*;

public class AnagramSolver {
   
   private List<String> myDictionary;
   private Map<String, LetterInventory> inventoryMap;
   
   // Constructs AnagramSolver object from the passed dictionary(list of words).
   // It computes the letter inventories in advance. 
   public AnagramSolver(List<String> dictionary) {
      myDictionary = dictionary;
      inventoryMap = new HashMap<String, LetterInventory>();
      for(String word : dictionary) {
         LetterInventory inventory = new LetterInventory(word);
         inventoryMap.put(word, inventory);
      }
   }
   
   // Prints the max number, entered by the user, of anagrams of the word entered
   // by the user from a dictionary of relevant words, in a sorted order. 
   // Throws an IllegalArgumentException if max is less than 0.
   // Prints all the anagrams if max is zero.
   public void print(String text, int max) {
      if(max < 0) {
         throw new IllegalArgumentException();
      }
      List<String> letterList = new ArrayList<String>();
      LetterInventory inventoryLetter = new LetterInventory(text);
      List<String> wordDictionary = new ArrayList<String>();
      for(String word : myDictionary) {
         LetterInventory inventoryWord = inventoryMap.get(word);
         if(inventoryLetter.subtract(inventoryWord) != null) {
            wordDictionary.add(word);
         }
      }
      print(inventoryLetter, wordDictionary, letterList, max);
   }
   
   // Only used when all the letters are selected from the text.
   // Walksthrough the inventory to find anagrams for the word or phrase.
   private void print(LetterInventory inventoryLetter, List<String> wordDictionary,
                      List<String> letterList, int max) {
      if(inventoryLetter.isEmpty()) {
         System.out.println(letterList);
      } else if(max >= (letterList.size() + 1) || max == 0) {
         for(String word : wordDictionary) {
            LetterInventory wordInventory = inventoryMap.get(word);
            LetterInventory subtracted = inventoryLetter.subtract(wordInventory);
            if(subtracted != null) {
               letterList.add(word);
               print(subtracted, wordDictionary, letterList, max);
               letterList.remove(letterList.size() - 1);
            }
         }
      }
   }
}