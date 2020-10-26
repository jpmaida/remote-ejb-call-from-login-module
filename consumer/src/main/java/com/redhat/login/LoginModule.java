package com.redhat.login;

import com.redhat.ejb.IFoo;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import java.util.Map;
import java.util.Properties;

public class LoginModule implements javax.security.auth.spi.LoginModule {
    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        System.out.println("Some init here");
    }

    @Override
    public boolean login() throws LoginException {
        try {
            Properties p = new Properties();
            p.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            p.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            p.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            InitialContext context = new InitialContext(p);
            String jndi = "ejb:provider-ear/provider-ejb/FooRemote!com.redhat.ejb.IFoo";
            Object o = context.lookup(jndi);
            IFoo fooService = (IFoo) o;
            System.out.println(fooService.foo());
            System.out.println("I'm logged in.");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean commit() throws LoginException {
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        System.out.println("I'm logged out.");
        return true;
    }
}
