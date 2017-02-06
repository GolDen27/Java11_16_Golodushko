package by.tc.parser.bean;

import java.util.ArrayList;

public class WelcomeFileList {

    ArrayList <String> welcomeFiles = new ArrayList<String>();

    public ArrayList<String> getWelcomeFiles() {
        return welcomeFiles;
    }

    public void setWelcomeFiles(ArrayList<String> welcomeFiles) {
        this.welcomeFiles = welcomeFiles;
    }

    public void addWelcomeFile(String welcomeFile) {
        welcomeFiles.add(welcomeFile);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        WelcomeFileList that = (WelcomeFileList) o;


        if (getWelcomeFiles()==null) {
            if (that.getWelcomeFiles() != null) {
                return false;
            }
        } else if (!getWelcomeFiles().equals(that.getWelcomeFiles())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return getWelcomeFiles().hashCode();
    }

    @Override
    public String toString() {
        return "WelcomeFileList{" +
                "welcomeFiles=" + welcomeFiles +
                '}';
    }
}
