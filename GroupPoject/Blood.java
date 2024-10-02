public class Blood
{
    private String bloodType;
    private int systolic;
    private int diastolic;
    
    public Blood (String bloodType, int systolic, int diastolic)
    {
        this.bloodType=bloodType;
        this.systolic=systolic;
        this.diastolic=diastolic;
    }
    public void setBlood (String bloodType,  int systolic, int diastolic)
    {
        this.bloodType=bloodType;
        this.systolic=systolic;
        this.diastolic=diastolic;
    }
    public String getBloodType (){return bloodType;}
    public int getSystolic (){return systolic;}
    public int getDiastolic (){return diastolic;}
    public String defineBlood ()
    {
        String category = null;
        
        if (diastolic < 80 && systolic < 120)
            category = "Normal";
        else if (diastolic < 80 && systolic < 130)
            category = "Elevated";
        else if (diastolic < 90 || systolic < 140)
            category = "Hypertension Stage 1";
        else if ((diastolic > 90 && diastolic < 120)||(systolic > 120 && systolic < 180))
            category = "Hypertension Stage 2";
        else if (diastolic > 120 || systolic > 180)
            category = "Hypertensive Crisis";
        else
            category = "Healthy";
    
        return category;
    }
}
