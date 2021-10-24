# tigercard

# Assumptions:
1) Purpose of the system is to calculate the overall Fare for all the input journeys provided as input during a single execution run
2) Journey wise fares are not caculated and adjusted as per daily and weekly fare caps. 
3) If there is problem with atleast one of the input journeys the complete input is failed
4) Daily Fare Cap is used as basis to calculate the Farthest Zone in a journey
5) Seperate Master Data line item for reverse journey is not maintained, cost of Zone 1-2 and Zone 2-1 is assumed to be same. Seperate cost master for reverse journey is not maintained
6) Weekdays are assumed to be from Monday to Friday and Weekend - Saturday & Sunday are assumed. Peak Hour timings are assumed to exist in only 2 categories > Weekend and Weekdays
7) If two interzone travels have the same DailyCapFare, its assumed that Weekly Fare Cap, Off Peak Hours Cost and Peak Hours cost are same
8) Reusability of code has not been considered for JUnit Test Cases. There is redundancy in data initilization code - prefer copy paste over building functions
9) Exception Handking Framework has not been focussed on. Simple "throws" and "printStackTrace" feature have been used. A more sophisticated mechanism can be implemented custom defined exceptions and a single Exception handler for all exceptions
10) File Based Inputs are provided to run the program with inputs and Master Data
11) Junit Test cases have not been written on File Input processing. They have been written only on core Business logic areas of code and important core business logic classes

# Solution
Visitor Design Pattern has been used to design the system. Fare Processing logic is decoupled from the Data structure design around journeys. The single journey data have been organized as a tree structure as Follows:


![image](https://user-images.githubusercontent.com/6222967/138601489-9d04ddf8-3c35-4a36-8a6a-ea84951169a0.png)

A visitor then travels all the nodes in the tree starting with the leaf node and iterates all the way up to the parent node calculating fares, rolling them up, applying fare caps at each level. This design enables swapping in new Algorithms for fare processing logic without need to update any part of the system
