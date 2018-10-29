package org.evosuite.ga.metaheuristics;

/**
 * Stores the genetic algorithm currently in use, so it can be accessed from outside of EvoSuite
 * (e.g. to register a listener)
 */
public class GeneticAlgorithmContainer {
    private static GeneticAlgorithmContainer instance = new GeneticAlgorithmContainer();
    private GeneticAlgorithmContainer(){}
    public static GeneticAlgorithmContainer get(){
        return instance;
    }

    private GeneticAlgorithm geneticAlgorithm;

    public GeneticAlgorithm getGeneticAlgorithm() {
        return geneticAlgorithm;
    }

    public void setGeneticAlgorithm(GeneticAlgorithm geneticAlgorithm) {
        this.geneticAlgorithm = geneticAlgorithm;
    }
}
