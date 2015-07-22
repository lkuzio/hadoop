# README #

### How do I get set up? ###

* Setup Hadoop (ver. 2.6.0)
* Setup Pig (ver. 0.14) - IMPORTANT!!! -> compile from source with option -Dhadoop23, don't use ready jars!!!
   - next compile Piggybank
* Setup Hive (ver. 1.1.1)


### RUN Map Reduce example ###

put lotto.csv into HDFS:
hdfs dfs -put /local_path_to/lotto.csv /input

from directory "mapreduce_example" run "mvn clean package",
next "mvn exec:java"


### RUN Pig Example ###

put some pom.xml file('s) into HDFS
example files in pig_example/src/main/resources/example_poms.tar.gz

from directory pig_example run "mvn clean package",
next "sh ./target/appassembler/bin/pigApp"

