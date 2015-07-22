A = load 'hdfs://localhost:9000/input/' using org.apache.pig.piggybank.storage.XMLLoader('dependency') as (x:chararray);
B = foreach A generate org.apache.pig.piggybank.evaluation.xml.XPath(x, 'dependency/artifactId') as dependency,
org.apache.pig.piggybank.evaluation.xml.XPath(x, 'dependency/version') ;
C = group B by dependency;
D= foreach C generate COUNT(B), group;
E = order D by $1;
store E into 'hdfs://localhost:9000/output/pomAnalyzer';
