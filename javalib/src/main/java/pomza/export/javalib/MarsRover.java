package pomza.export.javalib;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import sun.font.CreatedFontTracker;
import sun.font.FontResolver;
import sun.security.util.ArrayUtil;

public class MarsRover {



    public static void  main(String[] args){
        ArrayList<String> new_coord=new ArrayList<>();
        Scanner scanner=new Scanner(System.in);

        System.out.println("How many rover?");
        int amount_rovers = Integer.parseInt(scanner.nextLine());
        String[][] rovers=new String[amount_rovers][3];

        System.out.println("Enter the upper-right coordinates of area");
        String border_line=scanner.nextLine();
        String[] border=border_line.split(" ");

        for (int i=0;i<amount_rovers;i++){
            System.out.println("Enter the position of rover");
            String coord_line=scanner.nextLine().toUpperCase();
            String[] coord=coord_line.split(" ");

            System.out.println("Enter the route");
            String route=scanner.nextLine().toUpperCase();
            char[] rover_line=route.toCharArray();

            Router router= new Router();

            for (int j=0;j<rover_line.length;j++){
                new_coord=router.direction(coord,String.valueOf(rover_line[j]));
                if (Integer.parseInt(new_coord.get(0))>Integer.parseInt(border[0]) || Integer.parseInt(new_coord.get(1))>Integer.parseInt(border[1])){
                    System.out.println("The rover will be out of area");
                }else{
                    coord[0]=new_coord.get(0);
                    coord[1]=new_coord.get(1);
                    coord[2]=new_coord.get(2);
                }
            }
            rovers[i][0]=coord[0];
            rovers[i][1]=coord[1];
            rovers[i][2]=coord[2];
        }

        for (int i=0;i<amount_rovers;i++){
            System.out.println(rovers[i][0] + " " + rovers[i][1] + " " + rovers[i][2]);
        }
    }
}

class Router{
    int new_coord3;
    ArrayList<String> c_list=new ArrayList<>();

    public ArrayList<String> direction(String[] coord, String way){
        c_list.clear();
        if (way.equalsIgnoreCase("L")){
            c_list.add(coord[0]);
            c_list.add(coord[1]);
            c_list.add(face_to(coord[2],way));
        }else if (way.equalsIgnoreCase("R")){
            c_list.add(coord[0]);
            c_list.add(coord[1]);
            c_list.add(face_to(coord[2],way));
        }else if (way.equalsIgnoreCase("M")){
            c_list=move_forward(coord[0],coord[1],coord[2]);
        }
        return c_list;
    }



    public String face_to(String coord, String way){
        String s="";
        ArrayList<String> points=new ArrayList<String>();
        points.clear();
        points.add("W");
        points.add("N");
        points.add("E");
        points.add("S");

        int f_coord= points.indexOf(coord);
        
        if (way.equalsIgnoreCase("L")){
            if (f_coord==0){
                new_coord3=3;
            }else{
                new_coord3=f_coord-1;
            }
        }else if (way.equalsIgnoreCase("R")){
            if (f_coord==3){
                new_coord3=0;
            }else{
                new_coord3=f_coord+1;
            }
        }

        s=String.valueOf(points.get(new_coord3));

        return s;
    }



    public ArrayList<String> move_forward(String coord1, String coord2, String coord3){
        ArrayList<String> s=new ArrayList<>();
        s.clear();
        if (coord3.equalsIgnoreCase("W")){
            coord1=String.valueOf(Integer.parseInt(coord1)-1);
        }else if (coord3.equalsIgnoreCase("N")){
            coord2=String.valueOf(Integer.parseInt(coord2)+1);
        }else if (coord3.equalsIgnoreCase("E")){
            coord1=String.valueOf(Integer.parseInt(coord1)+1);
        }else if (coord3.equalsIgnoreCase("S")){
            coord2=String.valueOf(Integer.parseInt(coord2)-1);
        }

        s.add(coord1);
        s.add(coord2);
        s.add(coord3);
        return s;
    }
}
