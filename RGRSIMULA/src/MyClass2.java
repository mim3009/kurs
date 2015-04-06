import widgets.regres.Regres2;

public class MyClass2 extends Regres2 {

	@Override
	public double fi1(double x) {
		
		return Math.log(x)/Math.sqrt(x);
	}

	@Override
	public double fi2(double x) {
		
		return 1;
	}

	@Override
	public String getLabelName() {
		
		return "q=a*ln(w)/"+(char)0x221A+"(w)+b";
	}

}
