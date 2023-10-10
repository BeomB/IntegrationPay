package beom.pay.enums;

import lombok.Getter;

@Getter
public enum ServiceGroup {

    EXIMBAY_PAY("EXIMBAY","https://api-test.eximbay.com"),
    SMARTRO_PAY("SMARTRO","tpay.smartropay.co.kr");

    private final String company;
    private final String testDomain;

    ServiceGroup(String company, String testDomain) {
        this.company = company;
        this.testDomain = testDomain;
    }
}
