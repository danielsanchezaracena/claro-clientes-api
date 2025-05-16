package com.claro.clientesapi.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Add", namespace = "http://tempuri.org/")
public class CalculatorAddRequest {

    private int intA;
    private int intB;

    public CalculatorAddRequest() {}

    public CalculatorAddRequest(int intA, int intB) {
        this.intA = intA;
        this.intB = intB;
    }

    @XmlElement(name = "intA", namespace = "http://tempuri.org/")
    public int getIntA() {
        return intA;
    }

    public void setIntA(int intA) {
        this.intA = intA;
    }

    @XmlElement(name = "intB", namespace = "http://tempuri.org/")
    public int getIntB() {
        return intB;
    }

    public void setIntB(int intB) {
        this.intB = intB;
    }


}
