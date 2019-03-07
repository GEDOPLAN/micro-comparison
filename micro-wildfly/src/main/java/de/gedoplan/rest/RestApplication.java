package de.gedoplan.rest;

import javax.enterprise.context.Dependent;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@Dependent
@ApplicationPath("rs")
public class RestApplication extends Application {

}
