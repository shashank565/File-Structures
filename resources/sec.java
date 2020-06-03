package hello;


import java.util.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.StringTokenizer;
public class sec {


		private demo2[] sI = new demo2[200000];
			
		private String  order_id,place,item_type,sales_channel,order_date,ship_date,units_sold,unit_price,total_cost,profit;
		//private String Date_of_Joining,Salary,SSN,Phone_No;
		int reccount = 0;

		public void getData(){
		    		@SuppressWarnings("resource")
		    		Scanner scanner = new Scanner(System.in);
		    		System.out.println("Enter the order_id: ");
					order_id = scanner.next();
					System.out.println("Enter the place: ");
					 place = scanner.next();
					System.out.println("Enter the item_type: ");
					item_type = scanner.next();
					System.out.println("Enter the sales_channel: ");
					sales_channel = scanner.next();
					System.out.println("Enter the order_date: ");
					order_date = scanner.next();
					System.out.println("Enter the ship_date: ");
					ship_date = scanner.next();
					System.out.println("Enter the units_sold: ");
					units_sold = scanner.next();
					System.out.println("Enter the unit_price: ");
					unit_price = scanner.next();
					System.out.println("Enter the total_cost: ");
					total_cost = scanner.next();
					System.out.println("Enter the profit: ");
					profit = scanner.next();
					System.out.println("WAITING");
		}
		public void add(){
			String data = order_id +","+  place +","+ item_type +","+ sales_channel +","+ order_date +","+ ship_date +","+ units_sold + "," + unit_price + "," + total_cost + "," +  profit;
			try{			                                                                                                    
				RandomAccessFile recordfile = new RandomAccessFile ("C:\\\\Users\\\\Sheshank\\\\Desktop\\\\fs_mini_proj\\\\sales_.txt","rw");
				recordfile.seek(recordfile.length());
				long pos = recordfile.getFilePointer();
				recordfile.writeBytes(data+"\n");
				recordfile.close();
				
						
				RandomAccessFile indexfile1 = new RandomAccessFile ("C:\\\\\\\\Users\\\\\\\\Sheshank\\\\\\\\Desktop\\\\\\\\fs_mini_proj\\\\\\\\index1.txt","rw");
				indexfile1.seek(indexfile1.length());
				indexfile1.writeBytes(place+","+pos+"\n");
				indexfile1.close();
			}
			catch(IOException e){
				System.out.println(e);
			} 
				
		 
		}                     
		@SuppressWarnings("resource")
		public void priIndex(){

			String line,prikey = null,pos = null;
			int count = 0;
			int sIIndex = 0;
			reccount=0;
			RandomAccessFile indexfile;
			try {
				indexfile = new RandomAccessFile("C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\Sheshank\\\\\\\\\\\\\\\\Desktop\\\\\\\\\\\\\\\\fs_mini_proj\\\\\\\\\\\\\\\\index1.txt","rw");

				try {
					
					while((line = indexfile.readLine())!= null){
	                                     if(line.contains("*")) 
	                                     {
	                                    	 	continue;
	                                     }
						count = 0;
						StringTokenizer st = new StringTokenizer(line,",");
						while (st.hasMoreTokens()){
						 count+=1;
						 if(count==1)
					    prikey = st.nextToken();
						 pos = st.nextToken();                 
					    }
						sI[sIIndex] = new demo2();
						sI[sIIndex].setRecPos(pos);
						sI[sIIndex].setseckey(prikey);
						reccount++;
						sIIndex++;
	                                        if(sIIndex==200000)
	                                        {	                                      
	                                            break;    
	                                        }
	                                }
				} catch (IOException e) {	
					e.printStackTrace();
					return;
				}
			} catch (FileNotFoundException e) {	
				e.printStackTrace();
				return;
			} 
			
			
			System.out.println("total records" + reccount);
			if (reccount==1) { return;}
			sortIndex();
		}
			
			
		public void sortIndex() {
			demo2 temp = new demo2();
			
			for(int i=0; i<reccount; i++)
			    {	
					for(int j=i+1; j<reccount; j++) {
						if(sI[i].getseckey().compareTo(sI[j].getseckey())  > 0)
			            {
			                temp.setseckey(sI[i].getseckey()); 
					        temp.setRecPos(sI[i].getRecPos());
					
				        	sI[i].setseckey(sI[j].getseckey());
				        	sI[i].setRecPos(sI[j].getRecPos());
					
				        	sI[j].setseckey(temp.getseckey());
				        	sI[j].setRecPos(temp.getRecPos());
			            }
					}			
				}		
		}
		
		        public void search(){
		        	 System.out.println("Enter the place to search: ");
		             @SuppressWarnings("resource")
					Scanner scanner = new Scanner(System.in);
		             String place = scanner.next();
		             
		             
		             int oripos = binarySearch(sI, 0, reccount-1, place);
		             
		             if (oripos == -1) {
		                 System.out.println("Record not found in the record file");
		                 return ;
		             }
		             
		             int pos = oripos;
		             
		             do {
		                 readFile(pos);
		                 pos--;
		                 if (pos < 0) { break;}
		             }while(sI[pos].getseckey().compareTo(place)==0);
		             
		             pos = oripos + 1 ;             
		             while(sI[pos].getseckey().compareTo(place)==0 && pos > reccount-1) {
		                 readFile(pos);
		                 pos++;
		             }
		        }
		 public void readFile(int pos) {
		            
		            RandomAccessFile recordfile;
		            try {
		                recordfile = new RandomAccessFile ("C:\\\\Users\\\\Sheshank\\\\Desktop\\\\fs_mini_proj\\\\sales_.txt","rw");
		                try {
		                    recordfile.seek(Long.parseLong(sI[pos].getRecPos()));
		                    String record = recordfile.readLine();
		                    StringTokenizer st = new StringTokenizer(record,",");
		                    
		                    int count = 0;
		                       
		                    while (st.hasMoreTokens()){
		                             count += 1;
		                               if(count==1){
		                            	     String  tmp_order_id = st.nextToken();			                  	    	
				                  	    	String tmp_place = st.nextToken();
				                  	    	String tmp_item_type = st.nextToken();
				                  	    	String tmp_sales_channel = st.nextToken();
				                  	    	String tmp_order_date = st.nextToken();
				                  	    	String tmp_ship_date = st.nextToken();
				                  	    	String tmp_units_sold = st.nextToken();
				                  	    	String tmp_unit_price = st.nextToken();
				                  	    	String tmp_total_cost = st.nextToken();
				                  	    	String tmp_profit = st.nextToken(); 
                                          
		                            	      System.out.println("order_id: "+ tmp_order_id);
				                  	          this.order_id = tmp_order_id;
				                  	    	
				                  	         // String tmp_country = st.nextToken();
				                     	      System.out.println("place: "+tmp_place);
				                     	      this.place = place;
				                     	       
				                     	       //String tmp_item_type = st.nextToken();
				                     	       System.out.println("item_type: "+tmp_item_type);
				                     	       this.item_type = tmp_item_type;
				                  	    	 
				                     	       // String tmp_sales_channel = st.nextToken();
				                     	        System.out.println("sales_channel: "+tmp_sales_channel);
				                     	        this.sales_channel = sales_channel;
				                     	      
				                     	       // String tmp_order_date = st.nextToken();
				                     	        System.out.println("order_date: "+tmp_order_date);
				                     	        this.order_date = tmp_order_date;
				                     	     
				                     	       // String tmp_ship_date = st.nextToken();
				                     	        System.out.println("ship_date: "+tmp_ship_date);
				                     	        this.ship_date = tmp_ship_date;
				                     	        
				                     	        

				                     	       // String tmp_units_sold = st.nextToken();
				                     	        System.out.println("units_sold: "+tmp_units_sold);
				                     	        this.units_sold = tmp_units_sold;
				                     	        

				                     	     //  String tmp_unit_price = st.nextToken();
				                     	        System.out.println("unit_price: "+tmp_unit_price);
				                     	        this.unit_price = tmp_unit_price;
				                     	        


					                     	     //  String tmp_unit_price = st.nextToken();
					                     	        System.out.println("total_cost: "+tmp_total_cost);
					                     	        this.total_cost = tmp_total_cost;
					                     	      
					                     	   //  String tmp_unit_price = st.nextToken();
					                     	        System.out.println("profit: "+tmp_profit);
					                     	        this.profit = tmp_profit;
					                     	        
					                     	        
				                  	    	 }		                     	     			  				                  	    	 			                  	   
	                  	    	 else
	                  	    		 break;
		                       }
		                    
		                    recordfile.close();
		                } 
		                    catch (NumberFormatException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                } 
		                catch (IOException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }
		            }  
		            catch (FileNotFoundException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		            }
		            catch (NullPointerException e) {
		            	e.printStackTrace();
		            }
		            
		 }
		 
		   int binarySearch(demo2 s[], int l, int r, String place) {
		    	
		    	int mid;
		    	while (l<=r) {
		            
		    		mid = (l+r)/2;
		    		if(s[mid].getseckey().compareTo(place)==0) {return mid;}
		    		if(s[mid].getseckey().compareTo(place)<0) l = mid + 1;
		    		if(s[mid].getseckey().compareTo(place)>0) r = mid - 1;
		    	}
		    	return -1;
		    }

		        public  void indexing() 
				  {
				         try{
				        RandomAccessFile hey=new RandomAccessFile("C:\\\\\\\\Users\\\\\\\\Sheshank\\\\\\\\Desktop\\\\\\\\fs_mini_proj\\\\\\\\sales_.txt","rw");
				        
				        RandomAccessFile indexfile=new RandomAccessFile("C:\\\\\\\\Users\\\\\\\\Sheshank\\\\\\\\Desktop\\\\\\\\fs_mini_proj\\\\\\\\index1.txt","rw");
				        String line;
				        long pos=hey.getFilePointer();
				        while((line = hey.readLine())!=null)
				        {
				            if(line.contains("*")) 
				            {
				            	continue;
					        }
				            String[] columns=line.split(",");
				         
				            indexfile.writeBytes(columns[1]+","+pos+"\n");
				            pos=hey.getFilePointer();
				        } 
				        
				        indexfile.close();
				        hey.close();

				        }
				    
				    catch(IOException e)
				    {
				        System.out.println(e);
				    }
				  }

		     public   void delete() throws IOException {
			 indexing();
		     
		     System.out.println("Enter the place to delete: ");
		     @SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
		     String place = scanner.next();
		     String ans = "n";
		     int pos;
		     int oripos = binarySearch(sI, 0, reccount-1, place);
		     System.out.println("WAIT FOR FEW SECONDS....: ");
		     if (oripos == -1) {
		         System.out.println("Record not found in the record file");
		         return ;
		     }
		     
		     pos = oripos;
		     
		     do {
		         readFile(pos);
		         
		         System.out.println("Do You Want To delete This Record ?(y/n) ");
		         ans = scanner.next();
		         
		         if (ans.compareTo("y")==0) {
		             markDelete(pos, place);
		         }
		         pos--;
		         if (pos < 0) { break;}
		     }while(sI[pos].getseckey().compareTo(place)==0);
		         
		     
		     pos = oripos + 1 ;
		     
		     
		     while(sI[pos].getseckey().compareTo(place)==0 && pos > reccount-1){
		         readFile(pos);
		         
		         System.out.println("Do You Want To delete This Record ?(y/n) ");
		         ans = scanner.next();
		         
		         if (ans.compareTo("y")==0) {
		             markDelete(pos, place);
		             break;
		         }
		         pos++;
		         if (pos > reccount-1) { break;}
		     }
		}
		 public void markDelete(int pos, String ssn) {
		     try {
		         RandomAccessFile recordfilee = new RandomAccessFile ("C:\\\\Users\\\\Sheshank\\\\Desktop\\\\fs_mini_proj\\\\sales_.txt","rw");
		         @SuppressWarnings("resource")
				RandomAccessFile indexfilee = new RandomAccessFile ("C:\\\\\\\\Users\\\\\\\\Sheshank\\\\\\\\Desktop\\\\\\\\fs_mini_proj\\\\\\\\index1.txt","rw");
		     
		             recordfilee.seek(Long.parseLong(sI[pos].getRecPos()));
		             recordfilee.writeBytes("*");
		             System.out.println("Done");
		             recordfilee.close();
		             String line;
		             String indexName;
		             long indexPos = 0;
		             long delPosi;
		             //delPosi = indexfilee.getFilePointer();
		             while((line = indexfilee.readLine())!=null) {
		                 if(line.contains("*"))
		                     continue;
		                 StringTokenizer st = new StringTokenizer(line,",");
		                delPosi = indexfilee.getFilePointer();
		                 delPosi = delPosi - (line.length()+2);
		                 
		                 //System.out.println("Delposi: " + delPosi);
		                 
		                 while(st.hasMoreTokens()) {
		                     indexName=st.nextToken();
		                     indexPos= Long.parseLong(st.nextToken());
		                     //System.out.println("indexPos: " + indexPos);
		                     //System.out.println("getrecpos: " + Long.parseLong(sI[pos].getRecPos()));
		                     if(indexName.equals(ssn) && indexPos == Long.parseLong(sI[pos].getRecPos()) ) {
		                         indexfilee.seek(delPosi);
		                         indexfilee.writeBytes("*");
		                         indexfilee.close();
		                         System.out.println("Deleted");
		                         indexing();
		                         return;
		                     }
		                 }
		             }
		             }
		         
		         catch (Exception e) {
		             e.printStackTrace();
		         }
		 }

		}

		                
		                