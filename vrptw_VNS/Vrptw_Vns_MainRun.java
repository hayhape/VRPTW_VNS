package vrptw_VNS;

import static vrptw_VNS.ReadData.*;
import static vrptw_VNS.Parameter.*;

public class Vrptw_Vns_MainRun {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
        input();
        SolutionRun go=new SolutionRun();
        go.Run();
	}

}
