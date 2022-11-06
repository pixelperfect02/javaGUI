import nz.sodium.*;
import javax.swing.*;
import swidgets.*;
import java.awt.FlowLayout;
import javax.swing.table.*;

public class GpsGui {

    public static void main(String[] args){

        //GUI frame heading
        JFrame frame = new JFrame("GUI");
        //Frame Size
        frame.setSize(700, 700);
        //On Pressing GUI cross stop running the application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Code to Initialise the GPS Service from Example.java
        GpsService serv = new GpsService();
        // Retrieve Event Streams
        Stream<GpsEvent>[] streams = serv.getEventStreams(); 

        //Code to Attach a handler method to each stream from Example.java
        for(Stream<GpsEvent> s : streams)
        {
            s.listen((GpsEvent ev) -> { 
            //Printing the GPS Data on the terminal
            System.out.println(ev);
             });

            //Printing Tracker with numbers
            Cell<String> tNum;
            tNum = s.map((GpsEvent ev) -> 
            {
            String[] expected;
            //Using split to remove space after Tracker to directly print Latitude
            expected = ev.toString().split(" ");
            
            String t1String;
            //Printing Latitute as it is at place 2 in the array
            t1String = (expected[2]);
            //t1String is printing Latitude
            return t1String;  
            }).hold(" ");
            
            //Label for printing Latitude
            SLabel lA = new SLabel(tNum);
            frame.add(lA);
            //Layout for printing
            frame.setLayout(new FlowLayout());
            // Setting Gui as visible
            frame.setVisible(true);
        }
     }
 }
 
