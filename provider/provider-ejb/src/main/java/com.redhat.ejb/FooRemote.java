package com.redhat.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote
public class FooRemote implements IFoo{
    @Override
    public String foo() {
        return "Hi .. I'm foo.";
    }
}
