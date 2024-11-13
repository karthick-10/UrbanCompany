import javax.sound.midi.SysexMessage;
import java.util.*;
import java.lang.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
     public static LinkedHashMap<String,ArrayList<SubWorks>> primaryworks=new LinkedHashMap<>();
     public static  ArrayList<String> work= new ArrayList<>( Arrays.asList("Carpenter","House Cleaning","Driving","Plumber"));
     public static HashMap<String ,User>UserObj=new HashMap<>();
     public static void showSubWork(SubWorks s1)
     {
         System.out.print(s1.workName+"\t"+"₹"+s1.rs);
     }

     public static HashMap<String,ArrayList<String>> userName_password=new HashMap<>();
    public static  SubWorks ShowWorker( LinkedHashMap<String,ArrayList<SubWorks> >hm) {
        Scanner s = new Scanner(System.in);
//    System.out.println(hm.keySet());
        hm.keySet().stream().forEach(n -> System.out.println(work.indexOf(n) + 1 + " -> " + n));


        int selection = s.nextInt();
        String selectedwork="";
        if (selection < work.size()) {
            selectedwork= work.get(selection - 1);
        }
        else {
            System.out.println("Select a number between given Range");
            return ShowWorker(primaryworks);
        }
        System.out.println("You Have Selected "+selectedwork);
        int amt=0;
        ArrayList<SubWorks>arr=primaryworks.get(selectedwork);
        ArrayList<SubWorks>subWorks=new ArrayList<>(arr.stream().toList());
        HashMap<Integer,SubWorks> tempHashMap=new HashMap<>();
        for(int i=0;i<subWorks.size();i++)
        {
            int index=i+1;
            System.out.print(index+".");
            showSubWork(subWorks.get(i));
            if(i<subWorks.size()-1)
            {
            System.out.println();
            }
            tempHashMap.put(index,subWorks.get(i));

        }
        s.nextLine();
        int Selection=s.nextInt();
        SubWorks selctedSubworks=tempHashMap.get(Selection);
        return selctedSubworks;

    }
    public static void main(String[] args) {
        primaryworks.put("Carpenter", new ArrayList<>(Arrays.asList(new SubWorks("Furniture Assembly", 500),new SubWorks("Cabinet Repair", 70),new SubWorks("Wood Polishing", 350))));
        primaryworks.put("House Cleaning",new ArrayList<>(Arrays.asList(new SubWorks("Room Cleaning", 400),new SubWorks("Window & Door Cleaning", 300),new SubWorks("Carpet Cleaning", 300))));
        primaryworks.put("Driving",new ArrayList<>( Arrays.asList(new SubWorks("City Tour", 5000),new SubWorks("Acting Driver", 500),new SubWorks("Hourly Rental", 80))));
        primaryworks.put("Plumber",new ArrayList<>(Arrays.asList(new SubWorks("Pipe Installation",100),new SubWorks("Drain Cleaning",250),new SubWorks("Leak Fixing",200))));
        ArrayList<String> workers=new ArrayList<>();
        Scanner s=new Scanner(System.in);
        int sel;
        boolean state=true;
        while(state) {
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Press 1️⃣ to Add User 👤");
            System.out.println("Press 2️⃣ to Search Workers 🧑‍🏭");
            System.out.println("Press 3️⃣ to show the Cart 🛍️");
            System.out.println("Press 4️⃣ to Exit 🔴");
            sel=s.nextInt();
            s.nextLine();
            if(sel==1)
            {
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("Enter UserName-->");
                String tempUser=s.nextLine();
                System.out.print("Enter PassWord-->");
                String tempPass=s.nextLine();
                UserObj.put(tempUser,new User(tempUser,tempPass,new ArrayList<>(),0));
                UserObj.put(tempUser, new User(tempUser, tempPass, new ArrayList<>(),0));
                userName_password.put(tempUser+"@@"+tempPass,new ArrayList<String>());

            }
            else if(sel==2)
            {
               System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
                SubWorks s1=ShowWorker(primaryworks);

                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");

                int selectWorker=s.nextInt();
                selectWorker-=1;
                System.out.println("You are Selected "+s1.workName+" Which Cost ₹"+s1.rs);

                System.out.print("Enter Your User Name :");
                s.nextLine();
                String UserName=s.nextLine();
                System.out.print("Enter Your PassWord :");
                String PassWord=s.nextLine();
                String concat=UserName+"@@"+PassWord;
                if(userName_password.containsKey(concat))
                {
                    User user=UserObj.get(UserName);
                    user.arL.add(s1.workName);
                    int curSum= s1.rs;
                    user.total+=curSum;
                    System.out.println("Work Has Been Added to Your Cart  \n"+user.arL);
                }
                else {
                    System.out.println("UserName and PassWord Doesn't Match");
                }

            }
            else if(sel==3)
            {
                System.out.println("Enter Your User Name ");
                String UserName=s.nextLine();
                System.out.println("Enter Your PassWord");
                String PassWord=s.nextLine();
                String concat=UserName+"@@"+PassWord;
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
                if(userName_password.containsKey(concat)) {
                    if(UserObj.get(UserName).arL.size()!=0) {
                        System.out.println("Hello " + UserName + " 🖖\n" );
                        User TempUser=UserObj.get(UserName);
                        ArrayList<String> tempArr=TempUser.arL;
                        tempArr.stream().forEach(item-> System.out.println("- "+item));
                        System.out.println("Total Bill is "+TempUser.total);
                    }
                    else {
                        System.out.println("There is no Work Pending in Your Cart");
                    }
                }
                else {
                    System.out.println("UserName and PassWord Doesn't Match");
                }
            }
            else if(sel==4)
            {
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Tata Tata Bye Bye 🙋‍♂️");
                state=false;
            }
        }
    }
}