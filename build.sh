javac -d out ./src/ds/garden/*.java
jar cvfm garden.jar ./MANIFEST.MF -C out/ .