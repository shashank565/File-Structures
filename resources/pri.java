package hello;


import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
public class pri {



		private demo1[] sI = new demo1[186760];
			
		    private String order_id,place,item_type,sales_channel,order_date,ship_date,units_sold,unit_price,total_cost,profit;
			int reccount = 0;
		    int reccount1=0;
		public void getData(){
		    		@SuppressWarnings("resource")
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter the order_id: ");
				order_id = scanner.next();
				System.out.println("Enter the country: ");
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
		        String data=order_id +","+  place +","+ item_type +","+ sales_channel +","+ order_date +","+ ship_date +","+ units_sold + "," + unit_price + "," + total_cost + "," +  profit;

		 try{			
					RandomAccessFile recordfile = new RandomAccessFile ("C:\\Users\\Sheshank\\Desktop\\fs_mini_proj\\sales_.txt","rw");
					recordfile.seek(recordfile.length());
					long pos = recordfile.getFilePointer();
					recordfile.writeBytes(data+"\n");
					recordfile.close();
					
					RandomAccessFile indexfile = new RandomAccessFile ("C:\\\\Users\\\\Sheshank\\\\Desktop\\\\fs_mini_proj\\\\index.txt","rw");
					indexfile.seek(indexfile.length());
					indexfile.writeBytes(order_id+","+pos+"\n");
					indexfile.close();
					
					RandomAccessFile indexfile1 = new RandomAccessFile ("C:\\\\Users\\\\Sheshank\\\\Desktop\\\\fs_mini_proj\\\\index1.txt","rw");
					indexfile1.seek(indexfile1.length());
					indexfile1.writeBytes(place+","+pos+"\n");
					indexfile1.close();
				}
				catch(IOException e){
					System.out.println(e);
				}		 
		}     
		
		public void unPack(){
			try{
				@SuppressWarnings("resource")
				BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Sheshank\\Desktop\\fs_mini_proj\\sales_.txt"));
	    		String line;
	                try{
	              
	                while((line = reader.readLine())!= null){
	                	if(line.contains("*"))
							continue;
	                	int count = 0;
	                	StringTokenizer st = new StringTokenizer(line,",");
	                	while (st.hasMoreTokens()){
	           	    	 count += 1;
	           	    	 if(count==1) {
	           	         System.out.println("order_id: "+st.nextToken());
	           	    	 System.out.println("country: "+st.nextToken());
	           	    	 System.out.println("item_type: "+st.nextToken());
	           	    	 System.out.println("sales_channel: "+st.nextToken());
	           	    	 System.out.println("order_date: "+st.nextToken());
	           	    	 System.out.println("ship_date: "+st.nextToken());
	           	    	 System.out.println("units_sold: "+st.nextToken());
	           	    	 System.out.println("unit_price: "+st.nextToken());
	           	    	 System.out.println("total_cost: "+st.nextToken());	
	           	    	 System.out.println("profit: "+st.nextToken());	           	    	 
	           	    	 System.out.println();
	           	    	 }
	           	    	 
	           	    	 else
	           	    		 break;
	                }
	                }
	                }
	                catch(Exception e){return;}
	    		
	    		}
				catch(IOException e){
					return;
				}
		}
		
		    public void priIndex(){

				String line,prikey = null,pos = null;
				int count = 0;
				int sIIndex = 0;
				reccount=0;
				RandomAccessFile indexfile;
				try {
					indexfile = new RandomAccessFile("C:\\\\Users\\\\Sheshank\\\\Desktop\\\\fs_mini_proj\\\\index.txt","rw");

					try {
						
						while((line = indexfile.readLine())!= null){
		                                     if(line.contains("*")) {
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
							sI[sIIndex] = new demo1();
							sI[sIIndex].setRecPos(pos);
							sI[sIIndex].setprikey(prikey);
							reccount++;
							sIIndex++;
							 
		                                        if(sIIndex==186760)
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
				demo1 temp = new demo1();
				
				for(int i=0; i<reccount; i++)
				    {	
						for(int j=i+1; j<reccount; j++) {
							if(sI[i].getprikey().compareTo(sI[j].getprikey())  > 0)
				            {
				                temp.setprikey(sI[i].getprikey()); 
						        temp.setRecPos(sI[i].getRecPos());
						
					        	sI[i].setprikey(sI[j].getprikey());
					        	sI[i].setRecPos(sI[j].getRecPos());
						
					        	sI[j].setprikey(temp.getprikey());
					        	sI[j].setRecPos(temp.getRecPos());
				            }
						}
							
					}	
				
			}
			
		        public void search(){
		            System.out.println("Enter the order_id to search: ");
							Scanner scanner = new Scanner(System.in);
							String order_id = scanner.next();
							System.out.println(reccount);
							int pos = binarySearch(sI, 0, reccount-1, order_id);
		                                        
							
							if (pos == -1) {
								System.out.println("Record not found in the record file");
								return ;
							}
							
							RandomAccessFile recordfile;
							try {
								recordfile = new RandomAccessFile ("C:\\\\Users\\\\Sheshank\\\\Desktop\\\\fs_mini_proj\\\\sales_.txt","rw");
								try {
									recordfile.seek(Long.parseLong(sI[pos].getRecPos()));
									String record = recordfile.readLine();
									int length=record.length();
									StringTokenizer st = new StringTokenizer(record,",");
									int count = 0;
		                                                        
				               	    
				                	while (st.hasMoreTokens()){
				                		     count+=1;
				                		     
				                		     
				                		     
				                  	    	 if(count==1){
				                  	    	 String tmp_order_id = st.nextToken();			                  	    	
				                  	    	String tmp_place = st.nextToken();
				                  	    	String tmp_item_type = st.nextToken();
				                  	    	String tmp_sales_channel = st.nextToken();
				                  	    	String tmp_order_date = st.nextToken();
				                  	    	String tmp_ship_date = st.nextToken();
				                  	    	String tmp_units_sold = st.nextToken();
				                  	    	String tmp_unit_price = st.nextToken();
				                  	    	String tmp_total_cost = st.nextToken();
				                  	    	String tmp_profit = st.nextToken();
				                  	    	
				                  	    	
				                  	    	 
				                  	    	 if(tmp_order_id.contains("*"))
                                             {
                                                 System.out.println("it has been deleted");
                                                 break;
                                             }
					              System.out.println("order_id: "+order_id);
	                  	         this.order_id = tmp_order_id;
	                  	    	
	                  	          //String country = st.nextToken();
	                     	      System.out.println("country: "+tmp_place);
	                     	      this.place = place;
	                     	       
	                     	      // String item_type = st.nextToken();
	                     	       System.out.println("item_type: "+tmp_item_type);
	                     	       this.item_type = tmp_item_type;
	                  	    	 
	                     	        //String sales_channel = st.nextToken();
	                     	        System.out.println("sales_channel: "+tmp_sales_channel);
	                     	        this.sales_channel = sales_channel;
	                     	      
	                     	       //String order_date = st.nextToken();
	                     	        System.out.println("order_date: "+tmp_order_date);
	                     	        this.order_date = tmp_order_date;
	                     	     
	                     	       //String ship_date = st.nextToken();
	                     	        System.out.println("ship_date: "+tmp_ship_date);
	                     	        this.ship_date = tmp_ship_date;
	                     	        
	                     	        

	                     	       // String units_sold = st.nextToken();
	                     	        System.out.println("units_sold: "+tmp_units_sold);
	                     	        this.units_sold = tmp_units_sold;
	                     	        

	                     	      //  String unit_price = st.nextToken();
	                     	        System.out.println("unit_price: "+tmp_unit_price);
	                     	        this.unit_price = tmp_unit_price;
	                     	        

	                     	       // String profit = st.nextToken();
	                     	        System.out.println("total_cost: "+total_cost);
	                     	        this.total_cost = total_cost;		
	                     	        
	                     	       // String profit = st.nextToken();
	                     	        System.out.println("profit: "+profit);
	                     	        this.profit = profit;	
	                     	        
	                     	       System.out.println();
	                     	       
	                     	       
	                     	  	
	                  	    	 	System.out.println("Do you want to modify????? Y/N");
	                  	    	 	String modi = scanner.next();
	                  	    	 	
		                  	    	 	if ( modi.equalsIgnoreCase("y"))
		                  	    	 	{
		                  	    	 		System.out.println("What do you want to change");
		                  	    	 		System.out.println("Enter your choice");
		                  	    	 		System.out.println("1 order_id \n 2. place \n 3.item_type \n 4. sales{_channel \n 5.order_date\n 6. ship_date \n 7. units_sold \n 8. unit_price \n 9.total_cost \n  10. profit \n");

		                  	    	           int choice = scanner.nextInt();
		                  	    	           switch(choice)
		                  	    	           {
			                  	    	           case 1 :System.out.println("Enter the order_id ");
			                  	    	           tmp_order_id=scanner.next();
			                  	    	           break;
			                  	    	     
			                  	    	           case 2 :System.out.println("Enter the place ");
			                  	    	           tmp_place=scanner.next();
			                  	    	           break;
			                  	    	     
			                  	    	           case 3: System.out.println("Enter the item_type ");
			                  	    	           tmp_item_type=scanner.next();
			                  	    	           break;
			                  	    	           
			                  	    	           case 4 :System.out.println("Enter the sales_channel ");
			                  	    	           tmp_sales_channel=scanner.next();
			                  	    	           break;
			                  	    	     
			                  	    	           case 5 :System.out.println("Enter the order_date ");
			                  	    	           tmp_order_date=scanner.next();
			                  	    	           break;
			                  	    	     
			                  	    	           case 6: System.out.println("Enter the ship_date ");
			                  	    	           tmp_ship_date=scanner.next();
			                  	    	           break;
			                  	    	           
			                  	    	           case 7 :System.out.println("Enter the units_sold ");
			                  	    	           tmp_units_sold=scanner.next();
			                  	    	           break;
			                  	    	     
			                  	    	           case 8 :System.out.println("Enter the unit_price ");
			                  	    	           tmp_unit_price=scanner.next();
			                  	    	           break;
			                  	    	     
			                  	    	           case 9: System.out.println("Enter the total_cost ");
			                  	    	           tmp_total_cost=scanner.next();
			                  	    	           break;
			                  	    	           
			                  	    	           case 10 :System.out.println("Enter the profit ");
			                  	    	           tmp_profit=scanner.next();
			                  	    	           break;
			                  	    	     
		                  	    	           }
		                  	    	           long pointer=recordfile.getFilePointer();
	   String pack = tmp_order_id +","+  tmp_place +","+ tmp_item_type +","+ tmp_sales_channel +","+ tmp_order_date +","+ tmp_ship_date +","+ tmp_units_sold + "," + tmp_unit_price + "," + tmp_total_cost + "," +  tmp_profit;
	   if( pack.length()>length)
	   {
	 	
	 		if(reccount==1) {
	 			pointer = 0;
	 		}
	 		else {
	 			pointer = pointer-(length+1);
	 		}
	 		
	 		recordfile.seek(pointer);
	 		recordfile.writeBytes("*");
	      recordfile.seek(recordfile.length());
	      recordfile.writeBytes(pack+"\n");
	      recordfile.close();
	   }
	 else {
	 		if(reccount==1) {
	 			pointer = 0;
	 		}
	 		else {
	 			pointer = pointer-(length+1);
	 		}
	 		
	 	recordfile.seek(pointer);
	 	recordfile.writeBytes(pack);
	 	recordfile.close();	
	 }
		                  	    	 	}
		                  	    	 	else
		                  	    	 	{
		                  	    	 		System.out.println("ok done");
		                  
	                  	    	 	}
	                     	       
	                     	       
				                           	       
				                  	    	 }				                     	     			  				                  	    	 			                  	   
				                  	    	 else
				                  	    		 break;
				                       }
				                	
								} 
									catch (NumberFormatException e) {
									
									e.printStackTrace();
								} 
								catch (IOException e) {
									
									e.printStackTrace();
								}
								
								
								}
														
			                	catch (FileNotFoundException e) {
								e.printStackTrace();
							}
							catch(NoSuchElementException e) {
								e.printStackTrace();
							}
						
		        }
		        
		        int binarySearch(demo1 s[], int l, int r, String prikey) {
		    	
		    	int mid;
		    	while (l<=r) {
		            
		    		mid = (l+r)/2;
		    		if(s[mid].getprikey().compareTo(prikey)==0) {return mid;}
		    		if(s[mid].getprikey().compareTo(prikey)<0) l = mid + 1;
		    		if(s[mid].getprikey().compareTo(prikey)>0) r = mid - 1;
		    	}
		    	return -1;
		    }
		       /* int binarySearch1(demo s[], int l, int r, String place) {
		        	
		        	int mid;
		        	while (l<=r) {
		                
		        		mid = (l+r)/2;
		        		if(s[mid].getid().compareTo(place)==0) {return mid;}
		        		if(s[mid].getid().compareTo(place)<0) l = mid + 1;
		        		if(s[mid].getid().compareTo(place)>0) r = mid - 1;
		        	}
		        	return -1;
		        }*/

		  public  void indexing() 
		  {
		         try{
		        RandomAccessFile hey=new RandomAccessFile("C:\\\\Users\\\\Sheshank\\\\Desktop\\\\fs_mini_proj\\\\sales_.txt","rw");
		  
		    			

		        RandomAccessFile indexfile=new RandomAccessFile("C:\\\\Users\\\\Sheshank\\\\Desktop\\\\fs_mini_proj\\\\index.txt","rw");
		        String line;
		        long pos=hey.getFilePointer();
		        while((line = hey.readLine())!=null)
		        {
		            if(line.contains("*")) {
			                		continue;
			                	}

		            String[] columns=line.split(",");
		                                 	         
		            indexfile.writeBytes(columns[0]+","+pos+"\n");
		            pos=hey.getFilePointer();
		        } indexfile.close();
		        hey.close();
		                		       
		         }
		    
		    catch(IOException e)
		    {
		        System.out.println(e);
		    }
		  }
		  
		 public   void delete() throws IOException {
		         System.out.println("Enter the primary key to delete record ");
							Scanner scanner = new Scanner(System.in);
							String prikey = scanner.next();
		        int pos = binarySearch(sI, 0, reccount-1, prikey);
		        System.out.println("WAIT FOR FEW SECONDS....: ");	
							if (pos == -1) {
								System.out.println("Record not found in the record file");
								return ;
							}
		                                        RandomAccessFile recordfile;
		                                        
							
								recordfile = new RandomAccessFile ("C:\\\\Users\\\\Sheshank\\\\Desktop\\\\fs_mini_proj\\\\sales_.txt","rw");
								try {
									recordfile.seek(Long.parseLong(sI[pos].getRecPos()));
		                                                        recordfile.writeBytes("*");
		                                                        recordfile.close();
		                                                
		                                                        }    
		                                                            
		                                             catch (NumberFormatException e) {
									
									e.printStackTrace();
								} 
								catch (IOException e) {
									
									e.printStackTrace();
								}
								
								
								}
									


		}

