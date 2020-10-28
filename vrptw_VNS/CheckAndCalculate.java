package vrptw_VNS;

import java.util.ArrayList;


import static vrptw_VNS.ReadData.*;
import static vrptw_VNS.Parameter.*;


public class CheckAndCalculate {
	
	
	public static boolean CheckIfLegal(VehicleType[] vehicle) {   //检查所有路径是否合法
		for(int i=0;i<vehicle.length;i++) {
			if(!vehicle[i].path.isEmpty()) {
				boolean LoadLegal=CheckLoad(vehicle[i].path,vehicle[i].capacity);
				if(LoadLegal==false)
					return false;
				boolean TimeLegal=CheckTime1(vehicle[i].time,vehicle[i].path);
		        if(TimeLegal==false)
		        	
		        	return false;
			}
			
		}
		return true;
	}
	
	public static <V> boolean CheckIfLegal_Single(VehicleType vehicle) {   //检查单条路径是否合法
		
			boolean LoadLegal=CheckLoad(vehicle.path,vehicle.capacity);
			if(LoadLegal==false)
				return false;
			boolean TimeLegal=CheckTime1(vehicle.time,vehicle.path);
	        if(TimeLegal==false)
	        	return false;
		
		return true;
	}
	
	public static boolean CheckLoad(ArrayList<CustomerType> path,double capacity) {
		
		double load=0;
		for(int i=0;i<path.size();i++) {
			load+=path.get(i).demand;
		}
		if(load>capacity)
			return false;
		return true;
	}
	
	/*public static boolean CheckTime(ArrayList<Double> time,ArrayList<CustomerType> path) {
		double timeNow=0;
		for(int i=1;i<path.size()-1;i++) {
			
			if(timeNow+path.get(i-1).service+disMat[path.get(i-1).id][path.get(i).id]<=path.get(i).begin) {
				//System.out.print("if:"+i+"     ");
				timeNow=path.get(i).begin;
				//System.out.println(timeNow+" - "+time.get(i));
				if(timeNow!=time.get(i))
					return false;
			}else {
				//System.out.print("else:"+i+"   ");
				timeNow+=path.get(i-1).service+disMat[path.get(i-1).id][path.get(i).id];
				timeNow=double_truncate(timeNow);
				//System.out.println(timeNow+" - "+time.get(i));
				if(timeNow!=time.get(i))
					return false;
			}
		}
		
		return true;
	}*/
	
	
	public static boolean CheckTime1(ArrayList<Double> time,ArrayList<CustomerType> path) {   //检查time表是否符合节点时间窗
		for(int i=1;i<path.size()-1;i++) {
			if(time.get(i)<path.get(i).begin||time.get(i)>path.get(i).end) {
				return false;
			}
		}
		if(disMat[path.get(path.size()-2).id][0]+path.get(path.size()-2).service>depot[0].end) {
			return false;
		}
		return true;
	}
	
	public static double CalTotalDistance(VehicleType[] vehicle) {
		double totalDistance=0;
		for(int i=0;i<vehicle.length;i++) {
			vehicle[i].CalPathDistance();
			totalDistance+=vehicle[i].distance;
		}
		return double_truncate(totalDistance);
	}
	
	
	
	

}
