SET ANLUIT_DESCRIPTIONS=C:\Users\simon\Documents\workspace\anluit\data\descriptions
SET ANLUIT_RULES=C:\Users\simon\Documents\workspace\anluit\data\rules.json
SET ANLUIT_SENTENCE_MODEL=C:\Users\simon\Documents\workspace\anluit\data\en-sent.bin
SET ANLUIT_RESULTS=C:\Users\simon\Documents\workspace\anluit\data\results.json
SET ANLUIT_GENERATED_CLASS=C:\Users\simon\Documents\workspace\anluit\tr\app\src\main\java\anluit\tr\GeneratedTest.java

java -cp nlp\bin;nlp\libs\json-simple-1.1.1.jar;nlp\libs\opennlp-tools-1.6.0.jar anluit.nlp.Main %ANLUIT_DESCRIPTIONS% %ANLUIT_RULES% %ANLUIT_SENTENCE_MODEL% %ANLUIT_RESULTS%
java -cp cg\bin;cg\libs\json-simple-1.1.1.jar anluit.cg.Main %ANLUIT_RESULTS% %ANLUIT_GENERATED_CLASS%

PAUSE