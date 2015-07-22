package company.example.ecash;



public class StaticData {
	private static  int NCoin=77 ,ECoin=7 , m;
	private static String ip="192.168.224.21/ecash",ID,pass;
	private static String ip2="192.168.224.29/ecash";
//	private static String ip= "192.168.199.1/ecash",ID,pass;
//	private static String ip2=" 192.168.199.1/ecash";
	private static boolean f=true;

	public static int getNCoin() {
		return NCoin;
	}

	
	

	public static int getECoin() {
		return ECoin;
	}

	

	

	public static String getIp() {
		return ip;
	}

	public static String getID() {
		return ID;
	}

	public static void setID(String iD) {
		ID = iD;
	}




	public static int getM() {
		return m;
	}




	public static void setM(int m) {
		StaticData.m = m;
	}




	public static String getIp2() {
		return ip2;
	}




	public static void setIp2(String ip2) {
		StaticData.ip2 = ip2;
	}




	public static boolean isF() {
		return f;
	}




	public static void setF(boolean f) {
		StaticData.f = f;
	}




	public static String getPass() {
		return pass;
	}




	public static void setPass(String pass) {
		StaticData.pass = pass;
	}

	
	}
	

