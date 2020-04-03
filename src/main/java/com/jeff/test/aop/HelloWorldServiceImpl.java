package com.jeff.test.aop;


/**
 * @author yihua.huang@dianping.com
 */
public class HelloWorldServiceImpl implements HelloWorldService {

    private String text;

    private OutputService outputService;

    public void setText(String text) {
        this.text = text;
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }

    public OutputService getOutputService() {
        return outputService;
    }

    @Override
    public void helloWorld() {
        outputService.output(text);
    }
}
