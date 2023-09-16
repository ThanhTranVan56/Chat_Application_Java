package com.app.swing;

import javax.swing.Icon;
import javax.swing.JProgressBar;

public class Progress extends JProgressBar {

    public ProgressType getProgessType() {
        return progessType;
    }

    public void setProgessType(ProgressType getProgessType) {
        this.progessType = getProgessType;
        repaint();
    }
    private ProgressType progessType = ProgressType.NONE;
    public Progress(){
        setOpaque(false);
        setUI(new ProgressCircleUI(this));
    }
    
    public static enum ProgressType{
        NONE, DOWN_FILE, CANCEL, FILE
    }
}
