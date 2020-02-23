# CSX42: Assignment 2
## Name: Milind Keshav Parlawar

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in numberPlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

```commandline
ant -buildfile numberPlay/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

```commandline
ant -buildfile numberPlay/src/build.xml all
```

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

#### Use the below command to run the program.

```commandline
ant run -buildfile numberPlay/src/build.xml \
-DinputNumStream="<input file path>" \
-DrunAvgWindowSize="<size of the window for running average calculations>" \
-DrunAvgOutFile="<output file path to which running averages are written>" \
-Dk="<max size of the list containing the top K numbers>" \
-DtopKNumOutFile="<path of output file to which the top K numbers are written>" \
-DnumPeaksOutFile="<path of output file to which the peaks in the number stream are written>"
```
for exammple :
ant run -buildfile numberPlay/src/build.xml -DinputNumStream="input.txt" -DrunAvgWindowSize="3" -DrunAvgOutFile="run-avg-out.txt" -Dk="3" -DtopKNumOutFile="top-k-out.txt" -DnumPeaksOutFile="peaks-out.txt"


-----------------------------------------------------------------------
## Description:
1. Driver class takes 6 arguments - input file, average window size, running average output file, top k numbers, top k output file and peaks output file.
2. Driver validator class validates to check whether provided inputs are valid or not.
3. Number processor registers observers with filter as key.
4. Driver class reads number and pass it to number processor
5. Number processor based on type of number, calls observers.
6. Observers take number and process it and stores it in data structure
7. when process complete event gets called then observers provide command to other classes to write stored data into output files.

Data Structure: 
 I have used BlockingQueue and PriorityQueue. And worst case time complexity is o(logn) 

References:

https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/BlockingQueue.html
https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html
https://www.journaldev.com/20601/java-bufferedwriter 
https://www.journaldev.com/19879/java-bufferedreade

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 02/23/2020


