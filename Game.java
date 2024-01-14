import java.util.*;
class Game{
    public static void main(String argu[]){
       Scanner obj = new Scanner(System.in);
       boolean tryagain = true;

      for(int j=0;tryagain=true;j++){
        System.out.println("One random number is generated(0-100)"); 
        Random o = new Random();
        int n=o.nextInt(100);
        System.out.println("###### NUMBER GAME IS STARTED ######");
        System.out.println("(You Have Only 4 Attempts !!!)");

       for(int i=1;i<=4;i++){
          int choice=obj.nextInt();
        if(choice<100){
          if(choice==n){
            System.out.println("!!!!!YOU WON!!!!!!"+"\n"+"The number is "+n+"\n");
            break;
          }
           else if(i==4){
            System.out.println("Game Is Over\nThe Number Was "+n+"\n");
           }
           else if(choice!=n){
            System.out.println("Sorry please try again\n(Only "+(4-i)+" Attemps are left!!!!)");
           }
        }
        else{
            System.out.println("Number is not valid");
        }
    }
    System.out.print("Do you want to try again ? (y/n): ");
    String tryAgain = obj.next();

    if (!tryAgain.equalsIgnoreCase("y")) {
        System.out.println("Thanks for playing!!!!");
        break;
      }
    }
    obj.close();
}
}