package phoneStore;

import phoneStore.control.AdminControl;
import phoneStore.control.LoginControl;
import phoneStore.control.UserControl;
import phoneStore.window.StoreWindow;

public class StoreStart {
	
	public LoginControl lw;
	public AdminControl aw;
	public StoreWindow sw;
	public UserControl uw;
	
	
	
	public StoreStart() {
		sw = new StoreWindow(this);
		lw = new LoginControl(this);
		aw = new AdminControl(this);
		uw = new UserControl(this);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StoreStart ss = new StoreStart();
		System.out.println(ss.sw.storespec.getStoreid() + ss.sw.storespec.getStorelevel());
	}

}
