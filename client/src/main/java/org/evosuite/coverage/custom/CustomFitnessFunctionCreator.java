package org.evosuite.coverage.custom;

import org.evosuite.coverage.TestFitnessFactory;
import org.evosuite.testcase.TestFitnessFunction;
import org.evosuite.testcase.execution.TestRunnable;
import org.evosuite.testsuite.TestSuiteFitnessFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates the suite, factory and retrieves the class of the fitness function.
 * This is meant to be used with the CUSTOM {@link org.evosuite.Properties.Criterion}, s.t.
 * EvoSuite can be extended without including the source into one's own project.
 */
public class CustomFitnessFunctionCreator {
    private static CustomFitnessFunctionCreator ourInstance = new CustomFitnessFunctionCreator();
    private static final Logger logger = LoggerFactory.getLogger(TestRunnable.class);

    public static CustomFitnessFunctionCreator getInstance() {
        return ourInstance;
    }

    private CustomFitnessFunctionCreator() {
    }

    private CustomFitnessFunctionProvider customFitnessFunctionProvider;


    public TestSuiteFitnessFunction getNewFitnessFunctionSuite() {
        if (customFitnessFunctionProvider == null) {
            throw new IllegalStateException("CustomFitnessFunctionProvider not yet set");
        }
        return customFitnessFunctionProvider.getNewFitnessFunctionSuite();
    }

    public TestFitnessFactory<? extends TestFitnessFunction> getNewFitnessFactory() {
        if (customFitnessFunctionProvider == null) {
            throw new IllegalStateException("CustomFitnessFunctionProvider not yet set");
        }
        return customFitnessFunctionProvider.getNewFitnessFactory();
    }

    public Class<?> getTestFitnessFunctionClass() {
        if (customFitnessFunctionProvider == null) {
            throw new IllegalStateException("CustomFitnessFunctionProvider not yet set");
        }
        return customFitnessFunctionProvider.getTestFitnessFunctionClass();
    }

    public void setCustomFitnessFunctionProvider(CustomFitnessFunctionProvider customFitnessFunctionProvider) {
        this.customFitnessFunctionProvider = customFitnessFunctionProvider;
    }
}
