import java.lang.Math;
public class Medical
{
    private String name;
    private String icNo;
    private String phone;
    private Blood blood;
    private double height;
    private double weight;
    private String furtherCheckUp;
    private char packageInitial;

    public Medical (String name, String icNo, String phone, String bloodType, int systolic, int diastolic,  double height, double weight, String furtherCheckUp, char packageInitial)
    {
        this.name = name;
        this.icNo=icNo;
        this.phone=phone;
        blood = new Blood(bloodType,systolic,diastolic);
        this.height=height;
        this.weight=weight;
        this.furtherCheckUp=furtherCheckUp;
        this.packageInitial=packageInitial;
    }
    
    public void setMedical (String name, String icNo, String phone, String bloodType, int systolic, int diastolic, double height, double weight, String furtherCheckUp, char packageInitial)
    {
        this.name = name;
        this.icNo=icNo;
        this.phone=phone;
        blood = new Blood(bloodType,systolic,diastolic);
        this.height=height;
        this.weight=weight;
        this.furtherCheckUp=furtherCheckUp;
        this.packageInitial=packageInitial;
    }
    
    public void setPhone (String phone )
    {
       this.phone=phone; 
    }
    public void setHeight (double height)
    {
       this.height=height; 
    }
    public void setWeight (double weight)
    {
       this.weight=weight; 
    }
    
    public String getName (){return name;}
    public String getIcNo (){return icNo;}
    public String getPhone (){return phone;}
    public String getBloodType(){return blood.getBloodType();}
    public int getDiastolic(){return blood.getDiastolic();}
    public int getSystolic(){return blood.getSystolic();}
    public double getHeight (){return height;}
    public double getWeight (){return weight;}
    public String getFurtherCheckUp (){return furtherCheckUp;}
    public char getPackageInitial (){return packageInitial;}
    
    public double calcBMI ()
    {
        double body;
        double heightM;
        
        heightM = height / 100;
        body = weight / (Math.pow(heightM,2));
        
        return body;
    }
    
    public String defineBlood ()
    {
        String category;
        category = blood.defineBlood();
        
        return category;
    }
    
    public String packageName ()
    {
        String packageName = null;
        
        if (packageInitial == 'E' || packageInitial == 'e')
            packageName = "Eyesight Test";
        else if (packageInitial == 'F' || packageInitial == 'f')
            packageName = "Full Body CheckUp";
        else if (packageInitial == 'U' || packageInitial == 'u')
            packageName = "Urine Test";
        else if (packageInitial == 'X' || packageInitial == 'x')
            packageName = "Abdomen X-ray";
            
        return packageName;        
    }

    
    public double packagePrice ()
    {
        double price = 0;
        int age;
        int genderCheck; 
        
        age = Integer.parseInt(icNo.substring(0,2));
        if (age < 23)
            age = 2023 - (2000 + age);
        else 
            age = 2023 - (1900 + age);
            
        genderCheck = icNo.charAt(11);
        if (packageInitial == 'F' || packageInitial == 'f')
        {
            if (genderCheck % 2 == 1)
            {
                if ( age < 51)
                    price = 500;
                else
                    price = 650;
            }
            else
            {
                if ( age < 51)
                    price = 900;
                else
                    price = 1100;
            }
        }
        else if (packageInitial == 'E' || packageInitial == 'e')
            price = 50;
        else if (packageInitial == 'U' || packageInitial == 'u')
            price = 30;
        else if (packageInitial == 'X' || packageInitial == 'x')
            price = 65;
        
        return price;
    }
    
    public String toString()
    {
        if(furtherCheckUp.equalsIgnoreCase("YES"))
            return (String.format("%-40s%-18s%-20s%-13s%-15s%-15s%-17s%-10s%-10s%-5s",name,icNo,phone,getBloodType(),getSystolic(),getDiastolic(),height,weight,furtherCheckUp,packageName()));
        else
            return (String.format("%-40s%-18s%-20s%-13s%-15s%-15s%-17s%-10s%-5s",name,icNo,phone,getBloodType(),getSystolic(),getDiastolic(),height,weight,furtherCheckUp));
    }
}

