package com.site.MDL.serviceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.MDL.model.Employee;

@Service
public class EmployeeServiceImpl extends RouteBuilder {

        @Autowired
        DataSource dataSource;

        public DataSource getDataSource() {
            return dataSource;
        }

        public void setDataSource(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        public void configure() throws Exception {

            //Insert Route
            from("direct:insert").process(new Processor() {
                public void process(Exchange xchg) throws Exception {
                    //Take the Employee object from the exchange and create the insert query
                    Employee employee = xchg.getIn().getBody(Employee.class);
                    String query = "INSERT INTO employee(empId,empName,empPrenom,empPoste,empEmail,empTel,empSalaire)values('" + employee.getEmpId() + "','"
                            + employee.getEmpName()+"','"+employee.getEmpPrenom() + "','" +employee.getEmpPoste() +"','" +employee.getEmpEmail() +"','" +employee.getEmpTel() +"','"+employee.getEmpSalaire() + "')";
                    // Set the insert query in body and call camel jdbc
                    xchg.getIn().setBody(query);
                }
            }).to("jdbc:dataSource");

            // Select Route
            from("direct:select").setBody(constant("select * from Employee")).to("jdbc:dataSource")
                    .process(new Processor() {
                        public void process(Exchange xchg) throws Exception {
                            //the camel jdbc select query has been executed. We get the list of employees.
                            ArrayList<Map<String, String>> dataList = (ArrayList<Map<String, String>>) xchg.getIn()
                                    .getBody();
                            List<Employee> employees = new ArrayList<Employee>();
                            System.out.println(dataList);
                            for (Map<String, String> data : dataList) {
                                Employee employee = new Employee();
                                employee.setEmpId(data.get("empId"));
                                employee.setEmpName(data.get("empName"));
                                employee.setEmpPrenom(data.get("empPrenom"));
                                employee.setEmpPoste(data.get("empPoste"));
                                employee.setEmpEmail(data.get("empEmail"));
                                employee.setEmpTel(data.get("empTel"));
                                employee.setEmpSalaire(data.get("empSalaire"));
                                employees.add(employee);
                            }
                            xchg.getIn().setBody(employees);
                        }
                    });


        }
    }

