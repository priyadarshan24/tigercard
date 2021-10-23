# tigercard

Assumptions:
1) If there is problem with atleast one of the input journeys the complete input is failed
2) Daily Fare Cap is used as basis to calculate the Farthest Zone in a journey
3) Seperate Master Data line item for reverse journey is not maintained, cost of Zone 1-2 and Zone 2-1 is assumed to be same. Seperate cost master for reverse journey is not maintained
4) Weekdays are assumed to be from Monday to Friday and Weekend - Saturday & Sunday are assumed. Peak Hour timings are assumed to exist in only 2 categories > Weekend and Weekdays
5) If two interzone travels have the same DailyCapFare, its assumed that Weekly Fare Cap, Off Peak Hours Cost and Peak Hours cost are same
