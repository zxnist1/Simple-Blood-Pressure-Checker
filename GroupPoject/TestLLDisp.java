import java.util.Scanner;
import java.util.StringTokenizer;
import java.text.*;
import java.io.*;
import javax.swing.*;
public class TestLLDisp
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
            System.out.println("\t\t\t\t\t\t\t\t\tMedical Check Up");
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
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Name \t\t\t\t\t Ic Number \t  Number Phone \t   Blood Type \t Systolic \tDiastolic \t Height \t Weight \t  Check-Up \t Add-On");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            medLL.display();
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

