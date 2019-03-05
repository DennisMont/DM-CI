package org.seedstack.samples.store.interfaces.rest.customer;

import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class CustomersResourceTest {

    @Test(expected = NullPointerException.class)
    public void customer() throws URISyntaxException {
        CustomersResource c_res = new CustomersResource();
        c_res.getCustomer("25");
    }
}