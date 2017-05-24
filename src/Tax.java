public class Tax {
 double grossIncome;
 String state;
 int dependents;


public Tax(int grossInc, String state, int dependents) {
	this.grossIncome = grossInc;
	this.state = state;
	this.dependents = dependents;
}


public double calcTax(){
	double taxTotal = 0;
	if (grossIncome < 30000){
		taxTotal = grossIncome * 0.05;
	}else{
		taxTotal = grossIncome * 0.06;
	}
	return taxTotal;
}
	
	
}
    
