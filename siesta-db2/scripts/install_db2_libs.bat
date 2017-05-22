set DB2_LIB=%1

call mvn install:install-file ^
   -DlocalRepositoryPath=%HOME%\.m2\repository ^
   -DcreateChecksum=true ^
   -Dpackaging=jar ^
   -Dfile=%DB2_LIB%\db2jcc4.jar ^
   -DgroupId=com.ibm.db2 ^
   -DartifactId=db2jcc4 ^
   -Dversion=11.1.0

call mvn install:install-file ^
   -DlocalRepositoryPath=%HOME%\.m2\repository ^
   -DcreateChecksum=true ^
   -Dpackaging=zip ^
   -Dfile=%DB2_LIB%\sqlj4.zip ^
   -DgroupId=com.ibm.db2 ^
   -DartifactId=sqlj4 ^
   -Dversion=11.1.0
