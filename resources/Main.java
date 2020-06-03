package hello;

import java.io.IOException;
import java.util.Scanner;
public class Main {
	public static void main(String args[]) throws IOException{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		
		pri stu = new pri();
		sec stu1 = new sec();
		
		System.out.println("Please wait \n"); 
        System.out.println("Building the  primary index \n");
        long init_time = System.nanoTime();
        
        stu.indexing();
        stu.priIndex();
        

        long end_time =System.nanoTime();
        long total_time=end_time-init_time;
        System.out.println("Building the index took :"+(total_time/1000000)+" msec \n");
        System.out.println("Building the index file completed! \n"); 
        
		System.out.println("Building the  secondary index \n");
		System.out.println("Please wait \n");
		
		stu1.indexing();
		stu1.priIndex();
		
		  long end_time1 =System.nanoTime();
	        long total_time1=end_time-init_time;
	        System.out.println("Building the index took :"+(total_time/1000000)+" msec \n");
	        System.out.println("Building the index file completed! \n"); 
	        
		
		
        while (true){
        	System.out.println("WELCOME");
            		
        	System.out.println("ENTER YOUR CHOICE"); 
                   
        	System.out.println("1>Enter the details: \n"
				+ "2>Enter the order_id to Search: \n"
				+ "3>Enter the place to Search: \n"
				+ "4>To Build Index using primary key: \n"
				+ "5>To Build Index secondary key: \n"
				+ "6>Enter the order_id to be Deleted: \n"
				+ "7>Enter the country to be Deleted: \n"
				+ "8>Unpack the data\n"
				+ "9>Exit");
        	            int choice = scanner.nextInt();
	
        	switch(choice){

				case 1: long starttime =System.nanoTime();
	                                  stu.getData();
	                                  stu.add();
	                                  stu.priIndex();
	                      
	                                
	                                  stu1.priIndex();
	                                  long endtime =System.nanoTime();
	                                  long totaltime=endtime-starttime;
	                                  System.out.println(totaltime/1000000+"msec");
						               break;
						               
				case 2: long starttime1 =System.nanoTime();
	                              stu.search();
	                              long endtime1 =System.nanoTime();
	                                  long totaltime1=endtime1-starttime1;
	                                  System.out.println(totaltime1/1000000+"msec");
				                    break;
				case 3: long starttime2 =System.nanoTime();
	            		stu1.search();
	            		long endtime2 =System.nanoTime();
	            		long totaltime2=endtime2-starttime2;
	            		System.out.println(totaltime2/1000000+"msec");
	            		break;  
	            		
				case 4:long starttime3 =System.nanoTime();
	                              stu.indexing();
	                              long endtime3 =System.nanoTime();
	                                  long totaltime3=endtime3-starttime3;
	                                  System.out.println(totaltime3/1000000+"msec");
						               break;
						               
				case 5:long starttime4 =System.nanoTime();
	            		stu1.indexing();
	            		long endtime4 =System.nanoTime();
	            		long totaltime4=endtime4-starttime4;
	                	System.out.println(totaltime4/1000000+"msec");
	                	break;
	            case 6:long starttime5 =System.nanoTime();
						stu.delete();
				        long endtime5=System.nanoTime();
	                    long totaltime5=endtime5-starttime5;
	                    System.out.println(totaltime5/1000000+"msec");
	                    break;
	            case 7:long starttime6 =System.nanoTime();
						stu1.delete();
						long endtime6=System.nanoTime();
	                    long totaltime6=endtime6-starttime6;
	                    System.out.println(totaltime6/1000000+"msec");
	                    break; 
	                    
	            case 8:long starttime7 =System.nanoTime();
	            		stu.unPack();
						long endtime7=System.nanoTime();
						long totaltime7=endtime7-starttime7;
	            		System.out.println(totaltime7/1000000+"msec");
	            break; 
				case 9: System.out.println("Exiting..");
						System.out.println("Exited successfully");
						return ;
					
			default : System.out.println("Enter a valid choice!");
        		}	
			}
		}                 		
}