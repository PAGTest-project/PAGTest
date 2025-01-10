
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedSecurityScheme_toStringTest {

    @Test
    public void testToString() {
        SecurityScheme oldScheme = new SecurityScheme();
        oldScheme.setType("apiKey");
        oldScheme.setName("api_key");
        oldScheme.setIn("header");

        SecurityScheme newScheme = new SecurityScheme();
        newScheme.setType("http");
        newScheme.setScheme("bearer");
        newScheme.setBearerFormat("JWT");

        ChangedSecuritySchemeScopes changedScopes = new ChangedSecuritySchemeScopes();
        ChangedMetadata description = new ChangedMetadata();
        ChangedOAuthFlows oAuthFlows = new ChangedOAuthFlows();
        ChangedExtensions extensions = new ChangedExtensions();

        ChangedSecurityScheme changedSecurityScheme = new ChangedSecurityScheme(oldScheme, newScheme, null)
                .setChangedType(true)
                .setChangedIn(false)
                .setChangedScheme(true)
                .setChangedBearerFormat(true)
                .setChangedOpenIdConnectUrl(false)
                .setChangedScopes(changedScopes)
                .setDescription(description)
                .setOAuthFlows(oAuthFlows)
                .setExtensions(extensions);

        String expected = "ChangedSecurityScheme(oldSecurityScheme=SecurityScheme{type=apiKey, description='null', name='api_key', in=header, scheme='null', bearerFormat='null', flows=null, openIdConnectUrl='null'}, newSecurityScheme=SecurityScheme{type=http, description='null', name='null', in=null, scheme=bearer, bearerFormat=JWT, flows=null, openIdConnectUrl='null'}, changedType=true, changedIn=false, changedScheme=true, changedBearerFormat=true, changedOpenIdConnectUrl=false, changedScopes=ChangedSecuritySchemeScopes{increased=[], missing=[]}, description=ChangedMetadata{diffResult=NO_CHANGES, oldValue='null', newValue='null'}, oAuthFlows=ChangedOAuthFlows{diffResult=NO_CHANGES, added=null, decreased=null, changed=null}, extensions=ChangedExtensions{diffResult=NO_CHANGES, changed=[]})";

        assertEquals(expected, changedSecurityScheme.toString());
    }
}
