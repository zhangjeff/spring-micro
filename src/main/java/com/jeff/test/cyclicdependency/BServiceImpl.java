package com.jeff.test.cyclicdependency;

public class BServiceImpl implements BService {

    private AService aService;

    public void setaService(AService aService) {
        this.aService = aService;
    }

    public AService getaService() {
        return aService;
    }
}
