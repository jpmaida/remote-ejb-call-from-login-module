package com.redhat.rest;

import com.redhat.ejb.IFoo;

import javax.enterprise.context.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Properties;

@Path("/foo")
@RequestScoped
public class Foo {
    @GET
    public Response foo(){
        try {
            Properties p = new Properties();
            p.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            p.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            p.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            InitialContext context = new InitialContext(p);
            String jndi = "ejb:provider-ear/provider-ejb/FooRemote!com.redhat.ejb.IFoo";
            Object o = context.lookup(jndi);
            IFoo fooService = (IFoo) o;
            return Response.ok(fooService.foo()).build();
        } catch (Exception e){
            return Response.serverError().build();
        }
    }
}
