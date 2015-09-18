/*
 * Copyright (C) 2005-2015 Alfresco Software Limited.
 *
 * This is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */
package org.alfresco.resources;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asual.lesscss.LessEngine;
import com.asual.lesscss.LessException;

/**
 * Uses the lesscss-engine to validate given CSS files
 * 
 * @author Ray Gauss II
 */
public class LessCssValidator 
{
    private static final Log logger = LogFactory.getLog(LessCssValidator.class);
    
    private static final String RESULT_PASSED = "PASSED";
    private static final String RESULT_ERROR = "ERROR";

    public static void main( String[] args )
    {
        if (args.length == 0)
        {
            System.err.println("USAGE:\n\tjava -jar less-css-validator-1.0-SNAPSHOT.jar cssFilePath ...\n\n"
                    + "or pipe in arguments:\n\n\t"
                    + "find share/src/main/webapp -name '*.css' | xargs java -jar less-css-validator-1.0-SNAPSHOT.jar\n");
        }
        
        LessEngine engine = new LessEngine();
        
        Map<String, String> results = new LinkedHashMap<String, String>(args.length);
        
        for (int i = 0; i < args.length; i++)
        {
            File file = null;
            try
            {
                file = new File(args[i]);
                logger.debug("Validating " + file.toString());
                engine.compile(file);
                results.put(file.toString(), RESULT_PASSED);
            }
            catch (LessException e)
            {
                logger.debug(e.getMessage(), e);
                String key = args[i];
                if (file != null)
                {
                    key = file.toString();
                }
                results.put(key, RESULT_ERROR + ": " + e.getMessage());
            }
        }
        // Output results
        int longestFilePath = 0;
        for (String filePath : results.keySet())
        {
            if (filePath.length() > longestFilePath)
            {
                longestFilePath = filePath.length();
            }
        }
        for (String filePath : results.keySet())
        {
            String paddedPath = String.format("%1$-" + longestFilePath + "s", filePath);
            System.out.println(paddedPath + "\t" + results.get(filePath));
        }
    }
}
