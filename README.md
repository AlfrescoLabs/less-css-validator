Overview
--------

Simple project which uses the lesscss-engine to validate given CSS files.

Building
-----

mvn clean install

Usage
-----

    java -jar less-css-validator-1.0-SNAPSHOT.jar cssFilePath ...

or pipe in arguments:

    find ~/Share/share/src/main/webapp -name '*.css' | xargs java -jar target/less-css-validator-1.0-SNAPSHOT.jar
