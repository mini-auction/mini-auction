package com.mini.auction.common;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalTableName(Identifier logicalName, JdbcEnvironment context) {
        String originalText = logicalName.getText();
        String customText = originalText.substring(0, originalText.length() - 6);
        System.out.println("check table naming : " + customText);
        return new Identifier(customText, logicalName.isQuoted());
    }
}
