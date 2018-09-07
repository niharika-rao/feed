
public class dateCheck {

	public static void main(String[] args) {
		String date = "09/13/2018";
		DateValidator valid = new DateValidator();
		
		if(valid.validate(date)){
			System.out.println("Valid date");
		}
		else{
			System.out.println("Invalid date");
		}
	}

}
