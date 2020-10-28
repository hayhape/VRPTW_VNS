package vrptw_VNS;

import java.util.ArrayList;
import static vrptw_VNS.ReadData.*;
import static vrptw_VNS.Parameter.*;
public class EvaluateRoute {

	public static <V> void ReplaceElementList1(ArrayList<V> list,int From,int To) {   //同一条List中替换元素
		
		V copyFrom=list.get(From);
		V copyTo=list.get(To);
		list.remove(From);
		list.add(From,copyTo);
		list.remove(To);
		list.add(To,copyFrom);
	}
	
	public static <V> void ReplaceElementList2(ArrayList<V> listFrom,ArrayList<V> listTo,int From,int To) {  //不同list中替换元素
		V copyFrom=listFrom.get(From);
		V copyTo=listTo.get(To);
		listFrom.remove(From);
		listFrom.add(From,copyTo);
		listTo.remove(To);
		listTo.add(To,copyFrom);
	}
	
    public static <V> void Insert1(ArrayList<V> list,int From,int To) {   //同一条List中插入元素
		
		V copyFrom=list.get(From);
		list.remove(From);
		list.add(To,copyFrom);
	}
    
    public static <V> void Insert2(ArrayList<V> listFrom,ArrayList<V> listTo,int From,int To) {  //不同list中插入元素
		V copyFrom=listFrom.get(From);
		listFrom.remove(From);
		listTo.add(To,copyFrom);
	}
	
	
	
	public static void UpdateVehicle(VehicleType vehicle) {
		vehicle.CalPathDistance();
		if(vehicle.time.isEmpty())
			vehicle.time.add(0.0);
		double timeNow=0;
		vehicle.load=0;
	    for(int i=1;i<vehicle.path.size();i++) {
	    	vehicle.load+=vehicle.path.get(i).demand;
	    	if(vehicle.time.get(i-1)+vehicle.path.get(i-1).service+disMat[vehicle.path.get(i-1).id][vehicle.path.get(i).id]<=vehicle.path.get(i).begin) {
	    		timeNow=vehicle.path.get(i).begin;
	    		timeNow=double_truncate(timeNow);
	    		vehicle.time.add(timeNow);
	    	}else {
	    		timeNow=vehicle.time.get(i-1)+vehicle.path.get(i-1).service+disMat[vehicle.path.get(i-1).id][vehicle.path.get(i).id];
	    		timeNow=double_truncate(timeNow);
	    		vehicle.time.add(timeNow);
	    	}
		}
	    vehicle.CalPathDistance();
	}
}
