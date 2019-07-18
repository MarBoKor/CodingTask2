import java.util.ArrayList;
import java.util.Comparator;

/**
 * The Interval-class implements a <b>closed interval</b> and is therefore a simple tuple of integer objects.
 * @author	Marcus Korinth
 * @version	1.1
 * @since	2019-07-18
 */
public class Interval {
	// Fields:
	private int start; // This variable represents the left end of this interval.
	private int end; // This variable represents the right end of this interval.
	
	// Constructor:
	/** 
	 * This is the default constructor of the Interval-class.
	 * 
	 * @param	start Left end of the interval, thus it should be smaller or equal the 'end'.
	 * @param	end Right end of the interval, thus it should be greater or equal the 'start'.
	 * @return	Nothing.
	 */
	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
		
		validateIntervalBounds();
	}
	
	// Public Methods:
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
				return interval_A.getStart() - interval_B.getStart();
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
			 * 	- Condition 1: The end of Interval 1 has to be greater than or equal to the start of Interval 2. 8 > 6
			 * 	- Condition 2: The end of Interval 1 has to be smaller than the end of Interval 2. 8 < 10
			 */	
			if((bufferInterval.getEnd() >= currentInterval.getStart()) && // Making sure that the current interval starts within the bounds of the buffer interval.
					(bufferInterval.getEnd() < currentInterval.getEnd())) { // Making sure that the current interval ends after the buffer interval.
				bufferInterval.setEnd(currentInterval.getEnd()); // Save the end of the current interval in the buffer since they were overlapping.
			} 
			// If the current interval is not overlapping with the buffer interval, add the buffer to the output list and replace the buffer with the current.
			else if (bufferInterval.getEnd() < currentInterval.getStart()) {
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
	
	public String toString() {
		return String.format("[%d, %d]", this.start, this.end);
	}
	
	// Private Methods:
	/**
	 * This function checks if the specified interval start and end values are valid.
	 * (The 'start' value has to be smaller then or equal to the 'end' value).
	 * 
	 * @return	Nothing.
	 */
	private void validateIntervalBounds() {
		int startBuffer = this.start;
		int endBuffer = this.end;
		
		// The following check makes sure that the 'start' is smaller or equal than the 'end' variable.
		if(startBuffer <= endBuffer) {
			this.start = startBuffer;
			this.end = endBuffer;
		} 
		// Otherwise, if the int provided in 'end' should me smaller than the 'start' variable they are simply switched.
		else {
			this.start = endBuffer;
			this.end = startBuffer;
		}
	}
	
	// Getter and Setter:
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
		validateIntervalBounds();
	}
	
	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
		validateIntervalBounds();
	}
}
