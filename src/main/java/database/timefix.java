package database;

public class timefix {
public String fixedTime(String time){
	if (time.contains("A")){
		String newtime = time.substring(0, (time.indexOf("A")-1));
		return newtime;
	}
	else{
		String newtime = time.substring(0, (time.indexOf("P")-1));
		return newtime;
	}
}
}
