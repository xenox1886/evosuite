package org.evosuite.coverage.custom;

import org.evosuite.coverage.TestFitnessFactory;
import org.evosuite.testcase.TestFitnessFunction;
import org.evosuite.testsuite.TestSuiteFitnessFunction;

import java.io.Serializable;

/**
 * @see CustomFitnessFunctionCreator
 */
public interface CustomFitnessFunctionProvider extends Serializable {
    TestSuiteFitnessFunction getNewFitnessFunctionSuite();

    TestFitnessFactory<? extends TestFitnessFunction> getNewFitnessFactory();

    Class<?> getTestFitnessFunctionClass();
}
