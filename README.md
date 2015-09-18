Overview
--------

Simple project which uses the lesscss-engine to validate given CSS files.

Usage
-----

    java -jar less-css-validator-1.0-SNAPSHOT.jar cssFilePath ...

or pipe in arguments:

    find share/src/main/webapp -name '*.css' | xargs java -jar less-css-validator-1.0-SNAPSHOT.jar


Building
-----

mvn clean install