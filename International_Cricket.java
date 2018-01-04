import java.util.ArrayList;
import java.util.Scanner;
class Squad{
    String country_name;
    String captain_name;
    ArrayList<String> players_list;
    ArrayList<String> batsman,bowlers,allRounders;
    String wicket_keeper_name;
    public Squad(String country_name,String captain_name,ArrayList players_list,ArrayList batsman,ArrayList bowlers,ArrayList allRounders,String wicket_keeper_name){
        this.country_name=country_name;
        this.captain_name = captain_name;
        this.players_list = players_list;
        this.batsman = batsman;
        this.bowlers = bowlers;
        this.allRounders = allRounders;
        this.wicket_keeper_name =   wicket_keeper_name;
    }
}
public class International_Cricket {
    static void rulesAndRegulations(){
        System.out.println("Rules And Regulations:");
        System.out.println("    1)No. of players in a Squad should be 11.\n    2)Captain name should start with a special character '*'\n    3)Batsman's name should ends with  'BA'\n    4)Bowler's name should ends with 'BO'\n    5)Wicket Keeper's name should ends with 'WC'\n    6)All Rounder name should ends with 'AL'");
    }
    static ArrayList createArrayList(){
        return new ArrayList<String>();
    }
    static Squad squadFormation(){
        Scanner scanner  = new Scanner(System.in);
        rulesAndRegulations();
        //country
        System.out.println("Enter country's name");
        String country = scanner.next();
        //playersList
        System.out.println("Enter player's names:");
        String captain_name="",wicket_Keeper="";
        ArrayList<String> players_list = createArrayList();
        ArrayList<String> batsman = createArrayList();
        ArrayList<String> bowlers = createArrayList();
        ArrayList<String>allrounders = createArrayList();
        for(int i=1;i<=5;i++) {
            String name = scanner.next();
            String identity = name.substring(name.length()-2,name.length());
			name = name.substring(0,name.length()-2);
            //check captaincy
            if(name.startsWith("*")){
                name = name.substring(1,name.length());
                if(captain_name.equals("")){
                    captain_name = name+"*";
                    players_list.add(captain_name);
                }else {
                    System.out.println("*********OOPS..! More than one captain is not allowed...Please try again..*********");
                    i--;
                }
            }
            switch (identity){
                case "BA":
                    batsman.add(name);
                    players_list.add(name);
                    break;
                case "BO":
                    bowlers.add(name);
                    players_list.add(name);
                    break;
                case "AL":
                    allrounders.add(name);
                    players_list.add(name);
                    break;
                case "WC":
                    if(!wicket_Keeper.equals("")) {
                        System.out.println("********* OOPS..! you've already entered wicketKeeprs name...Please try again..*********");
                        i--;
                    }
                    else {
                        wicket_Keeper = name;
                        players_list.add(name);
                    }
                    break;
                default:
                    System.out.println("********* OOPS..! Please enter names with identity...*********");
                    i--;
            }
        }
        Squad squad = new Squad(country,captain_name,players_list,batsman,bowlers,allrounders,wicket_Keeper);
        System.out.println("~~~~~~~~~~ Congratulations..! Your Squad is successfully created..~~~~~~~~~~");
        return squad;
    }
static void squadInfo(Squad squad){
        System.out.println("Country Name:- "+squad.country_name);
        System.out.println("Captain Name:- "+squad.captain_name);
        System.out.println("List of Batsman:-");
        System.out.println("    "+squad.batsman);
        System.out.println("List of Bowlers:-");
        System.out.println("    "+squad.bowlers);
        System.out.println("List of AllRounders:-");
        System.out.println("    "+squad.allRounders);
        System.out.println("Wicket Keeper Name:- "+squad.wicket_keeper_name);
                                   }
    public static void main(String[] args){
        Squad squad1=null,squad2 = null;
        squad1 = squadFormation();
        squad2 = squadFormation();
        squadInfo(squad1);
        squadInfo(squad2);
                                          }
}

