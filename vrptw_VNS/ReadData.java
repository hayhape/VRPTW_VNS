package vrptw_VNS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import static vrptw_VNS.Parameter.*;


public class ReadData {
	static double[][] disMat;
	
	 public static void input() throws Exception {
		 for(int i=0;i<totalNumber;i++) {   
			 depot[i]=new CustomerType();
		 }
		 
    	 String path="T:\\javaÏà¹Ø\\lib\\c101_1.txt";
	  	 String line=null;
    	 String[] str=null;
    	 Scanner cin=new Scanner(new BufferedReader(new FileReader(path)));
    	 for(int i=0;i<totalNumber;i++) {
    		 line=cin.nextLine();
    		 line.trim();
    		 str=line.split("\\s+");
    		 depot[i].id=Integer.parseInt(str[1]);
    		 depot[i].x=Double.parseDouble(str[2]);
    		 depot[i].y=Double.parseDouble(str[3]);
    		 depot[i].demand=Double.parseDouble(str[4]);
    		 depot[i].begin=Double.parseDouble(str[5]);
    		 depot[i].end=Double.parseDouble(str[6]);
    		 depot[i].service=Double.parseDouble(str[7]);
    	 }
    	 cin.close();
    	
    	disMat=new double[totalNumber][totalNumber];
    	 for(int i=1;i<totalNumber;i++) {
    		 for(int j=1;j<totalNumber;j++) {
    			 disMat[i-1][j-1]=Math.sqrt(((depot[i-1].x-depot[i].x)
							*(depot[i-1].x-depot[i].x)+
					(depot[i-1].y-depot[i].y)
					*(depot[i-1].y-depot[i].y)));
    			 disMat[i][j]=double_truncate(disMat[i][j]);
    		 }
    	 } 
     }
     
     public static double double_truncate(double v){
 		int iv = (int) v;
 		if(iv+1 - v <= 1e-6)
 			return iv+1;
 		double dv = (v - iv) * 10;
 		int idv = (int) dv;
 		double rv = iv + idv / 10.0;
 		return rv;
 	}	
     
     
}
