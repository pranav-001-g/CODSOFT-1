import java.util.Scanner;
class Counter{
    public static void main(String argu[]){
        Scanner in =new Scanner(System.in);
        System.out.println("Enter the text:");
        String t =in.nextLine();
        String array[]=new String[100];
        String temp="";
        int count=0;
        int j=0;
       
       for(int i=0;i<t.length();i++){
           temp=temp+t.charAt(i);
         if(t.charAt(i)==' '|| i==t.length()-1){
            array[j]=temp;
            j++;
            temp="";
            count=count+1;
         }
       }
       
       for(int i=0;i<count;i++){
         int cf=1;
        for(int k=i+1;k<count;k++){
          if(array[i]==array[k]){
            cf++;
          }
          
        }System.out.println("Frequency is "+array[i]+" "+cf);
       }
       System.out.println("=======================================================");
       System.out.println("The List Of Words Present In The Array:");
       for(int i=0;i<count;i++){
         System.out.println((i+1)+". "+array[i]);
       }
       System.out.println("=======================================================");
       System.out.println("Total count of words in a string is :\n"+count);
    in.close();
    }
}