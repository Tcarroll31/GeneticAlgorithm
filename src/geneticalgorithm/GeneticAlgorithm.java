/*
 * This is Project 1 for CS 1181 Summer Class. This method represents a file of 
 * items that correspond relating to a sequence of seven individual Chromosomes.
 * We are wanting to find the best possible set for the items/chromosomes and
 * we then find the best 10 chromosomes by the end of the Project.
 */
package geneticalgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * Torey c TA: John Instructor: Prof. Cheatham
 */
public class GeneticAlgorithm {

    private static Random rng = new Random();

    /**
     * This method 'readData' it reads the items.txt file, Scans through the
     * file creating an Item object for each item in file and adding it to the
     * ArrayList
     *
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public static ArrayList<Item> readData(String fileName) throws FileNotFoundException {
        ArrayList<Item> fileItems = new ArrayList<>();
        // Creates a scanner to scan through the file
        Scanner scan = new Scanner(new File("items.txt"));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Scanner newScan = new Scanner(line).useDelimiter(", ");
            // Stores the values to their respected values
            String label = newScan.next();
            double weight = newScan.nextDouble();
            int value = newScan.nextInt();
            // adds the items to the ArrayList w/ creating Items for each one
            fileItems.add(new Item(label, weight, value));
        }
        return fileItems;
    }

    /**
     * This method iterates through the Items, creates Chromosome objects and
     * adds to the initialPopulation. Returns the initial population
     *
     * @param items
     * @param populationSize
     * @return
     */
    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize) {
        ArrayList<Chromosome> initializePopulation = new ArrayList<>();
        // Iterate through Items
        // Creating Chromosomes objects
        // Add to initializePopulation
        for (int i = 0; i < populationSize; i++) {
            Chromosome c = new Chromosome(items);
            initializePopulation.add(c);
        }
        return initializePopulation;
    }

    public static void main(String[] args) throws FileNotFoundException {
        final int POP_SIZE = 10;
        final int NUM_EPOCHS = 20;
        // read in the fileItems from the datafile
        ArrayList<Item> items = readData("items.txt");
        // create the fileItems based on the data in the file
        ArrayList<Chromosome> nextGen = new ArrayList<>();
        ArrayList<Chromosome> genCopy = new ArrayList<>();

        // sets the population ArrayList to be the inilitalizepop method
        ArrayList<Chromosome> population = initializePopulation(items, POP_SIZE);

        // Do the evolution
        for (int i = 0; i < NUM_EPOCHS; i++) { // each generation

            // do crossover
            // randomly pair off the existing Chromosomes in the population
            Collections.shuffle(population);

            // in a loop, take two at a time from the population arraylist and make them the parents
            try {
                for (int j = 0; j < population.size(); j++) {
                    // Creates Parent 1
                    Chromosome parent1 = population.get(j);
                    // Creates Parent 2
                    Chromosome parent2 = population.get(j + 1);
                    // Creates Parent 3
                    Chromosome child = parent1.crossover(parent2);
                    // Adds the parents and child to the arraylists
                    genCopy.add(parent1);
                    genCopy.add(parent2);
                    nextGen.add(child);
                }
            } catch (IndexOutOfBoundsException e) {

            }

            // do mutation
            // in a different loop
            // generate a random number between 1 and 10 and if it's equal to 1
            // call population.get(j).mutate();
            for (int j = 0; j < population.size(); j++) {
                int random = (int) (rng.nextInt(10) + 1);
                if (random == 1) {
                    genCopy.get(j).mutate();
                }
            }
            // Clear Population
            population.clear();
            // for loop to iterate through nextGen to grab best 10
            // add to population
            nextGen.addAll(genCopy);
            Collections.sort(nextGen);
            
                for (int j = 0; j < POP_SIZE; j++) {
                    population.add(nextGen.get(j));

                }
            
        }
        // Re-sorts the population
        Collections.sort(population);

        // Prints out the first item
        System.out.println(population.get(0));
    }
}
