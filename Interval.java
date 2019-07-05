/**
 * The Interval-class implements a <b>closed interval</b> and is therefore a simple tuple of integer objects.
 * @author	Marcus Korinth
 * @version	1.0
 * @since	2019-07-05
 */
class Interval {
	public int start; // This variable represents the left end of this interval.
	public int end; // This variable represents the right end of this interval.
	
	/** 
	 * This is the default constructor of the Interval-class.
	 * 
	 * @param	start Left end of the interval, thus it should be smaller or equal the 'end'.
	 * @param	end Right end of the interval, thus it should be greater or equal the 'start'.
	 * @return	Nothing.
	 */
	public Interval(int start, int end) {
		// The following check makes sure that the 'start' is smaller or equal than the 'end' variable.
		if(start <= end) {
			this.start = start;
			this.end = end;
		} 
		// Otherwise, if the int provided in 'end' should me smaller than the 'start' variable they are simply switched.
		else { 
			this.start = end;
			this.end = start;
		}
	}
	
	public String toString() {
		return String.format("[%d, %d]", this.start, this.end);
	}
}
