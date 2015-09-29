package com.vhs.web.service;

import java.lang.Exception;
import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

/**
 * I am responsible for populating the configured datasource
 */
public class HsqldbSchemaAndDataPopulator implements InitializingBean {
    
    private JdbcTemplate template;
    
    /**
     *
     */
    public void afterPropertiesSet() throws Exception {
	try{
        Assert.notNull(template, "dataSource required");
        
        // add tables to represent admin core-domain instances.
        template.execute("CREATE TABLE USERS(USERNAME VARCHAR_IGNORECASE(50) NOT NULL PRIMARY KEY,"
                        + "PASSWORD VARCHAR_IGNORECASE(50) NOT NULL,"
                        + "ENABLED BOOLEAN NOT NULL);");
        template.execute("CREATE TABLE AUTHORITIES(USERNAME VARCHAR_IGNORECASE(50) NOT NULL,AUTHORITY VARCHAR_IGNORECASE(50) NOT NULL,CONSTRAINT FK_AUTHORITIES_USERS FOREIGN KEY(USERNAME) REFERENCES USERS(USERNAME));");
        template.execute("CREATE UNIQUE INDEX IX_AUTH_USERNAME ON AUTHORITIES(USERNAME,AUTHORITY);");
        
		// add tables to represent Project details instances.
		template.execute("CREATE TABLE PROJECTDETAILS(PROJECTNAME VARCHAR_IGNORECASE(50) NOT NULL PRIMARY KEY,"
                        + "VERSION VARCHAR_IGNORECASE(50) NOT NULL,"
						+ "LASTUPDATED TIMESTAMP NOT NULL,"
                        + "LASTUPDATEDUSER VARCHAR_IGNORECASE(50) NOT NULL);");
						
		// add tables to represent user access privilages.				
		template.execute("CREATE TABLE USERACCESS(USERNAME VARCHAR_IGNORECASE(50) NOT NULL,"
                        + "PROJECTNAME VARCHAR_IGNORECASE(50) NOT NULL,"
						+ "FOREIGN KEY (USERNAME) REFERENCES USERS(USERNAME),"
                        + "FOREIGN KEY (PROJECTNAME) REFERENCES PROJECTDETAILS(PROJECTNAME));");
						
        // insert data here
        template.execute("INSERT INTO USERS VALUES('disabled','disabled',FALSE);");
        template.execute("INSERT INTO USERS VALUES('admin','admin',TRUE);");
        template.execute("INSERT INTO USERS VALUES('user','password',TRUE);");
        template.execute("INSERT INTO USERS VALUES('test','test',TRUE);");
		
        template.execute("INSERT INTO AUTHORITIES VALUES('admin','ROLE_USER');");
        template.execute("INSERT INTO AUTHORITIES VALUES('admin','ROLE_ADMIN');");        
        template.execute("INSERT INTO AUTHORITIES VALUES('user','ROLE_USER');");        
        template.execute("INSERT INTO AUTHORITIES VALUES('test','ROLE_USER');");       
		
		template.execute("INSERT INTO PROJECTDETAILS VALUES('ABC Core Domain Model','1.0','20 August 2015','moorel4l');");
		template.execute("INSERT INTO PROJECTDETAILS VALUES('AccessibilityforSmartCity','1.0','20 August 2015','marcelot');");
		template.execute("INSERT INTO PROJECTDETAILS VALUES('Camera','1.0','20 August 2015','iamaziz');");
		template.execute("INSERT INTO PROJECTDETAILS VALUES('E-Gitarren','1.0','20 August 2015','janschacht');");
		template.execute("INSERT INTO PROJECTDETAILS VALUES('Aero','1.0','20 August 2015','M Horridge');");
		template.execute("INSERT INTO PROJECTDETAILS VALUES('Daily Travel Tips','1.0','20 August 2015','Natasha Noy');");
		template.execute("INSERT INTO PROJECTDETAILS VALUES('Agricultural ontology','1.1','20 August 2015','soonho');");
		template.execute("INSERT INTO PROJECTDETAILS VALUES('Fruits','1.1','20 August 2015','Jefster');");
		template.execute("INSERT INTO PROJECTDETAILS VALUES('Home Improvement Tools','1.1','20 August 2015','shellys');");
		template.execute("INSERT INTO PROJECTDETAILS VALUES('Allergy Detector','1.0','20 August 2015','nquevedo');");
		template.execute("INSERT INTO PROJECTDETAILS VALUES('Alzheimer Disease','1.0','20 August 2015','Kunwu');");
		template.execute("INSERT INTO PROJECTDETAILS VALUES('Google Maps Coordinates','1.0','20 August 2015','vinicius murakami');");
		template.execute("INSERT INTO PROJECTDETAILS VALUES('American comic books','1.0','20 August 2015','nathanpease');");
		template.execute("INSERT INTO PROJECTDETAILS VALUES('AnalisiDelFusto','1.1','20 August 2015','ndrscotti');");
		template.execute("INSERT INTO PROJECTDETAILS VALUES('bachat','1.1','20 August 2015','Kim joo chang');");
		template.execute("INSERT INTO PROJECTDETAILS VALUES('ICD-10','1.0','20 August 2015','jamoulle');");
		template.execute("INSERT INTO PROJECTDETAILS VALUES('Maps','1.0','20 August 2015','Rafael Amorim ');");
		template.execute("INSERT INTO PROJECTDETAILS VALUES('LOINC','1.0','20 August 2015','Vojtech Huser');");
		template.execute("INSERT INTO PROJECTDETAILS VALUES('Persons','1.1','20 August 2015','landuyt');");
		template.execute("INSERT INTO PROJECTDETAILS VALUES('Ontology Engineering','1.1','20 August 2015','Masoud');");
		
		template.execute("INSERT INTO USERACCESS VALUES('user','E-Gitarren');");
        template.execute("INSERT INTO USERACCESS VALUES('test','AnalisiDelFusto');");
        template.execute("INSERT INTO USERACCESS VALUES('user','Persons');");
        template.execute("INSERT INTO USERACCESS VALUES('test','Home Improvement Tools');");
		}
		catch(Exception ex)
		{
		}
    }
    
    public void setDataSource(final DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }
}