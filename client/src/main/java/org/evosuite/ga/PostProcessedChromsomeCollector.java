package org.evosuite.ga;

import org.evosuite.testcase.TestChromosome;

/**
 * An interface that can be extended by a fitness function, s.t. a chromsome that covers
 * it can be collected for statistics AFTER postProcessing.
 */
public interface PostProcessedChromsomeCollector {
    /**
     * Collect a chromosome after post processing
     *
     * @param chromosome the chromosome to collect
     * @param index      the index of the chromosome in its containing suite
     */
    void collectPostProcessedChromosome(TestChromosome chromosome, int index);
}
