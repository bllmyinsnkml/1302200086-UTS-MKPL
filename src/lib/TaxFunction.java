package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */

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
