package database;

public class timefix {
public String fixedTime(String time){
	if (time.contains("A")){
		String newtime = time.substring(0, (time.indexOf("A")-1));
		return newtime;
	}
	else{
		String newtime2 = time.substring(0, (time.indexOf("P")-1));
		 int i =Integer.parseInt(newtime2);
		String newtime = Integer.toString(i+12);
		return newtime;
	}
}
}
