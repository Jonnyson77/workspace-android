package com.java.demo.child;

import java.io.Serializable;

public interface Chid {
    int getAge();
    String getName();
}

class ChildImpl implements  Chid, Serializable {

    @Override
    public int getAge() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }
}


final class ChildImpl1 extends ChildImpl implements Serializable{

}
