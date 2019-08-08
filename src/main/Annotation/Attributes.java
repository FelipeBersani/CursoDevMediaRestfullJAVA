package main.Annotation;

import org.springframework.beans.factory.annotation.Required;

public class Attributes{

    public Attributes(String attribute1) {
        this.attribute1 = attribute1;
    }

    //@Required
    //@AttributeValidation
    private String attribute1;



    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }




    public static void main(String[] args) {

    }
}


