package main.view;

import javax.swing.*;
import java.util.ArrayList;

public class LeftPanel extends JPanel{

    private int numOfWindows;
    private ArrayList<WindowPanel> windows = new ArrayList<>();


    public LeftPanel(int numOfWindows){

        this.numOfWindows = numOfWindows;

        initializeComponents();
    }

    private void initializeComponents(){

        initializePanel();
        fillPanel();

    }

    public void initializePanel(){

        JScrollPane scroll = new JScrollPane(this);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }

    public void fillPanel(){
        for(int i =0; i<this.numOfWindows;i++){
            WindowPanel windowPanel = new WindowPanel(i);
            this.add(windowPanel);
            windows.add(windowPanel);
        }
    }

    public ArrayList<WindowPanel> getWindows() {
        return windows;
    }

    public void setWindows(ArrayList<WindowPanel> windows) {
        this.windows = windows;
    }


}
