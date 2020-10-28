package vrptw_VNS;

import static vrptw_VNS.ReadData.*;

import java.util.ArrayList;

public class VehicleType {
	
	int vehicle_id;
	double load;
	double capacity;
	ArrayList<CustomerType> path=new ArrayList<CustomerType>();
	ArrayList<Double> time=new ArrayList<Double>();
	double distance;
 
	
	
	
    public VehicleType(int id,double capacity) {
    	this.vehicle_id=id;
    	this.load=0;
    	this.capacity=capacity;
    }
 	
	public void CalPathDistance() {
		double PathDistance=0;
		for(int i=1;i<path.size();i++) {
			PathDistance+=disMat[path.get(i-1).id][path.get(i).id];
		}
		this.distance=double_truncate(PathDistance);
	}
}
