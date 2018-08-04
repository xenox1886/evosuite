package org.evosuite.coverage.custom;

import org.evosuite.coverage.TestFitnessFactory;
import org.evosuite.testcase.TestFitnessFunction;
import org.evosuite.testsuite.TestSuiteFitnessFunction;

import java.io.*;

/**
 * Creates the suite, factory and retrieves the class of the fitness function.
 * This is meant to be used with the CUSTOM {@link org.evosuite.Properties.Criterion}, s.t.
 * EvoSuite can be extended without including the source into one's own project.
 */
public class CustomFitnessFunctionCreator {
    private static CustomFitnessFunctionCreator ourInstance = null;
    public static final File PROVIDER = new File("provider.ser");

    public synchronized static CustomFitnessFunctionCreator getInstance() {
        if (ourInstance == null) {
            ourInstance = new CustomFitnessFunctionCreator();
        }
        return ourInstance;
    }

    private CustomFitnessFunctionCreator() {
    }

    private CustomFitnessFunctionProvider customFitnessFunctionProvider;


    public TestSuiteFitnessFunction getNewFitnessFunctionSuite() {
        setCustomFitnessFunctionProvider();
        if (customFitnessFunctionProvider == null) {
            throw new IllegalStateException("CustomFitnessFunctionProvider not yet set");
        }
        return customFitnessFunctionProvider.getNewFitnessFunctionSuite();
    }

    public TestFitnessFactory<? extends TestFitnessFunction> getNewFitnessFactory() {
        setCustomFitnessFunctionProvider();
        if (customFitnessFunctionProvider == null) {
            throw new IllegalStateException("CustomFitnessFunctionProvider not yet set");
        }
        return customFitnessFunctionProvider.getNewFitnessFactory();
    }

    public Class<?> getTestFitnessFunctionClass() {
        setCustomFitnessFunctionProvider();
        if (customFitnessFunctionProvider == null) {
            throw new IllegalStateException("CustomFitnessFunctionProvider not yet set");
        }
        return customFitnessFunctionProvider.getTestFitnessFunctionClass();
    }

    /**
     * Set the provider by passing a serialized file. Needs to be done this way,
     * because directly accessing creator might not be possible (due to multiple threads, VMs, or
     * something along those lines)
     */
    private void setCustomFitnessFunctionProvider() {
        if (this.customFitnessFunctionProvider == null) {
            this.customFitnessFunctionProvider = deserializeProvider();
        }
    }

    /**
     * Deserialize the provider file.
     *
     * @return the provider
     * @throws IllegalArgumentException if the file doesn't exist or deserialization leads to an error otherwise
     */
    private CustomFitnessFunctionProvider deserializeProvider() throws IllegalArgumentException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PROVIDER))) {
            Object obj = ois.readObject();
            if (!(obj instanceof CustomFitnessFunctionProvider)) {
                throw new IllegalArgumentException("Deserialized file '" + PROVIDER + "' isn't an instance of CustomFitnessFunctionProvider");
            }
            return (CustomFitnessFunctionProvider) obj;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File '" + CustomFitnessFunctionCreator.PROVIDER + "' not found", e);
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Error while deserializing file '" + PROVIDER + "'", e);
        }
    }
}
