package org.evosuite.ga.metaheuristics;


import org.evosuite.ga.Chromosome;

public interface EventEmitter {
    /**
     * This gets called by evo suite after the search is finished
     *
     * @param bestIndividual the best individual
     */
    void onEventCompletion(Chromosome bestIndividual);

    /**
     * Record values after each evolution
     *
     * @param bestIndividual the best individual
     */
    void doRecordAfterEvolution(Chromosome bestIndividual);

}
