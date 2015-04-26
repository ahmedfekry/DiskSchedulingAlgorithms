import java.util.*;
public class Main {
//98 183 37 122 14 124 65 67
//86 1470 913 1774 948 1509 1022 1750 130
	public static void main(String[] args) {
			
		Scanner input = new Scanner(System.in);
		System.out.println("input the number of the cylinders : ");
		int c = input.nextInt();
		
		System.out.println("input the size of the Queue : ");
		int n = input.nextInt();
		int []arr = new int[n];
		
		System.out.println("input the Queue");
		for(int i = 1; i < n; ++i){
			arr[i] = input.nextInt();
		}
		
		System.out.println("input the start: ");
		int s = input.nextInt();
		arr[0] = s;
//		
//		System.out.println("input the previous : ");
//		int p = input.nextInt();
//		arr[0] = p;
		
		SchedulingAlgo object = new SchedulingAlgo(n,arr,s,c);
		object.FirstComeFirstServed();
		System.out.println("-----------------------------------");
		object.ShortestSeekTimeFirst();
		System.out.println("-----------------------------------");
		object.SCAN();
		System.out.println("-----------------------------------");
		object.C_SCAN();
		System.out.println("-----------------------------------");
		object.LOOK();
		System.out.println("-----------------------------------");
		object.C_LOOK();
	}

}
