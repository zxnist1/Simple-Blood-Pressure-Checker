import java.util.Scanner;
import java.util.StringTokenizer;
import java.text.*;
import java.io.*;
import javax.swing.*;

public class TestMedical
{
    public static void main(String[] args)
    {
        try
        {
            Scanner sc =  new Scanner(System.in);

            FileReader fr = new FileReader("medicalPatient.txt");
            BufferedReader br = new BufferedReader (fr);
            
            DecimalFormat df = new DecimalFormat ("0.00");
            
            sc.useDelimiter("\n");
            
            String inData = null;
            String name, icNo,phone, bloodT;
            int systolic, diastolic;
            double height, weight;
            String checkUp;
            char packageIni;
            
            LinkedList medLL = new LinkedList();
            //System.out.println("\t\t\t\t\t\t\t\t\tMedical Check Up");
            System.out.println("\t\t\t\t\t\t░█▄█░█▀▀░█▀▄░▀█▀░█▀▀░█▀█░█░░░░░█▀▀░█░█░█▀▀░█▀▀░█░█░█░█░█▀█");
            System.out.println("\t\t\t\t\t\t░█░█░█▀▀░█░█░░█░░█░░░█▀█░█░░░░░█░░░█▀█░█▀▀░█░░░█▀▄░█░█░█▀▀");
            System.out.println("\t\t\t\t\t\t░▀░▀░▀▀▀░▀▀░░▀▀▀░▀▀▀░▀░▀░▀▀▀░░░▀▀▀░▀░▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀░░");
            while ((inData = br.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(inData,";");

                name = st.nextToken();
                icNo = st.nextToken();
                phone = st.nextToken();
                bloodT = st.nextToken();
                systolic = Integer.parseInt(st.nextToken());
                diastolic = Integer.parseInt(st.nextToken());
                height = Double.parseDouble(st.nextToken());
                weight = Double.parseDouble(st.nextToken());
                checkUp = st.nextToken();
                
                if(checkUp.equalsIgnoreCase("YES"))
                {
                    packageIni = st.nextToken().charAt(0);
                    
                    Medical m = new Medical(name, icNo, phone, bloodT, systolic, diastolic, height, weight, checkUp, packageIni);
                    medLL.insertAtFront(m);
                }
                else
                {
                    Medical m = new Medical(name, icNo, phone, bloodT, systolic, diastolic, height, weight, checkUp, '.');
                    medLL.insertAtFront(m);
                }
            }
            br.close();
            
            /**Display patient*/
            Medical obj;
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Name \t\t\t\t\t Ic Number \t  Number Phone \t   Blood Type \t Systolic \tDiastolic \t Height \t Weight \t  Check-Up \t Add-On");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            medLL.display();

            System.out.println("===================================================================================================================================================================================");
            
            /**Search patient*/
            System.out.println();
            String search = null;
            search = JOptionPane.showInputDialog("IC Number To Be Search : ");
            Medical fObj = null;
            boolean found = false;
            obj = (Medical)medLL.getFirst();
            while(obj != null)
            {
                if (obj.getIcNo().equalsIgnoreCase(search))
                { 
                    found = true;
                    
                    String option = null;
                    String update = null;
                    double dUpdate = 0.00;
                    
                    option = JOptionPane.showInputDialog("Insert Option below : \n[1]Update Phone Number \n[2]Update Height       \n[3]Update Weight       ");
                    
                    if (option.equalsIgnoreCase("1"))
                    {
                        update = JOptionPane.showInputDialog("Phone Number To Be Update : ");
                        obj.setPhone(update);
                    }
                    else if (option.equalsIgnoreCase("2"))
                    {
                        dUpdate = Double.parseDouble(JOptionPane.showInputDialog("Height To Be Update : "));
                        obj.setHeight(dUpdate);
                    }
                    else if (option.equalsIgnoreCase("3"))
                    {
                        dUpdate = Double.parseDouble(JOptionPane.showInputDialog("Weight To Be Update : "));
                        obj.setWeight(dUpdate);
                    }
                    fObj = obj;
                }
                obj = (Medical)medLL.getNext();   
            }
            System.out.println("Result of Updating");
            if (found)
            {
                System.out.print(fObj.toString());
            }
            else
            {
                System.out.println("Patient Not Found");
            }
            System.out.println();
            System.out.println("===================================================================================================================================================================================");
            /**Remove patient with no further checkup and move it into noMedLL*/
            LinkedList noMedLL = new LinkedList();
            LinkedList tempLL = new LinkedList();
            while (!medLL.isEmpty())
            {
                obj = (Medical)medLL.getFirst();
                if (obj.getFurtherCheckUp().equalsIgnoreCase("No"))
                    noMedLL.insertAtFront(obj);
                else
                    tempLL.insertAtFront(obj);
                medLL.remove(obj);
            }
            
            while (!tempLL.isEmpty())
            {
                obj = (Medical)tempLL.getFirst();
                medLL.insertAtFront(obj);
                tempLL.remove(obj);
            }
            
            /**Remove patient with no further checkup and move it into noMedLL*/
            System.out.println();
            System.out.println("Patients with further check-up (medLL)");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Name \t\t\t\t\t Ic Number \t  Number Phone \t   Blood Type \t Systolic \tDiastolic \t Height \t Weight \t  Check-Up \t Add-On");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            obj = (Medical)medLL.getFirst();
            while(obj != null)
            {
                System.out.println(obj.toString());
                obj = (Medical)medLL.getNext();
            }
            
            System.out.println();
            System.out.println("Patients with no further check-up (noMedLL)");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Name \t\t\t\t\t Ic Number \t  Number Phone \t   Blood Type \t Systolic \tDiastolic \t Height \t Weight \t  Check-Up \t ");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            obj = (Medical)noMedLL.getFirst();
            while(obj != null)
            {
                System.out.println(obj.toString());
                obj = (Medical)noMedLL.getNext();
            }
            
            /**Calculate customer with full body checkup and payment collected*/
            int count = 0;
            double totalprice = 0.00;
            obj = (Medical)medLL.getFirst();
            while(obj != null)
            {
                if (obj.getPackageInitial() == 'F' || obj.getPackageInitial() == 'f')
                {
                    count ++;
                    totalprice += obj.packagePrice();
                }
                obj = (Medical)medLL.getNext();
            }
            
            System.out.println();
            System.out.println("===================================================================================================================================================================================");
            System.out.println("Total Patient(s) With Full Body Check Up : " + count);
            System.out.println("Total payment collected                  : RM " + totalprice);
            System.out.println("===================================================================================================================================================================================");
            
            /**Patient with further check up that have high blood pressure and display the BMI*/
            System.out.println();
            System.out.println("Patient(s) With Add-on Package With High Blood Pressure : ");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Name \t\t\t\t\t Blood Pressure Category \t BMI");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            obj = (Medical)medLL.getFirst();
            while(obj != null)
            {
                if (obj.defineBlood().equalsIgnoreCase("Hypertension Stage 1") || obj.defineBlood().equalsIgnoreCase("Hypertension Stage 2") || obj.defineBlood().equalsIgnoreCase("Hypertensive Crisis"))
                {
                    System.out.println(String.format("%-40s%-33s%-5s",obj.getName(),obj.defineBlood(),df.format(obj.calcBMI())));
                }
                obj = (Medical)medLL.getNext();
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

