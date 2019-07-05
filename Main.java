import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author	Marcus Korinth
 * @version	1.0
 * @since	2019-07-05
 */
public class Main {

	public static void main(String[] args) {
		Interval A = new Interval(25, 30);
		Interval B = new Interval(2, 19);
		Interval C = new Interval(14, 23);
		Interval D = new Interval(4, 8);
		Interval E = new Interval(33, 65);
		
		ArrayList<Interval> input = new ArrayList<Interval>();
		input.add(A);
		input.add(B);
		input.add(C);
		input.add(D);
		input.add(E);
		
		System.out.println("Input:\t"	+ input.toString());
		
		ArrayList<Interval> output = merge(input);
		System.out.println("Output:\t"	+ output.toString());
	}
	
	/**
	 * This method merges a list of overlapping intervals. Non overlapping intervals remain untouched.
	 * <p>
	 * Example: <br>
	 * Input: [4, 8], [6, 10], [25, 30] <br>
	 * Output: [4, 10], [25, 30]
	 * 
	 * @param inputIntervalList A list of Interval objects which should be merged.
	 * @return ArrayList A list of merged Interval objects.
	 */
	public static ArrayList<Interval> merge(ArrayList<Interval> inputIntervalList) {
		Interval bufferInterval;
		ArrayList<Interval> output = new ArrayList<Interval>();
				
		// Check if the input list is empty.
		if(inputIntervalList.isEmpty()) {
			return output;
		}
		
		// Check if the input list contains only 1 element.
		if(inputIntervalList.size() == 1) {
			return output = inputIntervalList;
		}
		
		
		// Step 1: (see readme)
		// Sort the intervals in increasing order to make sure that the interval with the lowest start variable is on the first position.
		inputIntervalList.sort(new Comparator<Interval>() {
			public int compare (Interval interval_A, Interval interval_B) {
				return interval_A.start - interval_B.start;
			}
		});
		
		// Step 2: (see readme)
		// Save the first interval to the buffer since it is used to be compared in the beginning.
		bufferInterval = inputIntervalList.get(0);
		
		// Step 3: (see readme)
		// Loop through all intervals (except the first one) and merge them if necessary.
		for(int i = 1; i < inputIntervalList.size(); i++) {
			Interval currentInterval = inputIntervalList.get(i);
			
			/* If the current interval is overlapping with the buffer interval, change the end variable in the buffer.
			 * Example:		Interval 1 - [4, 8]:		[ 4, 5, 6, 7, 8 ]
			 *				Interval 2 - [6, 10]:		      [ 6, 7, 8, 9, 10 ]
			 * From the example we can see that the sorting before helped a lot. Now we only have to check for 2 conditions in oder to tell if the intervals are overlapping:
			 * 	- Condition 1: The end of Interval 1 has to be greater than the start of Interval 2. 8 > 6
			 * 	- Condition 2: The end of Interval 1 has to be smaller than the end of Interval 2. 8 < 10
			 */	
			if((bufferInterval.end > currentInterval.start) && // Making sure that the current interval starts within the bounds of the buffer interval.
					(bufferInterval.end < currentInterval.end)) { // Making sure that the current interval ends after the buffer interval.
				bufferInterval.end = currentInterval.end; // Save the end of the current interval in the buffer since they were overlapping.
			} 
			// If the current interval is not overlapping with the buffer interval, add the buffer to the output list and replace the buffer with the current.
			else if (bufferInterval.end < currentInterval.start) {
				output.add(bufferInterval); // Add the buffer interval to the output list.
				bufferInterval = currentInterval; // Replace the buffer interval with the current element.
			}
			
			// If this is the last iteration of the loop, add the buffer interval to the output list.
			if(i == inputIntervalList.size() - 1) {
				output.add(bufferInterval);
			}
		}
		
		return output;
	}
}
