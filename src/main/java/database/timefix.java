package database;

public class timefix {
	
	
public String fixedTime(String time){
	if (time.contains("A")){
		String newtime = time.substring(0, (time.indexOf("A")-1));
		return newtime;
	}
	else{
		String newtime2 = time.substring(0, (time.indexOf("P")-1));
		String newtime_right = newtime2.substring(newtime2.indexOf(":"));
		String newtime_left = newtime2.substring(0, newtime2.indexOf(":"));
		int i = Integer.parseInt(newtime_left);
		String newtime = Integer.toString(i+12);
		newtime+=newtime_right;
		return newtime;
	}
}
}
