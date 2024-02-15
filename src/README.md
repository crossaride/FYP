Current Issues:

##Arithmetic Exception

This is due to Json Parser "Jackson".
While the library is running in Java 7 or 8, its logger is using Java 9, which caused the issue.
The glassfish 4.1.1 has the incorrect detection of errors, you can try using glassfish 5 for verification. 

##TO-UPDATE:
-- Change the code with the other Json Parser
