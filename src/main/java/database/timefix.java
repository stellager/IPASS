package database;

public class timefix {
	
	public String addedTime(String time, int duur){
		double uren = duur/3600;
		    int uur = (int) uren;
		    int minuten = (int) ((uren - uur) * 100);
		 
		    String newtime_right = time.substring((time.indexOf(":")+1));
			String newtime_left = time.substring(0, time.indexOf(":"));
			int i_left = Integer.parseInt(newtime_left);
			int i_right = Integer.parseInt(newtime_right);
			i_right+=minuten;
			if (i_right >59){
				uur+=1;
				i_right = i_left-60;
				i_left+=uur;
				if(i_left>23){
					i_left= i_left -24;
					String newtime = Integer.toString(i_left);
					newtime+= ":";
					newtime+=Integer.toString(i_right);
					return newtime+"xnextdate";
					
				}
				String newtime = Integer.toString(i_left);
				newtime+= ":";
				newtime+=Integer.toString(i_right);
				return newtime+"xsamedate";
				
				
			}
			i_left+=uur;
			if(i_left>23){
				i_left= i_left -24;
				String newtime = Integer.toString(i_left);
				newtime+= ":";
				newtime+=Integer.toString(i_right);
				return newtime+"xnextdate";
			}
			else{
				return "00:00"+"xfaileddate";
			}
			
	}
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
