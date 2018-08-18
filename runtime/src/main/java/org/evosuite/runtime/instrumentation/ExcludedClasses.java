/**
 * Copyright (C) 2010-2018 Gordon Fraser, Andrea Arcuri and EvoSuite
 * contributors
 *
 * This file is part of EvoSuite.
 *
 * EvoSuite is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3.0 of the License, or
 * (at your option) any later version.
 *
 * EvoSuite is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with EvoSuite. If not, see <http://www.gnu.org/licenses/>.
 */
package org.evosuite.runtime.instrumentation;

import org.evosuite.PackageInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Maven shade plugin replaces strings (https://issues.apache.org/jira/browse/MSHADE-156)
 * To avoid this problem, we have to store the strings in a file and read them here.
 *
 *
 * Created by gordon on 19/03/2016.
 */
public class ExcludedClasses {

    private static boolean classesLoaded = false;

    public static List<String> excludedClasses = new ArrayList<>();

    private static void loadExcludedClassNames() {
        if(classesLoaded)
            return;

        InputStream excludedClassesStream = ExcludedClasses.class.getClassLoader().getResourceAsStream("excluded.classes");
        classesLoaded = true;
        try {
            //Construct BufferedReader from InputStreamReader
            BufferedReader br = new BufferedReader(new InputStreamReader(excludedClassesStream));

            String line = null;
            while ((line = br.readLine()) != null) {
                excludedClasses.add(line);
            }

            br.close();
        } catch(IOException e) {

        }
    }
    /**
     * <p>
     * getPackagesShouldNotBeInstrumented
     * </p>
     *
     * @return the names of class packages EvoSuite is not going to instrument
     */
    static List<String> getPackagesShouldNotBeInstrumented() {
        //explicitly blocking client projects such as specmate is only a
        //temporary solution, TODO allow the user to specify
        //packages that should not be instrumented

        List<String> list = new ArrayList<>();
        loadExcludedClassNames();
        list.add(PackageInfo.getEvoSuitePackage());
        list.addAll(excludedClasses);
        return list;
    }
}
