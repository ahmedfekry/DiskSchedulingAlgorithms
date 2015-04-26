import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;



public class SchedulingAlgo {
	private int n;
	private int []queue;
	private int start;
	private int size ;
//	private int previous;
	private long numofmv = 0;
	public SchedulingAlgo(){
		
	}
	public SchedulingAlgo(int n,int []queue,int start,int size){
		this.n = n;
		this.queue = new int[n];
		this.queue = queue;
		this.start = start;
		this.size = size;
//		this.previous = p;
	}
	
	public void FirstComeFirstServed(){
		numofmv = 0;
		System.out.println("First Come First Served");
//		System.out.println("Head : " + queue[1]);
//		System.out.println("Previous Servied : " + queue[0] );
		for(int i=0; i < queue.length-1; ++i){
			System.out.print(queue[i]+ " ");
//			if(i <= queue.length-2 )
			numofmv += Math.abs(queue[i] - queue[i+1]);
		}
		System.out.println("number of movements : " + numofmv);
		
	}
	/////////////////////////////////////////////////////////////////
	public void ShortestSeekTimeFirst(){
		System.out.println("Shortest Seek Time First");
		numofmv = 0;
        int prev = start;
        int [] rpath = path();
        for (int i = 0; i < rpath.length; i++) {
			System.out.print(rpath[i] + " ");
		}
        for (int i=0; i < rpath.length; i++) {
            numofmv += Math.abs(rpath[i]-prev);
            prev = rpath[i];
        }
        System.out.println("number of movements : " + numofmv);
	}
	public int[] path(){
	    int [] resultPath = new int[n];
	    int now = start;
	    int [] requests = new int[n];
	    for (int i = 0; i < queue.length; i++){
	        requests[i] = queue[i];
	    }
	    for (int i = 0; i < resultPath.length; i++){
	        int closest = closest(now, requests);
	        resultPath[i] = closest;
	        now = closest;
	    }
	      return resultPath; 
	 }
	int closest(int k, int[] requests){
        int min = 5000000;
        int minPos = -1;
        for (int i = 0; i < requests.length; i++){
            if (requests[i] == -1 ) continue;
            else if  (Math.abs(k-queue[i]) < min) {
                minPos = i;
                min = Math.abs(k-queue[i++]);            
            }
        }
        int nearestCylinder = requests[minPos];
        requests[minPos] = -1;
        return nearestCylinder;
    }
	/////////////////////////////////////////////////////////////////
	
	public void SCAN(){
		System.out.println("SCAN");
		 numofmv=0;
		 int [] requests = new int[queue.length+1];
		 requests[0]=0;
		 for (int i = 0; i < queue.length; i++) {
			 requests[i+1] = queue[i];
		 }
//		 for (int i = 0; i < requests.length; i++) {
//				System.out.print(requests[i]+" ");
//		 }
//		 System.out.println();
		 Arrays.sort(requests);
//		 System.out.println("After sorting");
//		 for (int i = 0; i < requests.length; i++) {
//			System.out.print(requests[i]+" ");
//		 }
//		 System.out.println();
		 int pos =0;
		 for (int i = 0; i < requests.length; i++) {
			if (requests[i] == start) {
				pos=i;
				break;
			}
		}
		int []path = new int[n+1];
		int x = pos,i=0;
		while( x >= 0){
			path[i] = requests[x];
			x--;
			i++;
		}
		for (int j = pos+1; j < requests.length; j++) {
			path[i] = requests[j];
			i++;
		}
		for (int j = 0; j < path.length; j++) {
			System.out.print(path[j] + " ");
		}
		int prev = start;
//		for (int j=1; j < path.length; j++) {
//            numofmv += Math.abs(path[j]-prev);
//            prev = path[j];
//        }
//		for (int j = 1; j < path.length; j++) {
//			numofmv+=Math.abs(path[i-1] - path[i]);
//		}
		for (int j = 0; j < path.length; j++) {
			numofmv+=Math.abs(path[j] - prev);
//			System.out.println(path[j] - prev);
			prev = path[j];
		}
	    System.out.println("number of movements : " + numofmv);	
	}
	/////////////////////////////////////////////////////////////
	public  void C_SCAN (){
		System.out.println("C_SCAN");

        numofmv =0;
		ArrayList<Integer>array = new ArrayList();
        for(int i=0; i < queue.length; ++i){
        	array.add(queue[i]);
        }
        ArrayList <Integer> greater = new ArrayList();
        ArrayList <Integer> less = new ArrayList();
        ArrayList <Integer> out = new ArrayList();
        less.add(0);
        for (int i = 0; i < array.size(); i++) {
            if(array.get(i)<start)
                less.add(array.get(i));
            else if(array.get(i)>start)
                greater.add(array.get(i));
        }
        greater.add(4999);
        greater.add(start);
        Collections.sort(greater);
        Collections.sort(less);
        for (int i = 0; i < greater.size(); i++) {
            out.add(greater.get(i));
         }
        for (int i = 0; i < greater.size()-1; i++) {
                numofmv+=(Math.abs(greater.get(i+1)-greater.get(i)));
         }
        
         numofmv += Math.abs(greater.get(greater.size()-1)-less.get(0));
         //System.out.println("size" +less.size());
         for (int i =0; i <less.size() ; i++) {
             out.add(less.get(i));
        }
         for (int i=0 ; i <less.size()-1 ; i++ ) {
             numofmv += Math.abs(less.get(i+1)-less.get(i));
         }
        System.out.println("");
         for (int i = 0; i < out.size(); i++) {
             System.out.print(out.get(i) + " ");
        }
         System.out.println();
       
        System.out.println("number of movements : " + numofmv);
    }
		
	public  void LOOK () {
		System.out.println("LOOK");
		numofmv=0;
	    ArrayList<Integer>array = new ArrayList();
	    for(int i=0; i < queue.length; ++i){
	    	array.add(queue[i]);
	      }
	    ArrayList <Integer> greater = new ArrayList();
	    ArrayList <Integer> less = new ArrayList();
	    ArrayList <Integer> out = new ArrayList();
	    for (int i = 0; i < array.size(); i++) {
	    	if(array.get(i)<start)
	    		less.add(array.get(i));
	        else if(array.get(i)>start)
	        	greater.add(array.get(i));
	    }
	    greater.add(start);
	    Collections.sort(greater);
	    Collections.sort(less);
	    for (int i = 0; i < greater.size(); i++) {
	    	out.add(greater.get(i));
	    }
	    for (int i = 0; i < greater.size()-1; i++) {
	    	numofmv+=(Math.abs(greater.get(i+1)-greater.get(i)));
	    }
	        
	    numofmv += Math.abs(greater.get(greater.size()-1)-less.get(less.size()-1));
	         //System.out.println("size" +less.size());
	    for (int i =less.size()-1; i >=0 ; i--) {
	    	out.add(less.get(i));
	     }
	     for (int i=0 ; i <less.size()-1 ; i++ ) {
	    	 numofmv += Math.abs(less.get(i+1)-less.get(i));
	       }
	      for (int i = 0; i < out.size(); i++) {
	             System.out.print(out.get(i) +" ");	            
	        }
	       
	        System.out.println("number Of Movements : " + numofmv);
	    }
	    public void C_LOOK ()
	    {
	    	System.out.println("C_LOOK");
	    	numofmv=0;
	        ArrayList<Integer>array = new ArrayList();
	        for(int i=0; i < queue.length; ++i){
	        	array.add(queue[i]);
	        }

	        ArrayList <Integer> greater = new ArrayList();
	        ArrayList <Integer> less = new ArrayList();
	        ArrayList <Integer> out = new ArrayList();
	        //less.add(0);
	        for (int i = 0; i < array.size(); i++) {
	            if(array.get(i)<start)
	                less.add(array.get(i));
	            else if(array.get(i)>start)
	                greater.add(array.get(i));
	        }
	        greater.add(start);
	        Collections.sort(greater);
	        Collections.sort(less);
	        for (int i = 0; i < greater.size(); i++) {
	            out.add(greater.get(i));
	         }
	        for (int i = 0; i < greater.size()-1; i++) {
	                numofmv+=(Math.abs(greater.get(i+1)-greater.get(i)));
	         }
	        
	         numofmv+= Math.abs(greater.get(greater.size()-1)-less.get(0));
	         for (int i =0; i<less.size() ; i++) {
	             out.add(less.get(i));
	        }
	         for (int i=0 ; i <less.size()-1 ; i++ ) {
	             numofmv+= Math.abs(less.get(i+1)-less.get(i));
	         }
	        System.out.println("");
	         for (int i = 0; i < out.size(); i++) {
	             System.out.print(out.get(i) + " ");
	            
	        }
	       
	        System.out.println("number of movements : " + numofmv);
	    }
	 ////////////////////////////////////////////////////////////////
	
}
