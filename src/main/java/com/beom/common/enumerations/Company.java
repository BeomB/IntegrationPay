package com.beom.common.enumerations;

public enum Company {
    SMARTRO("Smartro","스마트로"),
    EXIMBAY("Eximbay","엑심베이"),
    KCP("NHN Kcp","케이시피");

    private String koreanName;

    private String englishName;

    Company(String koreanName, String englishName) {
        this.koreanName = koreanName;
        this.englishName = englishName;
    }
}
