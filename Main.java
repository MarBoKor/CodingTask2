import java.util.ArrayList;

/**
 * @author	Marcus Korinth
 * @version	1.1
 * @since	2019-07-18
 */
public class Main {

	public static void main(String[] args) {
		Interval A = new Interval(25, 30);
		Interval B = new Interval(2, 19);
		Interval C = new Interval(14, 23);
		Interval D = new Interval(4, 8);
		Interval E = new Interval(30, 65);
		
		ArrayList<Interval> input = new ArrayList<Interval>();
		input.add(A);
		input.add(B);
		input.add(C);
		input.add(D);
		//input.add(E);
		
		System.out.println("Input:\t"	+ input.toString());
		
		ArrayList<Interval> output = Interval.merge(input);
		System.out.println("Output:\t"	+ output.toString());
	}
}
