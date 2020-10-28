package vrptw_VNS;

import static vrptw_VNS.Parameter.*;
import static vrptw_VNS.ReadData.*;
import static vrptw_VNS.SolutionRun.*;



public class InitialSolution {
	
	
	public static void RandomCreate() {


		int arrangedNumber=0;
		for(int i=0;i<vehicle.length;i++) {
			if(vehicle[i].path.isEmpty())
				vehicle[i].path.add(depot[0]);
		    if(vehicle[i].time.isEmpty())
		    	vehicle[i].time.add(0.0);
			int point=0;
			double timeNow=0;
			
			for(int j=1;j<totalNumber;j++) {
				if(depot[j].isArranged==true) {
					continue;
				}
				System.out.print(j+" ");
				if(depot[j].demand+vehicle[i].load<=vehicle[i].capacity) {
					if(timeNow+disMat[vehicle[i].path.get(point).id][depot[j].id]>depot[j].begin) {
						if(timeNow+disMat[vehicle[i].path.get(point).id][depot[j].id]<depot[j].end) {
							if(timeNow+disMat[vehicle[i].path.get(point).id][depot[j].id]+depot[j].service+disMat[depot[j].id][0]<depot[0].end) {
								vehicle[i].path.add(depot[j]);
								vehicle[i].load+=depot[j].demand;
								timeNow+=disMat[vehicle[i].path.get(point).id][depot[j].id];
								timeNow=double_truncate(timeNow);
								vehicle[i].time.add(timeNow);
								timeNow+=depot[j].service;
								depot[j].isArranged=true;
								j=0;
								point++;
								arrangedNumber++;
							
							}else continue;
							
						}else continue;
						
					}else continue;
					
				}continue;
				
				
			}
			System.out.println();
			vehicle[i].path.add(depot[0]);
			//System.out.println(arrangedNumber);
			if(arrangedNumber==totalNumber-1) {
				System.out.println(arrangedNumber);
				break;
			}
			
		}
	}
	
	public static void RandomCreate1() { // 以时间窗为优先条件，遍历该路径的到达节点的最早时间窗，并插入


		int arrangedNumber=0;
		for(int i=0;i<vehicle.length;i++) {
			if(vehicle[i].path.isEmpty()) {
				vehicle[i].path.add(depot[0]);
				vehicle[i].path.add(depot[0]);
			}
				
		    if(vehicle[i].time.isEmpty()) {
		    	vehicle[i].time.add(0.0);
		    	vehicle[i].time.add(depot[0].end);
		    }
		    	
		
			double timeNow=0;
			
			for(int j=1;j<totalNumber;j++) {
				if(depot[j].isArranged==true) {
					continue;
				}

				if(depot[j].demand+vehicle[i].load<=vehicle[i].capacity) {
					for(int k=0;k<vehicle[i].time.size()-1;k++) {
						if(vehicle[i].time.get(k)+vehicle[i].path.get(k).service+disMat[vehicle[i].path.get(k).id][j]<=depot[j].begin
							&&depot[j].begin+depot[j].service+disMat[vehicle[i].path.get(k+1).id][j]<=vehicle[i].time.get(k+1)) {
								
								vehicle[i].time.add(k+1,depot[j].begin);
								vehicle[i].path.add(k+1,depot[j]);
								vehicle[i].load+=depot[j].demand;
								depot[j].isArranged=true;
								j=0;
								arrangedNumber++;
								break;
							}else if(vehicle[i].time.get(k)+vehicle[i].path.get(k).service+disMat[vehicle[i].path.get(k).id][j]>depot[j].begin
									&&vehicle[i].time.get(k)+vehicle[i].path.get(k).service+disMat[vehicle[i].path.get(k).id][j]+depot[j].service+disMat[j][vehicle[i].path.get(k+1).id]
									<vehicle[i].time.get(k+1)
									&&vehicle[i].time.get(k)+vehicle[i].path.get(k).service+disMat[vehicle[i].path.get(k).id][j]<depot[j].end){
								
								timeNow=vehicle[i].time.get(k)+vehicle[i].path.get(k).service+disMat[vehicle[i].path.get(k).id][j];
								timeNow=double_truncate(timeNow);
								vehicle[i].time.add(k+1,timeNow);
								vehicle[i].path.add(k+1,depot[j]);
								vehicle[i].load+=depot[j].demand;
								depot[j].isArranged=true;
								j=0;
								arrangedNumber++;
								break;
							}
						
					}
				}else continue;
			}
			if(arrangedNumber==totalNumber-1) {
				break;
			}
			vehicle[i].CalPathDistance();
		}
		if(arrangedNumber!=totalNumber-1) {
			System.out.println("不够车");
			System.exit(0);
		}
		
		
		
	}
	
	
	
	
	
	
	
}
