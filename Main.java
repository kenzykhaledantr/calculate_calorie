import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N;
        Scanner sc = new Scanner(System.in);


        Boolean t= true;
        String nameHighestBMI=null;
        int ageHighestBMI=0;
        Double weightHighestBMI=0.0;
        Double hightHighestBMI=0.0;
        Double HighestBMI=0.0;
        Double HighestBMR=0.0;
        String genderHighestBMI=null;
        String StateweightHigestBMI=null;
        int Count=0;
        double TotalBMRF=0.0;
        double TotalBMRM=0.0;
        int FemaleCount=0;
        int MaleCount=0;
        double AverageMale = 0.0;
        double AverageFemale=0.0;



        while (t) {
            try {
                System.out.print("Enter the number of persons: ");
                N = sc.nextInt();
                if ( N<0 ) {
                    throw new IllegalArgumentException("You entered an invalid number.");
                }


                File fr = new File("report.txt");
                Scanner sx = new Scanner(fr);
                PrintWriter pr = new PrintWriter(fr);
                DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("Report.dat"));

                for (int i = 1; i <= N; i++) {
                    System.out.println("Enter data for Person " + i + ":");
                    System.out.print("Name: ");
                    String name = sc.next();

                    System.out.print("Age (in years): ");
                    int age = sc.nextInt();
                    if(age < 0 ){
                        throw new IllegalArgumentException("You entered an negative number in the age section. Try again !!");
                    }

                    System.out.print("Weight (in kilograms): ");
                    double weight = sc.nextDouble();
                    if(weight < 0 ){
                        throw new IllegalArgumentException("You entered an negative number in the weight section. Try again !!");
                    }

                    System.out.print("Height (in centimeters): ");
                    double height = sc.nextDouble();
                    if(height  < 0 ){
                        throw new IllegalArgumentException("You entered an negative number in the height section. Try again !!");
                    }


                    System.out.print("Gender (M/F): ");
                    String gender = sc.next();

                    String StateWeight;
                    Double BMI =weight / ((height / 100) * (height / 100));

                    if (BMI < 18.5) {
                        StateWeight = "Underweight";
                    } else if (BMI >= 18.5 && BMI < 25) {
                        StateWeight = "Optimal weight";


                    } else {
                        StateWeight = "Overweight";
                    }






                    Double BMR;
                    if (gender.equalsIgnoreCase("M")) {
                        BMR = 66 + 13.7 * weight + 5 * height - 6.8 * age;

                        if(BMI >= 18.5 && BMI < 25){
                            MaleCount++;
                            TotalBMRM+=BMR;
                        }



                    } else if (gender.equalsIgnoreCase("F")) {
                        BMR = 655 + 9.6 * weight + 1.8 * height - 4.7 * age;
                        if(BMI >= 18.5 && BMI < 25){
                            FemaleCount++;
                            TotalBMRF+=BMR;
                        }


                    } else {
                        throw new IllegalArgumentException("Invalid gender input.");
                    }





                        if (BMI > HighestBMI) {
                            HighestBMI = BMI;
                            HighestBMR=BMR;
                            nameHighestBMI = name;
                            ageHighestBMI = age;
                            weightHighestBMI = weight;
                            hightHighestBMI = height;
                            genderHighestBMI = gender;
                            StateweightHigestBMI = StateWeight;


                        }




                    pr.println("\nName : "+name);
                    pr.println("\nAge : "+age);
                    pr.println("\nWeight : "+weight);
                    pr.println("\n Height : "+height);
                    pr.println("\nBMR : "+BMR);
                    pr.println("\nBMI : "+BMI);
                    pr.println("\n Weight State : "+StateWeight);


                    if(i==N)
                    {
                        t=false;
                    }



                }
                pr.close();
                sx.close();
                if(MaleCount>0)
                {
                    AverageMale=TotalBMRM/MaleCount;
                }
                else {
                    AverageMale=0.0;
                }
                if(FemaleCount>0)
                {
                    AverageFemale=TotalBMRF/FemaleCount;
                }
                else {
                    AverageFemale=0.0;
                }




                dataOutputStream.writeUTF("Name: " + nameHighestBMI+ "\n\n");
                dataOutputStream.writeUTF("Age : " + ageHighestBMI+ "\n\n" );
                dataOutputStream.writeUTF("Weight = " + weightHighestBMI+ "\n\n");
                dataOutputStream.writeUTF("Hight = " + hightHighestBMI+ "\n\n");
                dataOutputStream.writeUTF("BMR = " + HighestBMR+ "\n\n");
                dataOutputStream.writeUTF("BMI = " + HighestBMI+ "\n\n");
                dataOutputStream.writeUTF("Weight Status: " + StateweightHigestBMI + "\n\n");
                dataOutputStream.writeUTF("Average Male : " + AverageMale + "\n\n");
                dataOutputStream.writeUTF("Average Female : " + AverageFemale + "\n\n");
                dataOutputStream.close();

                System.out.println("Name: " + nameHighestBMI);
                System.out.println("Age : " + ageHighestBMI);
                System.out.println("Weight = " + weightHighestBMI);
                System.out.println("Hight = " + hightHighestBMI);
                System.out.println("BMR = " + HighestBMR);
                System.out.println("BMI = " + HighestBMI);
                System.out.println("Weight Status: " + StateweightHigestBMI) ;
                System.out.println("Average Male : " + AverageMale );
                System.out.println("Average Female : " + AverageFemale );



            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("You entered an invalid input.Write again !!");
                sc.nextLine();

            }

        }

    }
}