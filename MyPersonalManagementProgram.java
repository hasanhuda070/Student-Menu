

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

//IdException class
class IdException extends Exception{
   public IdException(String str)throws Exception{
       super(str);
   }
}//end class

//Person class
abstract class Person {
  
   String fullName;
   String iD;
  
   public Person(){
      
   }
  
   public Person(String fullName, String iD) {
       super();
       this.fullName = fullName;
       this.iD = iD;
   }

   public String getFullName() {
       return fullName;
   }

   public void setFullName(String fullName) {
       this.fullName = fullName;
   }

   public String getID() {
       return iD;
   }

   public void setID(String iD) throws Exception {
       //logic
       if(iD.matches("[a-zA-Z]{2}[0-9]{4}")){
           this.iD = iD;
       }
       else{
           throw new IdException("Sorry Invalid id format-It has to be LetterLetterDigitDigitDigitDigit");
       }
      
   }

   public abstract void printInfo();

}

//Student class
class Student extends Person implements Serializable{
      
       double gpa;
       int creditHours;
      
       public Student(){
          
       }
      
       public Student(String fullName, String iD, double gpa, int creditHours) {
           super(fullName, iD);
          
           this.gpa = gpa;
           this.creditHours = creditHours;
       }
      
       public void tuitionInvoice(Student str){
           System.out.println("\tHere is the tuition invoice for " + str.getFullName() + " :");
           System.out.println("\t---------------------------------------------------------------------------");
           System.out.println("\t" + str.getFullName() + "\t" + str.getID());
          
           System.out.println("\tCredit Hours:"+str.getCreditHours()+" ($236.45/credit hour)");
           System.out.println("\tFees: $52");
           if(str.getGpa()>=3.85){
               double totalCreditHoursAmount=str.getCreditHours()*236.45;
               totalCreditHoursAmount=totalCreditHoursAmount+52;
               double stuOff=(double)0.25*totalCreditHoursAmount;
               totalCreditHoursAmount=(double)totalCreditHoursAmount-stuOff;
               System.out.println("Total payment: $"+totalCreditHoursAmount + "\t" + "($"+stuOff+" discount applied)");
           }
           else{
           double totalCreditHoursAmount=str.getCreditHours()*236.45;
           totalCreditHoursAmount=totalCreditHoursAmount+52;
           System.out.println("Total payment: $"+totalCreditHoursAmount + "\t" + "($0 discount applied)");
           System.out.println();
           }
       }

       public double getGpa() {
           return gpa;
       }

       public void setGpa(double gpa) {
           this.gpa = gpa;
       }

       public int getCreditHours() {
           return creditHours;
       }

       public void setCreditHours(int creditHours) {
           this.creditHours = creditHours;
       }

       @Override
       public void printInfo() {
           // TODO Auto-generated method stub
          
       }

   }
//Faculty class
class Faculty extends Person implements Serializable, Comparator<Faculty>{
      
      
      
       String department;
       String rank;
      
       public Faculty(){
          
       }
          
       public Faculty(String fullName, String iD, String department, String rank) {
           super(fullName, iD);
           this.department = department;
           this.rank = rank;
       }

       public String getDepartment() {
           return department;
       }

       public void setDepartment(String department) {
           this.department = department;
       }

       public String getRank() {
           return rank;
       }

       public void setRank(String rank) {
           this.rank = rank;
       }

       @Override
       public void printInfo() {
           System.out.println("\t\t---------------------------------------------------------------------------");
           System.out.println();
           System.out.println("\t\t");
       }
      
       public int compare(Faculty faculty1, Faculty faculty2) {

       String dept1 = faculty1.getDepartment().toUpperCase();
       String dept2 = faculty2.getDepartment().toUpperCase();

       //ascending order
       return dept1.compareTo(dept2);

       //descending order
       //return dept2.compareTo(dept1);
       }

   }

//main program
public class MyPersonalManagementProgram {

   public static void main(String[] args) {
       System.out.println("\t\t\t\tWelcome to my Personal Management Program");
       ArrayList list = new ArrayList();
  
       whilelabel:
       while(true){
          
          
           Scanner in = new Scanner(System.in);
          
           System.out.println("\tChoose one of the options:");
           System.out.println();
           System.out.println("1.Add a new Faculty member");
           System.out.println("2.Add a new Student");
           System.out.println("3.Print tuition invoice for a student");
           System.out.println("4.Print information of a faculty");
           System.out.println("5.Exit Program");
           System.out.println();
           System.out.print("\t\tEnter your selection: ");
           int selection=0;
           try{
               selection=in.nextInt();
           }catch(InputMismatchException ime){
               System.out.println();
               System.out.println("Invalid entry- please try again");
               System.out.println();
           }
          
           switch (selection)
           {
           case 1: Faculty faculty = new Faculty();
              
               System.out.println();
               System.out.println();
               System.out.println("Enter the faculty’s info:");
               in.nextLine();
               System.out.println();
               System.out.print("\tName of the faculty: ");
                   String name = in.nextLine();
                   faculty.setFullName(name);
                   System.out.print("\tID: ");
                   String id=in.next();
                   try {
                       faculty.setID(id);
                   } catch (Exception e) {
                       e.printStackTrace();
                       System.out.println();
                       break;
                   }
                  
                   System.out.print("\tRank: ");
                   String rank=in.next();
                   labelrank:
               while(true){
                   if(rank.equalsIgnoreCase("professor")||rank.equalsIgnoreCase("adjunct")){
                       faculty.setRank(rank);
                       break labelrank;
                      
                   }
                   else{
                       System.out.println("\tSorry entered rank ("+rank+") is invalid");
                       System.out.print("\tRank: ");
                       rank=in.next();
                       continue labelrank;
                   }
                   }//end while
                   System.out.print("\tDepartment: ");
                   String department=in.next();
                   faculty.setDepartment(department);
                   list.add(faculty);
                   System.out.println();
                   System.out.println("Thanks!");
                   System.out.println();
                   break;
                  
           case 2: Student student = new Student();
          
           System.out.println();
           System.out.println();
           System.out.println("Enter the student’s info:");
           in.nextLine();
           System.out.println();
           System.out.print("\tName of Student: ");
           String stdName = in.nextLine();  
           student.setFullName(stdName);
           System.out.print("\tID: ");
           String stdId=in.nextLine();
               try {
                   student.setID(stdId);
               } catch (Exception e) {
                   e.printStackTrace();
                   System.out.println();
                   break;
               }
               System.out.print("\tGpa: ");
               double stdGpa=in.nextDouble();
               student.setGpa(stdGpa);
               System.out.print("\tCredit hours: ");
               int stdCreditHours=in.nextInt();
               student.setCreditHours(stdCreditHours);
               list.add(student);
               System.out.println();
               System.out.println("Thanks!");
               System.out.println();
               break;
              
           case 3: System.out.println();
           System.out.print("\tEnter the student’s id: ");
           String stdIdForTuitionInvoice=in.next();
               Iterator itr=list.iterator();
              
                   while(itr.hasNext()){
                       Object obj=itr.next();
                       if(obj instanceof Student){
                           Student std = (Student) obj;
                           if(std.getID().equalsIgnoreCase(stdIdForTuitionInvoice)){
                               //logic
                               std.tuitionInvoice(std);
                               continue whilelabel;
                           }
                          
                       }
                   }
                   System.out.println();
                   System.out.println("Sorry-student not found!");
                   System.out.println();
              
               break;
              
           case 4:
               System.out.println();
               System.out.print("\t\tEnter the faculty’s id: ");
               String faculId=in.next();
               Iterator itrF=list.iterator();
              
               while(itrF.hasNext()){
                   Object obj=itrF.next();
                   if(obj instanceof Faculty){
                       Faculty facul = (Faculty) obj;
                       if(facul.getID().equalsIgnoreCase(faculId)){
                           //logic
                           //std.tuitionInvoice(std);
                           System.out.println();
                           System.out.println("Faculty found:");
                           System.out.println();
                           //facul.printInfo();
                           System.out.println("\t\t---------------------------------------------------------------------------");
                           System.out.println("\t\t" + facul.getFullName());
                           System.out.println();
                           System.out.println("\t\t"+facul.getDepartment()+" Department, " + facul.getRank());
                           System.out.println();
                           System.out.println("\t\t---------------------------------------------------------------------------");
                           System.out.println();
                           continue whilelabel;
                       }
                      
                   }
               }
               System.out.println();
               System.out.println("\t\tSorry "+faculId+" doesn’t exist");
               System.out.println();
               break;
          
                  
           case 5:
               System.out.println();
               System.out.print("\t\tWould you like to create the report? (Y/N): ");
               String report=in.next();
               if(report.equalsIgnoreCase("y")){
                     
                   //write to file
               try{
               FileOutputStream writeData = new FileOutputStream("C:\\Users\\hudas\\Desktop\\report.dat");
               ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

               writeStream.writeObject(list);
               writeStream.flush();
               writeStream.close();
               System.out.println();
               System.out.println("\t\tYour file has been created!");
               System.out.println();
                           System.out.println("\t\tGoodbye!");
                           System.out.println();
                           System.out.println("Sample Report (report.dat)");
                           System.out.println();
                           Date date = new Date();
                           SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
                           String stringDate= DateFor.format(date);
                           //System.out.println(stringDate);
                           System.out.println("\t\tReport created on " +stringDate);
                           System.out.println("\t\t***********************");
                           System.out.println("\tFaculty Members (Sorted by Department)");
                           System.out.println("\t--------------------------------------------------");
                          Iterator itrFacultySorByDept=list.iterator();
                           int i=0;
                          while(itrFacultySorByDept.hasNext()){
                               Object obj=itrFacultySorByDept.next();
                               if(obj instanceof Faculty){
                                   Faculty facultyobj = (Faculty) obj;
                                   i++;
                                   System.out.println("\t\t" + i + ". " +facultyobj.getFullName() );
                                   System.out.println("\t\tID: " + facultyobj.getID());
                                   System.out.println("\t\t" + facultyobj.getRank() + "," + facultyobj.getDepartment());
                                   System.out.println();
                               }
                              
                           }
                         
                          System.out.println("\tStudents");
                           System.out.println("\t-----------");
                         
                           Iterator students=list.iterator();
                           int stui=0;
                          while(students.hasNext()){
                               Object obj=students.next();
                               if(obj instanceof Student){
                                   Student studentobj = (Student) obj;
                                   stui++;
                                   System.out.println("\t\t" + stui + ". " +studentobj.getFullName() );
                                   System.out.println("\t\tID: " + studentobj.getID());
                                   System.out.println("\t\tGpa: " + studentobj.getGpa());
                                   System.out.println("\t\tCredit hours: " + studentobj.getCreditHours());
                                   System.out.println();
                               }
                              
                           }
                         

               }catch (IOException e) {
               e.printStackTrace();
               }
                     
               }
               System.exit(0);
          
           }
      
   }
}
}

