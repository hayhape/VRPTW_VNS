package vrptw_VNS;

import static vrptw_VNS.ReadData.*;
import static vrptw_VNS.CheckAndCalculate.*;
import static vrptw_VNS.Parameter.*;
import static vrptw_VNS.InitialSolution.*;
import static vrptw_VNS.EvaluateRoute.*;


import java.util.ArrayList;






public class SolutionRun {
      static VehicleType[] vehicle;
      
	 SolutionRun(){
		 vehicle=new VehicleType[vehicleNumber];
		 for(int i=0;i<vehicleNumber;i++) {
			 vehicle[i]=new VehicleType(i,200);
		 }
	 }
	  public void Run()throws Exception { 
		  RandomCreate1();

		  double currentDistance=calIfBetter();
		  VND();
		  //System.out.println(CheckIfLegal(vehicle));  //检验初始解是否合法  
		  print();
		  double bestDistance=calIfBetter();
		  System.out.println(currentDistance+"  vs  "+bestDistance);
		  
	
		  
	
	  }
	  
	  
	  public void VND() {
		  boolean goingOn=true;
		  double tempCost=Double.MAX_VALUE;
		  int SW=1;
		  double currentCost=0;
		  while(goingOn) {
			  switch(SW) {
			  case 1:
				  Swap();
				  currentCost=calIfBetter();
				  if(tempCost>currentCost) {
					  tempCost=currentCost;
				  }
			  case 2:
				  Reloacation();
				  currentCost=calIfBetter();
				  if(tempCost>currentCost) {
					 SW=0;
					 break;
				  }
			  case 3:
				  goingOn=false;
			  }
			  SW++;
			  
		  }
	  }
	  
	  public void Swap() {     //选择两个不同的节点，做位置的交换
		  for(int vfrom=0;vfrom<vehicle.length;vfrom++) {
			  if(!vehicle[vfrom].path.isEmpty()) {
				  
				  for(int pfrom=1;pfrom<vehicle[vfrom].path.size()-1;pfrom++) {
					  
					  for(int vto=0;vto<vehicle.length;vto++) {
						  if(!vehicle[vto].path.isEmpty()) {
							  
							  for(int pto=1;pto<vehicle[vto].path.size()-1;pto++) {
								  //System.out.println("vfrom="+vfrom+"  pfrom="+pfrom+"  vto="+vto+"  pto="+pto);
								  VehicleType vehicleFrom=new VehicleType(vfrom, vehicle[vfrom].capacity);
								  vehicleFrom.path.addAll(vehicle[vfrom].path);
								  if(vfrom==vto) {
									  if(pfrom!=pto) {
										  ReplaceElementList1(vehicleFrom.path, pfrom, pto);
											 UpdateVehicle(vehicleFrom);
											 if(CheckIfLegal_Single(vehicleFrom)) {
												 if(vehicleFrom.distance<vehicle[vfrom].distance) {
													// System.out.println("success ==");
													 vehicle[vfrom].path.clear();
													 vehicle[vfrom].path.addAll(vehicleFrom.path);
													 vehicle[vfrom].time.clear();
													 UpdateVehicle(vehicle[vfrom]);
													 UpdateVehicle(vehicle[vto]);
												 }
											 }
									  }
								  }
								  else {
									  VehicleType vehicleTo=new VehicleType(vto, vehicle[vto].capacity);
									  vehicleTo.path.addAll(vehicle[vto].path);
									  ReplaceElementList2(vehicleFrom.path, vehicleTo.path, pfrom, pto);
									  UpdateVehicle(vehicleFrom);
									  UpdateVehicle(vehicleTo);
									  if(CheckIfLegal_Single(vehicleFrom)&&CheckIfLegal_Single(vehicleTo)) {
										  if(vehicleFrom.distance+vehicleTo.distance<vehicle[vfrom].distance+vehicle[vto].distance) {
											  //System.out.println("success !=");
											  vehicle[vfrom].path.clear();
											  vehicle[vfrom].path.addAll(vehicleFrom.path);
											  vehicle[vfrom].time.clear();
											  vehicle[vto].time.clear();
											  vehicle[vto].path.clear();
											  vehicle[vto].path.addAll(vehicleTo.path);
											  UpdateVehicle(vehicle[vfrom]);
											  UpdateVehicle(vehicle[vto]);
										  }
									  }  
								  }
							  }
						  }
			
					  }
				  }
			  }

		  }
	  }
	  
	  public void Reloacation() {     //选择一个节点，移除并插入到另外的位置
		  boolean stop=false;
		  for(int vfrom=0;vfrom<vehicle.length;vfrom++) {
			  if(!vehicle[vfrom].path.isEmpty()) {
				  
				  for(int pfrom=1;pfrom<vehicle[vfrom].path.size();pfrom++) {
					  
					  for(int vto=0;vto<vehicle.length;vto++) {
						  if(!vehicle[vto].path.isEmpty()) {
							  
							  for(int pto=1;pto<vehicle[vto].path.size();pto++) {
								  //System.out.println("vfrom="+vfrom+"  pfrom="+pfrom+"  vto="+vto+"  pto="+pto);
								  VehicleType vehicleFrom=new VehicleType(vfrom, vehicle[vfrom].capacity);
								  vehicleFrom.path.addAll(vehicle[vfrom].path);
								  if(vfrom==vto) {
									  if(pfrom!=pto) {
										  Insert1(vehicleFrom.path, pfrom, pto);
											 UpdateVehicle(vehicleFrom);
											 if(CheckIfLegal_Single(vehicleFrom)) {
												 if(vehicleFrom.distance<vehicle[vfrom].distance) {
													// System.out.println("success ==");
													 vehicle[vfrom].path.clear();
													 vehicle[vfrom].path.addAll(vehicleFrom.path);
													 vehicle[vfrom].time.clear();
													 UpdateVehicle(vehicle[vfrom]);
													 UpdateVehicle(vehicle[vto]);
												 }
											 }
									  }
								  }
								  else {
									  VehicleType vehicleTo=new VehicleType(vto, vehicle[vto].capacity);
									  vehicleTo.path.addAll(vehicle[vto].path);
									  Insert2(vehicleFrom.path, vehicleTo.path, pfrom, pto);
									  UpdateVehicle(vehicleFrom);
									  UpdateVehicle(vehicleTo);
									  if(CheckIfLegal_Single(vehicleFrom)&&CheckIfLegal_Single(vehicleTo)) {
										  if(vehicleFrom.distance+vehicleTo.distance<vehicle[vfrom].distance+vehicle[vto].distance) {
											  //System.out.println("success !=");
											  vehicle[vfrom].path.clear();
											  vehicle[vfrom].path.addAll(vehicleFrom.path);
											  vehicle[vfrom].time.clear();
											  vehicle[vto].time.clear();
											  vehicle[vto].path.clear();
											  vehicle[vto].path.addAll(vehicleTo.path);
											  UpdateVehicle(vehicle[vfrom]);
											  UpdateVehicle(vehicle[vto]);
										  }
									  }  
								  }
							  }
						  }
					  }
				  }
			  }
		  }
	  }
	  
	  
	  
	  
	  public void print() {
		  System.out.println("\n"+"           -----打印当前解-----");
		  for(int i=0;i<vehicle.length;i++) {
			  if(!vehicle[i].time.isEmpty()){
				  System.out.println("——"+i+" 路径——");
				  for(int j=0;j<vehicle[i].path.size();j++) {
					  if(j==vehicle[i].path.size()-1) {
						  System.out.print(vehicle[i].path.get(j).id);
						  break;
					  }
					  System.out.print(vehicle[i].path.get(j).id+"->");
				  }
				
				  System.out.println("   Load="+vehicle[i].load);
				 
				  System.out.println(vehicle[i].time+"\n");
			  }
			  
		  }
	  }
	  
	  public double  calIfBetter() {
		  double currentDistance=0;
		  for(int i=0;i<vehicle.length;i++) {
			  currentDistance+=vehicle[i].distance;
		  }
		  
		  return currentDistance;
	  }
}
