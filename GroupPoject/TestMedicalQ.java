import java.util.*;
import java.text.*;
import java.io.*;
import javax.swing.*;

public class TestMedicalQ
{
    public static void main (String[] args)
    {
        try
        {
            Scanner sc =  new Scanner(System.in);
            DecimalFormat df = new DecimalFormat("0.00");

            FileReader fr = new FileReader("medicalPatient.txt");
            BufferedReader br = new BufferedReader(fr);

            sc.useDelimiter("\n");

            String inData = null;
            String name, icNo, phoneNo, bloodType, checkUp;
            int systolic, diastolic;
            double height, weight;
            char packageIni;

            Queue medicalQ = new Queue();
            System.out.println("\t\t\t\t\t\t\t\t\t\t|----------------------------|");
            System.out.println("\t\t\t\t\t\t\t\t\t\t|      Medical Check Up      |");
            System.out.println("\t\t\t\t\t\t\t\t\t\t|----------------------------|");
            System.out.println("\n");

            while ((inData = br.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(inData,";");

                name = st.nextToken();
                icNo = st.nextToken();
                phoneNo = st.nextToken();
                bloodType = st.nextToken();
                systolic = Integer.parseInt(st.nextToken());
                diastolic = Integer.parseInt(st.nextToken());
                height = Double.parseDouble(st.nextToken());
                weight = Double.parseDouble(st.nextToken());
                checkUp = st.nextToken();

                if(checkUp.equalsIgnoreCase("YES"))
                {
                    packageIni = st.nextToken().charAt(0);

                    Medical med = new Medical(name, icNo, phoneNo, bloodType, systolic, diastolic, height, weight, checkUp, packageIni);
                    medicalQ.enqueue(med);
                }
                else
                {
                    Medical med = new Medical(name, icNo, phoneNo, bloodType, systolic, diastolic, height, weight, checkUp, '.');
                    medicalQ.enqueue(med);
                }
            }
            br.close();

            /**Display Patients*/
            Medical obj;
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Name \t\t\t\t\t IC Number \t  Number Phone \t   Blood Type \t Systolic \tDiastolic \t Height \t Weight\t  Check-Up \tAdd-On");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            Queue temp = new Queue();
            while(!medicalQ.isEmpty())
            {
                obj = (Medical)medicalQ.dequeue();
                System.out.println(obj.toString());

                temp.enqueue(obj);
            }
            while(!temp.isEmpty())
            {
                medicalQ.enqueue(temp.dequeue());
            }

            System.out.println("========================================================================================================================================================================================================");
            System.out.println("\n");
            
            System.out.println("=======================================================================================================================================================================================================");
            
            /**Search Patients*/
            String search = null;
            search = JOptionPane.showInputDialog("IC Number To Be Search : ");
            Medical fObj = null;
            boolean found = false;
            while(!medicalQ.isEmpty())
            {
                obj = (Medical)medicalQ.dequeue();
                if (obj.getIcNo().equalsIgnoreCase(search))
                { 
                    found = true;

                    String newPhone = null;
                    double newHeight=0.00, newWeight=0.00;
                    int update = 0;
                    update = Integer.parseInt(JOptionPane.showInputDialog("Update [1]Phone Number  [2]Height  [3]Weight"));
                    if(update == 1)
                    {
                        newPhone = JOptionPane.showInputDialog("Phone Number To Be Update : ");
                        obj.setPhone(newPhone);
                    }
                    else if(update == 2)
                    {
                        newHeight = Double.parseDouble(JOptionPane.showInputDialog("Height To Be Update : "));
                        obj.setHeight(newHeight);
                    }
                    else if(update == 3)
                    {
                        newWeight = Double.parseDouble(JOptionPane.showInputDialog("Weight To Be Update : "));
                        obj.setWeight(newWeight);
                    }
                    
                    fObj = obj;
                }
                temp.enqueue(obj);
            }
            while(!temp.isEmpty())
            {
                medicalQ.enqueue(temp.dequeue());
            }
            if (found)
            {
                System.out.println("\t\t\t\t\t\t\t\t\t\tPatient Update");
                System.out.println("\n");
                System.out.print(fObj.toString());
            }
            else
            {
                System.out.print("Patient Not Found !");
            }
            System.out.println();
            System.out.println("======================================================================================================================================================================================================");
            System.out.println("\n");
            
            /**Remove patient with no further checkup and move it into noMedLL*/
            Queue noMedicQ = new Queue();
            while(!medicalQ.isEmpty())
            {
                obj = (Medical)medicalQ.dequeue();
                if(obj.getFurtherCheckUp().equalsIgnoreCase("NO"))
                    noMedicQ.enqueue(obj);
                else
                    temp.enqueue(obj);
            }
            while(!temp.isEmpty())
            {
                medicalQ.enqueue(temp.dequeue());
            }

            /**display list in medicalQ*/
            System.out.println();
            System.out.println("Patients with further check-up (medLL)");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Name \t\t\t\t\t IC Number \t  Number Phone \t   Blood Type \t Systolic \tDiastolic \t Height \t Weight\t  Check-Up \tAdd-On");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            while(!medicalQ.isEmpty())
            {
                obj = (Medical)medicalQ.dequeue();
                System.out.println(obj.toString());

                temp.enqueue(obj);
            }
            while(!temp.isEmpty())
            {
                medicalQ.enqueue(temp.dequeue());
            }

            /**display list in noMedicQ*/
            System.out.println();
            System.out.println("Patients without further check-up (noMedLL)");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Name \t\t\t\t\t IC Number \t  Number Phone \t   Blood Type \t Systolic \tDiastolic \t Height \t Weight\t Check-Up");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            while(!noMedicQ.isEmpty())
            {
                obj = (Medical)noMedicQ.dequeue();
                System.out.println(obj.toString());

                temp.enqueue(obj);
            }
            while(!temp.isEmpty())
            {
                noMedicQ.enqueue(temp.dequeue());
            }
            /**Calculate customer with full body checkup and payment collected*/
            int count = 0;
            double totalprice = 0.00;
            while(!medicalQ.isEmpty())
            {
                obj = (Medical)medicalQ.dequeue();
                if (obj.getPackageInitial() == 'F' || obj.getPackageInitial() == 'f')
                {
                    count ++;
                    totalprice += obj.packagePrice();
                }
                temp.enqueue(obj);
            }
            while(!temp.isEmpty())
            {
                medicalQ.enqueue(temp.dequeue());
            }

            System.out.println();
            System.out.println("=================================================================================================================================================================================================");
            System.out.println("Total Patient(s) With Full Body Check Up : " + count);
            System.out.println("Total payment collected                  : RM " + totalprice);
            System.out.println("================================================================================================================================================================================================");

            /**Patient with further check up that have high blood pressure*/
            System.out.println();
            System.out.println("Patient(s) With Add-on Package With High Blood Pressure : ");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Name \t\t\t\t\tBlood Pressure Category \t\t  BMI");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            while(!medicalQ.isEmpty())
            {
                obj = (Medical)medicalQ.dequeue();
                if (obj.defineBlood().equalsIgnoreCase("Hypertension Stage 1")|| obj.defineBlood().equalsIgnoreCase("Hypertension Stage 2") || obj.defineBlood().equalsIgnoreCase("Hypertensive Crisis"))
                {
                    System.out.println(String.format("%-41s%-32s%-10s",obj.getName(),obj.defineBlood(),df.format(obj.calcBMI())));
                }
                temp.enqueue(obj);
            }
            while(!temp.isEmpty())
            {
                medicalQ.enqueue(temp.dequeue());
            }
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println(fnfe.getMessage());
        }
        catch (IOException io)
        {
            System.out.println(io.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}            
            