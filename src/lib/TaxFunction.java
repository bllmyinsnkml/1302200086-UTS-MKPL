package lib;

public class TaxFunction {

	private static final int JumlahBulan = 12;
    private static final double Pajak = 0.05;
    private static final int Lajang = 54000000;
    private static final int Menikah = Lajang + 4500000;
    private static final int MemilikiAnak = 4500000;
    private static final int MaksAnak = 3;
	
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		
		int tax = 0;

		int taxableIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking - deductible;
		
		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}
		
		int exemptedIncome = 0;
        if (isMarried) {
            exemptedIncome += Menikah;
        } else {
            exemptedIncome += Lajang;
        }

        int childExemption = Math.min(numberOfChildren, MaksAnak) * MemilikiAnak;
        exemptedIncome += childExemption;

        int taxableAmount = taxableIncome - exemptedIncome;
        if (taxableAmount > 0) {
            tax = (int) Math.round(Pajak * taxableAmount);
        }

        return Math.max(tax, 0);
    }
	
}
