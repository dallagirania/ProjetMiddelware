package com.site.MDL.model;

public class Employee {
    private String empId;
    private String empName;
    private String empPrenom;
    private String empPoste;
    private String empSalaire;
    private String empEmail;
    private String empTel;


    public String getEmpPrenom() {
        return empPrenom;
    }

    public void setEmpPrenom(String empPrenom) {
        this.empPrenom = empPrenom;
    }

    public String getEmpPoste() {
        return empPoste;
    }

    public void setEmpPoste(String empPoste) {
        this.empPoste = empPoste;
    }

    public String getEmpSalaire() {
        return empSalaire;
    }

    public void setEmpSalaire(String empSalaire) {
        this.empSalaire = empSalaire;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpTel() {
        return empTel;
    }

    public void setEmpTel(String empTel) {
        this.empTel = empTel;
    }


    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Override
    public String toString() {
        return "Employee [empId=" + empId + ", empName=" + empName + "]";
    }
}
