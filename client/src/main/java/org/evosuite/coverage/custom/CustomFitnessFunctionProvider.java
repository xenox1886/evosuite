package org.evosuite.coverage.custom;

import org.evosuite.coverage.TestFitnessFactory;
import org.evosuite.testcase.TestFitnessFunction;
import org.evosuite.testsuite.TestSuiteFitnessFunction;

/**
 * @see CustomFitnessFunctionCreator
 */
public interface CustomFitnessFunctionProvider {
    TestSuiteFitnessFunction getNewFitnessFunctionSuite();

    TestFitnessFactory<? extends TestFitnessFunction> getNewFitnessFactory();

    Class<?> getTestFitnessFunctionClass();
}
