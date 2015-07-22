# README #

### How do I get set up? ###

* Setup Hadoop (ver. 2.6.0)
* Setup Pig (ver. 0.14) - IMPORTANT!!! -> compile from source with option -Dhadoop23, don't use ready jars!!!
   - next compile Piggybank
* Setup Hive (ver. 1.1.1)


### RUN Map Reduce example ###

put lotto.csv into HDFS:
hdfs dfs -put /local_path_to/lotto.csv /input


### RUN Pig Example ###

put some pom.xml file('s) into HDFS

