# CodingTask2

#### Processing time (Bearbeitungszeit): 
2h (with documentation)

#### Assumption (Annahme):
- The intervals are int tuples.
- The Interval-class is only used within the same package as where it is used from. 
  (Done this for simplicity reason, to avoid using getter and setter, although the could have been used to ensure that the start and end values are always valid).
  (Currently this is only done in the constructor).
- The Interval objects are initiated by using the default constructor. (Which, as already mentioned, will make sure that the values are on the correct position).
- The input list does not contain Interval objects which are null.

#### Solution Idea (Lösungsidee):
I usually draw and write on a blank peace of paper before I start programming, because this helps me thinking.<br>
I started writing down some simple intervals with all the numbers they are containing:<br>
<pre>
	 Interval 1 - [4, 8]:		[ 4, 5, 6, 7, 8 ]
	 Interval 2 - [6, 10]:		      [ 6, 7, 8, 9, 10 ]</pre>
I specifically placed them as shown above so I get a good understanding of the problem, and then the answer was clear.
First of all I knew that they had to be sorted to make sure that the ones with the smallest start value are always on the top position (Interval 1). <br>
Furthermore it was clear to me that I had to check if the Interval 2 (the one on the bottom position) is overlapping with 1, 
thus Interval 1's end has to be greater than Intervals 2's start. 
If the end of Interval 2 is greater than the end of Interval 1 the value has to be saved, since these intervals are going to be merged (therefore the buffer). <br>
If an Interval, which does not overlap emerges, I thought that the buffered (and merged) interval from before has to be saved to an output list.
And that the new not-overlapping interval could be stored in Interval 1's position (in the buffer) and used to be compared to the rest. 
This approach/solution is only made possible by sorting the input list beforehand.

#### Notes:
I usually try to make my code as easy to understand as possible.
I try achieving this by:
1. Using multiple comments and examples if I feel it helps others understanding "what" a function does, or how they are linked together (in case of classes).
2. Explain fundamentals of things where I believe not everybody knows about them straight from the start.
3. I purposely try to avoid using fancy coding styles, because for 1 the compiler is going to optimize lots of it anyways, and 2 it is just hard to read or understand.
4. I try to use self-explaining variable names, even if it might be a very long variable name in the end. :D

#### Algorithm of the MERGE function:
The general idea of the algorithm is summarized in the following list:
1. Sort the intervals in ascending order by its start variable.
2. Save the first interval of the input list into a buffer.
3. Run a loop starting from the 2nd element of the list. For each interval do the following:
	* Check if the current interval is overlapping with the buffer interval. If it is overlapping change the end of the buffer interval accordingly.<br>
	    Conditions:<br>	
		* Condition 1: The end of Interval 1 (buffer interval) has to be greater than the start of Interval 2 (current interval).
		* Condition 2: The end of Interval 1 (buffer interval) has to be smaller than the end of Interval 2 (current interval).
	* If the current interval is not overlapping with the buffer interval, add the buffer interval to the output list and afterwards overwrite the buffer interval with the current interval.
	* In the last iteration of the loop add the buffer interval to the output.
	
#### Answers:
**Question 1:** Wie ist die Laufzeit Ihres Programms?
O(N x Log N) due to the usage of Javas mergesort algorithm. (The complexity of the merge part is linear).

**Question 2:** Wie kann die Robustheit sichergestellt werden, vor allem auch mit Hinblick auf sehr große Eingaben?		
* By making sure that the intervals are always defined correctly (start has to be smaller or equal to end).
* By using a dynamic array (ArrayList) as output parameter which grows by 50% of its size each time (starting from 10).
* By making sure that the input list is not empty.
* By making sure that no Interval object is null (I recommand doing this before calling my merge function).
			
**Question 3:** Wie verhält sich der Speicherverbrauch Ihres Programms?
* Currently for the input list an dynamic array (ArrayList) of Interval objects are used.
* An Interval object holds 2 integer. (For N elements it would be 2x32-bitxN + bits for overhead). 
* The dynamic array (ArrayList) is initiated with 10 slots and increased by 50% when the last slot is filled.
* The output list is an dynamic array (ArrayList) as well. 
			
#### Misc:			
This was a fun thing to do. Time to get schwifty.
Thanks. :D
