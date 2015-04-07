package hardware.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
		{ 
			VMRUS_COM_C_MITest.class,
			VMRUS_COM_C_MTest.class,
			VMRUS_COM_P_MITest.class,
			VMRUS_COM_C_MTest.class,
			VMRUS_SFF_P_CITest.class, 
			VMRUS_SFF_P_CTest.class, 
			VMRUS_SFF_P_PITest.class, 
			VMRUS_TOC_C_MITest.class,
			VMRUS_TOC_CP_ITest.class,
			VMRUS_TOC_CPTest.class,
			VMRUS_TOC_P_ITest.class,
			VMRUS_TOC_P_MITest.class,
			hardware.test.funds.CardSlotTest.class,
			hardware.test.funds.CoinReceptacleTest.class,
			hardware.test.funds.CoinSlotTest.class,
			hardware.test.racks.CoinRackTest.class,
			hardware.test.racks.PopCanRackTest.class,
			hardware.test.ui.DeliveryChuteTest.class,
			hardware.test.ui.DisplayTest.class,
			hardware.test.ui.LockTest.class,
			hardware.test.ui.SelectionButtonTest.class
		})

public class AllTests {}
